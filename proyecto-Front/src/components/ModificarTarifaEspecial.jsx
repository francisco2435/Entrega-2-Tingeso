import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import {
  Box,
  TextField,
  Button,
  Typography,
  CircularProgress,
  MenuItem,
} from "@mui/material";
import tarifaServicio from "../services/tarifa.servicio"; // Asegúrate de que `modificarTarifaEspecial` y `obtenerTarifaEspecial` estén definidos ahí

const tipos = ["normal", "fin de semana", "dia especial"];

const ModificarTarifaEspecial = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [tarifa, setTarifa] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    tarifaServicio
      .obtenerTarifaEspecial(id)
      .then((res) => {
        setTarifa(res.data);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error al obtener tarifa especial:", error);
        setLoading(false);
      });
  }, [id]);

  const handleChange = (e) => {
    setTarifa({ ...tarifa, [e.target.name]: e.target.value });
  };

  const handleSubmit = () => {
    tarifaServicio
      .modificarTarifaEspecial(tarifa)
      .then(() => {
        alert("Tarifa especial modificada exitosamente");
        navigate("/tarifas"); // Cambia esto según tu ruta real
      })
      .catch((error) => {
        console.error("Error al modificar tarifa especial:", error);
        alert("Hubo un error al modificar la tarifa especial.");
      });
  };

  if (loading || !tarifa) {
    return (
      <Box sx={{ display: "flex", justifyContent: "center", mt: 4 }}>
        <CircularProgress />
      </Box>
    );
  }

  return (
    <Box sx={{ maxWidth: 500, mx: "auto", mt: 5 }}>
      <Typography variant="h5" gutterBottom>
        Modificar Tarifa Especial
      </Typography>

      <TextField
        label="Número de Vueltas"
        name="numeroVueltas"
        type="number"
        fullWidth
        margin="normal"
        value={tarifa.numeroVueltas}
        onChange={handleChange}
      />

      <TextField
        label="Tiempo Máximo"
        name="tiempoMax"
        type="number"
        fullWidth
        margin="normal"
        value={tarifa.tiempoMax}
        onChange={handleChange}
      />

      <TextField
        label="Precio"
        name="precio"
        type="number"
        fullWidth
        margin="normal"
        value={tarifa.precio}
        onChange={handleChange}
      />

      <TextField
        label="Duración de Reserva"
        name="duracionReserva"
        type="number"
        fullWidth
        margin="normal"
        value={tarifa.duracionReserva}
        onChange={handleChange}
      />

      <TextField
        label="Tipo"
        name="tipo"
        select
        fullWidth
        margin="normal"
        value={tarifa.tipo}
        onChange={handleChange}
      >
        {tipos.map((tipo) => (
          <MenuItem key={tipo} value={tipo}>
            {tipo}
          </MenuItem>
        ))}
      </TextField>

      <Box sx={{ mt: 3, display: "flex", justifyContent: "space-between" }}>
        <Button variant="contained" color="primary" onClick={handleSubmit}>
          Guardar cambios
        </Button>
        <Button variant="outlined" onClick={() => navigate("/tarifas")}>
          Cancelar
        </Button>
      </Box>
    </Box>
  );
};

export default ModificarTarifaEspecial;
