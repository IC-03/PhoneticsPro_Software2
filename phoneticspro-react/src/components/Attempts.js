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
} from "chart.js";

ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
);

const Attempts = () => {
  const [listaPorId, setListaPorId] = useState([]);
  let id = sessionStorage.getItem("id_user");

  useEffect(() => {
    const fetchAndCombineAttempts = async () => {
      try {
        const response = await fetch("http://localhost:8094/api/Attempt/list");
        const data = await response.json();

        const filteredData = data.filter(
          (intento) => id === intento.user.id
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
    const day = currentDate.getUTCDay();
    const diff = currentDate.getUTCDate() - day + (day === 0 ? -6 : 1); // adjust when day is Sunday
    const startOfWeek = new Date(currentDate.setUTCDate(diff));
    startOfWeek.setUTCHours(0, 0, 0, 0); // set time to the start of the day
    return startOfWeek;
  }

  function getEndOfWeek(date) {
    const startOfWeek = getStartOfWeek(date);
    const endOfWeek = new Date(startOfWeek);
    endOfWeek.setUTCDate(endOfWeek.getUTCDate() + 6);
    endOfWeek.setUTCHours(23, 59, 59, 999); // set time to the end of the day
    return endOfWeek;
  }

  const now = new Date();
  const startOfWeek = getStartOfWeek(now);
  const endOfWeek = getEndOfWeek(now);

  const dataThisWeek = listaPorId.filter((intento) => {
    const intentoDate = new Date(intento.date_attempt);
    return intentoDate >= startOfWeek && intentoDate <= endOfWeek;
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
    date.setUTCDate(startOfWeek.getUTCDate() + index);
    const dateString = formatoFecha(date);

    const dailyAttempts = dataThisWeek.find(
      (int) => formatoFecha(int.date_attempt) === dateString
    ) || { total_attempt: 0, correct_attempt: 0 };
    dailyAttempts.wrong_attempt =
      dailyAttempts.total_attempt - dailyAttempts.correct_attempt;
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
      <div>
        <Line data={data} options={options} />
      </div>
    </div>
  );
};

export default Attempts;
