import React, { useEffect, useState } from "react";
import { Line } from "react-chartjs-2";
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
  Filler,
} from "chart.js";

ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
  Filler
);

const Attempts = () => {
  const [listaPorId, setListaPorId] = useState([]);
  let id = sessionStorage.getItem("id_user");
  id = parseInt(id);

  useEffect(() => {
    const fetchAndCombineAttempts = async () => {
      try {
        const response = await fetch("http://34.16.128.99:8094/api/Attempt/list");
        const data = await response.json();

        const filteredData = data.filter(
          (intento) => id === intento.id_user.id_user
        );

        let combinedList = [...filteredData];
        for (let i = 0; i < combinedList.length; i++) {
          for (let j = i + 1; j < combinedList.length; j++) {
            if (combinedList[i].date_attempt === combinedList[j].date_attempt) {
              combinedList[i].total_attempt += combinedList[j].total_attempt;
              combinedList[i].correct_attempt +=
                combinedList[j].correct_attempt;
              combinedList.splice(j, 1);
              j--;
            }
          }
        }

        setListaPorId(combinedList);
      } catch (error) {
        console.error("Error al cargar las palabras", error);
        alert("Ha ocurrido un error, intente más tarde");
      }
    };

    fetchAndCombineAttempts();
  }, [id]);

  function formatoFecha(fecha) {
    const date = new Date(fecha);
    const year = date.getFullYear();
    let month = (date.getMonth() + 1).toString();
    let day = date.getDate().toString();
    if (month.length === 1) {
      month = "0" + month;
    }
    if (day.length === 1) {
      day = "0" + day;
    }
    return `${year}-${month}-${day}`;
  }

  function getStartOfWeek(date) {
    const currentDate = new Date(date);
    const day = currentDate.getDay();
    const diff = currentDate.getDate() - day + (day === 0 ? -6 : 1); // adjust when day is sunday
    return new Date(currentDate.setDate(diff));
  }

  function getEndOfWeek(date) {
    const startOfWeek = getStartOfWeek(date);
    return new Date(startOfWeek.setDate(startOfWeek.getDate() + 6));
  }

  const now = new Date();
  const startOfWeek = getStartOfWeek(now);
  const endOfWeek = getEndOfWeek(now);

  //console.log("startOfWeek:", formatoFecha(startOfWeek));
  //console.log("endOfWeek:", formatoFecha(endOfWeek));

  const dataThisWeek = listaPorId.filter((intento) => {
    const intentoDate = new Date(intento.date_attempt).toISOString().split('T')[0];
    //console.log("intentoDate:", intentoDate);
    return intentoDate >= formatoFecha(startOfWeek) && intentoDate <= formatoFecha(endOfWeek);
  });

  const daysOfWeek = [
    "Lunes",
    "Martes",
    "Miércoles",
    "Jueves",
    "Viernes",
    "Sábado",
    "Domingo",
  ];

  const weeklyData = daysOfWeek.map((day, index) => {
    const date = new Date(startOfWeek);
    date.setDate(date.getDate() + index);
    const dateString = date.toISOString().split('T')[0];

    const dailyAttempts = dataThisWeek.find(
      (int) => new Date(int.date_attempt).toISOString().split('T')[0] === dateString
    ) || { total_attempt: 0, correct_attempt: 0 };
    dailyAttempts.wrong_attempt =
      dailyAttempts.total_attempt - dailyAttempts.correct_attempt;

    //console.log(`Day: ${day}, DateString: ${dateString}, DailyAttempts:`, dailyAttempts);

    return dailyAttempts;
  });

  const data = {
    labels: daysOfWeek,
    datasets: [
      {
        label: "Intentos Correctos",
        data: weeklyData.map((int) => int.correct_attempt),
        borderColor: "rgba(75, 192, 192, 1)",
        backgroundColor: "rgba(75, 192, 192, 0.2)",
        fill: true,
      },
      {
        label: "Intentos Fallidos",
        data: weeklyData.map((int) => int.wrong_attempt),
        borderColor: "rgba(255, 99, 132, 1)",
        backgroundColor: "rgba(255, 99, 132, 0.2)",
        fill: true,
      },
    ],
  };

  const options = {
    responsive: true,
    plugins: {
      legend: {
        position: "top",
      },
      title: {
        display: true,
        text: "Intentos Correctos y Fallidos",
      },
    },
    scales: {
      y: {
        beginAtZero: true,
      },
    },
  };

  return (
    <div>
      {/* 
      <ul style={{ listStyle: "none", padding: 0 }}>
        {listaPorId.map((intento, index) => (
          <li key={index}>
            {" "}
            <b>{formatoFecha(intento.date_attempt)}:</b> <br />
            Intentos totales: {intento.total_attempt}, <br />
            Intentos correctos: {intento.correct_attempt}.{" "}
          </li>
        ))}
      </ul>*/}
      <div>
        <Line data={data} options={options} />
      </div>
    </div>
  );
};

export default Attempts;
