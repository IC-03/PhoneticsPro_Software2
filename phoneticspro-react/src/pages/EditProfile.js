import React, { useState, useEffect } from "react";
import Navbar from "../components/Navbar";
import profileimg from "../assets/images/usuario.png";
import { useNavigate } from "react-router-dom";
import APIInvoke from "../utils/APIInvoke";
import swal from "sweetalert";

const EditProfile = () => {
  const navigate = useNavigate();
  const id_user_ = sessionStorage.getItem("id_user");

  const [BDusuario, setBDUsuario] = useState({
    id_user: "",
    email: "",
    name_user: "",
    password_user: "",
  });

  const [usuario, setUsuario] = useState({
    new_email: "",
    old_password_user: "",
    new_name_user: "",
    new_password_user: "",
  });

  const { new_email, old_password_user, new_name_user, new_password_user } =
    usuario;

  const onChange = (e) => {
    setUsuario({
      ...usuario,
      [e.target.name]: e.target.value,
    });
  };

  useEffect(() => {
    const cargarUsuario = async () => {
      try {
        const response = await APIInvoke.invokeGET(
          `api/Users/list/${id_user_}`
        );
        if (response) {
          setBDUsuario(response);
        } else {
          throw new Error("No se pudo obtener el usuario");
        }
      } catch (error) {
        console.error("Error al cargar el usuario", error);
      }
    };

    if (id_user_ >= 0) {
      cargarUsuario();
    }
  }, [id_user_]);

  const confirmarCambios = async (password) => {
    if (BDusuario.password_user === password) {
      // Verificar si no se han realizado cambios
      if (
        usuario.new_email === "" &&
        usuario.new_name_user === "" &&
        usuario.new_password_user === ""
      ) {
        swal("No hay cambios", "No hay nada que guardar", "info");
        return;
      }

      if (usuario.new_email === "") {
        usuario.new_email = BDusuario.email;
      }
      if (usuario.new_password_user === "") {
        usuario.new_password_user = BDusuario.password_user;
      }
      if (usuario.new_name_user === "") {
        usuario.new_name_user = BDusuario.name_user;
      }

      const data = {
        id_user: BDusuario.id_user,
        email: usuario.new_email,
        password_user: usuario.new_password_user,
        name_user: usuario.new_name_user,
      };

      if(sessionStorage.getItem("name_user") !== data.name_user){
        sessionStorage.setItem("name_user", data.name_user);
      }

      const response = await APIInvoke.invokePUT(`api/Users/`, data);
      if (response.id === usuario.id_user) {
        swal("Éxito", "Perfil editado correctamente", "success");
        navigate("/");
      } else {
        const msg = "Ha ocurrido un error";
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
      }
    } else {
      const msg = "La contraseña no coincide, inténtelo nuevamente";
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
    }
  };

  const ActualizarUsuario = async (password) => {
    if (
      new_password_user !== "" &&
      (new_password_user.length < 6 ||
        new_password_user.length > 20 ||
        /\s/.test(new_password_user) ||
        /[^a-zA-Z0-9]/.test(new_password_user))
    ) {
      const msg =
        "La nueva contraseña debe tener entre 6 y 20 caracteres, sin espacios ni caracteres especiales";
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
      return;
    }

    await confirmarCambios(password);
  };

  const onSubmit = (e) => {
    e.preventDefault();
    swal({
      title: "Confirme su contraseña",
      text: "Por favor, ingresa tu contraseña para confirmar los cambios",
      content: {
        element: "input",
        attributes: {
          type: "password",
        },
      },
      buttons: {
        cancel: "Cancelar",
        confirm: {
          text: "Confirmar",
          closeModal: false,
        },
      },
    })
      .then((password) => {
        if (!password) {
          swal("Error", "Cambio cancelado", "error");
          return;
        }
        ActualizarUsuario(password);
      })
      .catch((error) => {
        if (error) {
          console.error("Unknown promise rejection reason:", error);
        }
      });
  };

  const Borrado = async (password) => {
    if (BDusuario.password_user === password) {
      const response = await APIInvoke.invokeDELETE(`api/Users/${id_user_}`);
      navigate("/");
      sessionStorage.clear();

      if (response.success) {
        swal({
          title: "Borrado Exitoso",
          text: "Tu cuenta ha sido borrada satisfactoriamente.",
          icon: "success",
          buttons: {
            confirm: {
              text: "Ok",
              value: true,
              visible: true,
              className: "btn btn-success",
              closeModal: true,
            },
          },
        });
      } else {
        const msg = "Ha ocurrido un error al borrar la cuenta";
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
      }
    } else {
      const msg = "La contraseña no coincide, debes poner tu contraseña actual";
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
    }
  };

  const borrarCuenta = (e) => {
    e.preventDefault();
    swal({
      title: "Confirma tu contraseña",
      text: "Por favor, ingresa tu contraseña para confirmar la eliminación de la cuenta",
      content: {
        element: "input",
        attributes: {
          type: "password",
        },
      },
      buttons: {
        cancel: "Cancelar",
        confirm: {
          text: "Confirmar",
          closeModal: false,
        },
      },
    })
      .then((password) => {
        if (!password) {
          swal("Error", "Cancelado", "error");
          return;
        }
        Borrado(password);
      })
      .catch((error) => {
        if (error) {
          console.error("Unknown promise rejection reason:", error);
        }
      });
  };

  return (
    <div>
      <Navbar />
      <div
        className="container mt-5 mb-4 p-5"
        style={{ backgroundColor: "rgb(204, 204, 204)", borderRadius: 15 }}
      >
        <div className="justify-content-center">
          <div className="text-center ">
            <h3>Editar perfil de {sessionStorage.getItem("name_user")}</h3>
            <div className="mb-2 mt-2">
              <img
                src={profileimg}
                alt="profile"
                style={{ width: 128, height: 128 }}
              />
            </div>
          </div>
        </div>

        <form onSubmit={onSubmit}>
          <div className="mb-3">
            <label htmlFor="name_user" className="form-label">
              Nombre de usuario
            </label>
            <input
              type="text"
              className="form-control"
              placeholder={BDusuario.name_user}
              id="new_name_user"
              name="new_name_user"
              value={new_name_user}
              onChange={onChange}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="email" className="form-label">
              Correo electrónico
            </label>
            <input
              type="email"
              className="form-control"
              placeholder={BDusuario.email}
              id="new_email"
              name="new_email"
              value={new_email}
              onChange={onChange}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="old_password_user" className="form-label">
              Contraseña vieja
            </label>
            <input
              type="password"
              className="form-control"
              id="old_password_user"
              name="old_password_user"
              value={old_password_user}
              onChange={onChange}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="new_password_user" className="form-label">
              Contraseña nueva
            </label>
            <input
              type="password"
              className="form-control"
              id="new_password_user"
              name="new_password_user"
              value={new_password_user}
              onChange={onChange}
            />
            <div>
              <small className="form-text text-muted">
                Su contraseña debe tener entre 6 y 20 caracteres entre letras y
                números, no deben haber espacios, caracteres especiales ni
                emojis.
              </small>
            </div>
          </div>
          <div className="text-center">
            <div className="form-text pt-2">No olvide guardar sus cambios</div>
            <button
              className="btn btn-success btn-block text-center mt-1 mb-3 mb-5"
              id="guardar"
            >
              Guardar
            </button>
          </div>
        </form>
        <form onSubmit={borrarCuenta}>
          <div
            className="container p-4 text-center"
            style={{ backgroundColor: "#EE8D85", borderRadius: 15 }}
          >
            <h4 className="text-center">Zona de peligro</h4>
            <hr style={{ backgroundColor: "#1C1C1C" }} />
            <div className="form-text">¿Desea borrar su cuenta?</div>
            <button className="btn btn-danger btn-block text-center mb-">
              Borrar cuenta
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default EditProfile;
