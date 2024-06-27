import React, {Fragment} from 'react'
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Home from './pages/Home';
import Login from './pages/Login';
import Register from './pages/Register'; 
import Profile from './pages/Profile';
import EditProfile from './pages/EditProfile';

function App() {
  return (
    <Fragment>
      <Router>
        <Routes>
          <Route path='/' exact element={<Home/>}/>
          <Route path='/iniciar-sesion' exact element={<Login/>}/>
          <Route path='/registro' exact element={<Register/>}/>
          <Route path='/perfil' exact element={<Profile/>}/>
          <Route path='/editar-perfil' exact element={<EditProfile/>}/>
        </Routes>
      </Router>
    </Fragment>
  );
}

export default App;
