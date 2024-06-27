import React from "react";
import Navbar from "../components/Navbar";
import Attempts from "../components/Attempts";
import profileimg from "../assets/images/usuario.png";

const Profile = () => {
  return (
    <div>
      <Navbar />
      <div
        className="container mt-5 px-5 pt-5 pb-3 mb-3"
        style={{ backgroundColor: "rgb(204, 204, 204)", borderRadius: 15 }}
      >
        <div className="justify-content-center">
          <div className="text-center">
            <h3> Welcome, {sessionStorage.getItem("name_user")} </h3>
            <div className="mb-4">
              <img
                src={profileimg}
                alt=""
                style={{ width: 128, height: 128 }}
              />
              <br />
            </div>

            <div
              className="container p-2 "
              style={{
                backgroundColor: "rgb(255, 255, 255)",
                borderRadius: 15,
              }}
            >
                <h4 className="mt-2"> Seguimiento Semanal </h4>

              <Attempts />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Profile;
