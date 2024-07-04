import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { eyeIcons, togglePassword } from "../utils/PasswordEyeForm.js";
import swal from "sweetalert";
import APIInvoke from "../utils/APIInvoke.js";
import Navbar from "../components/Navbar.js";

const Login = () => {
  const navigate = useNavigate();

  const [usuario, setUsuario] = useState({
    email: "",
    password_user: "",
  });

  const { email, password_user } = usuario;

  const onChange = (e) => {
    setUsuario({
      ...usuario,
      [e.target.name]: e.target.value,
    });
  };

  const login = async () => {
    if (password_user.length < 6) {
      const msg = "La contraseña debe ser al menos de 6 caracteres.";
      swal({
        title: "Error",
        text: msg,
        icon: "error",
        buttons: {
          confirm: {
            text: "Ok",
            value: true,
            visible: true,
            className: "btn btn-danger",
            closeModal: true,
          },
        },
      });
    } else {
      const data = {
        email: usuario.email,
        password_user: usuario.password_user,
      };

      const response = await APIInvoke.invokePOST(`api/Users/login`, data);
      const mensaje = response.Mensaje;

      if (mensaje === "Alerta: email o password incorrectos") {
        const msg =
          "No fue posible iniciar la sesión verifique los datos ingresados.";
        swal({
          title: "Error",
          text: msg,
          icon: "error",
          buttons: {
            confirm: {
              text: "Ok",
              value: true,
              visible: true,
              className: "btn btn-danger",
              closeModal: true,
            },
          },
        });
      } else {
        //guardamos en el localstorage
        sessionStorage.setItem('id_user', response.Usuario.id)
        sessionStorage.setItem('name_user', response.Usuario.nameUser)


        //redireccionamos al home la pagina principal
        navigate("/");
      }
    }
  };

  const onSubmit = (e) => {
    e.preventDefault();
    login();
  };

  const [showPassword, setShowPassword] = useState(false);

  return (
    <div>
      <Navbar/>
      <div
        className="container mt-5 p-5"
        style={{ backgroundColor: "rgb(204, 204, 204)", borderRadius: 20 }}
      >
        <div className="text-center">
          <h3>Inicia sesión</h3>
        </div>

        <form onSubmit={onSubmit}>
          <div className="mb-3">
            <label htmlFor="email" className="form-label">
              Email
            </label>
            <input
              type="text"
              className="form-control"
              placeholder="correo@example.com"
              id="email"
              name="email"
              value={email}
              onChange={onChange}
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="password-field" className="form-label">
              Contraseña
            </label>
            <div className="password-wrapper">
              <input
                type={showPassword ? "text" : "password"}
                id="password-field"
                className="form-control"
                placeholder="************"
                name="password_user"
                value={password_user}
                onChange={onChange}
                
              />
              <div
                className="toggle-button"
                onClick={() => togglePassword(showPassword, setShowPassword)}
              >
                {showPassword ? eyeIcons.closed : eyeIcons.open}
              </div>
            </div>
          </div>
          <div className="form-check">
            <input
              className="form-check-input"
              type="checkbox"
              defaultValue
              id="flexCheckDefault"
            />
            <label className="form-check-label" htmlFor="flexCheckDefault">
              Recordar contraseña
            </label>
          </div>

          <button
            className="btn btn-block btn-success text-center mt-2" /*onClick={validarLogin}*/
          >
            Entrar
          </button>
        </form>
      </div>
    </div>
  );
};

export default Login;