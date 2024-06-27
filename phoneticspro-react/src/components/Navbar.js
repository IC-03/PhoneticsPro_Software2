import React, { useState, useEffect } from 'react';
import { Link } from "react-router-dom";
import '../assets/css/style.css';
import menubtn from '../assets/images/menu.png';

const Navbar = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  useEffect(() => {
    const token = sessionStorage.getItem('id_user');
    if (token) {
      setIsLoggedIn(true);
    } else {
      setIsLoggedIn(false);
    }
  }, []);

  const handleLogout = () => {
    sessionStorage.removeItem('id_user');
    setIsLoggedIn(false);
  };

  return (
    <nav className="navbar navbar-expand-lg bg-body-tertiary">
      <div className="container-fluid">
        <Link to={"/"} className="navbar-brand" id="Home2">
          Phonetics Pro
        </Link>
        <button
          className="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <img
            src={menubtn}
            alt="menu"
            style={{ width: 28, height: 28 }}
          />
        </button>
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav">
            {isLoggedIn ? (
              <>
                <li className="nav-item">
                  <Link to={"/"} className="nav-link" id="HomeLink">Home</Link>
                </li>
                <li className="nav-item">
                  <Link to={"/perfil"} className="nav-link" id="perfilLink">Perfil de {sessionStorage.getItem('name_user')}</Link>
                </li>
                <li className="nav-item">
                  <Link to={"/editar-perfil"} className="nav-link">Editar Perfil</Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" onClick={handleLogout} to={"/iniciar-sesion"}>Cerrar Sesión</Link>
                </li>
              </>
            ) : (
              <>
                <li className="nav-item">
                  <Link to={"/"} className="nav-link" id="HomeLink">Home</Link>
                </li>
                <li className="nav-item">
                  <Link to={"/iniciar-sesion"} className="nav-link">Iniciar Sesión</Link>
                </li>
                <li className="nav-item">
                  <Link to={"/registro"} className="nav-link">Registro</Link>
                </li>
              </>
            )}
          </ul>
        </div>
      </div>
    </nav>
  );
}

export default Navbar;
