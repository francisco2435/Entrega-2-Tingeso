import React from "react";
import infografia from "../images/infografia.png"; // Asegúrate de que la ruta sea correcta

const Home = () => {
  return (
    <div style={{ maxWidth: "900px", margin: "auto", padding: "20px" }}>

      <img
        src={infografia}
        style={{
        width: "120%",
        marginLeft: -90,  // empuja hacia la derecha
        marginRight: 0,
      }}
      />
    </div>
  );
};

export default Home;
