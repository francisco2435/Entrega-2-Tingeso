import React, { useEffect, useState } from "react";
import tarifaServicio from "../services/tarifa.servicio";
import {
  Box,
  Typography,
  TableContainer,
  Table,
  TableHead,
  TableBody,
  TableRow,
  TableCell,
  Paper,
  CircularProgress,
  Grid,
} from "@mui/material";

const TarifasUsuario = () => {
  const [tarifas, setTarifas] = useState([]);
  const [tarifasEspeciales, setTarifasEspeciales] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchTarifas = async () => {
      try {
        const [resNormales, resEspeciales] = await Promise.all([
          tarifaServicio.obtenerTarifas(),
          tarifaServicio.obtenerTarifasEspeciales(),
        ]);
        setTarifas(resNormales.data);
        setTarifasEspeciales(resEspeciales.data);
      } catch (error) {
        console.error("Error al obtener tarifas:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchTarifas();
  }, []);

  const renderTabla = (titulo, tarifas) => (
    <Box
      sx={{
        width: "100%",
        display: "flex",
        justifyContent: "center",
        mb: 4,
      }}
    >
      <Box sx={{ width: "100%", maxWidth: 1000, ml: 12}}>
        <Typography variant="h5" sx={{ mb: 2 }}>{titulo}</Typography>
        <TableContainer component={Paper} sx={{ width: "100%" }}>
          <Table sx={{ tableLayout: "fixed" }}>
            <TableHead sx={{ backgroundColor: "#1976d2" }}>
              <TableRow>
                <TableCell sx={{ color: "#fff", width: "20%" }}>Número de vueltas</TableCell>
                <TableCell sx={{ color: "#fff", width: "20%" }}>Tiempo Máximo</TableCell>
                <TableCell sx={{ color: "#fff", width: "20%" }}>Precio</TableCell>
                <TableCell sx={{ color: "#fff", width: "20%" }}>Duración de reserva</TableCell>
                <TableCell sx={{ color: "#fff", width: "20%" }}>Tipo</TableCell>
                <TableCell sx={{ color: "#fff", width: "20%", textAlign: "center" }}></TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {tarifas.map((tarifa, index) => (
                <TableRow key={index}>
                  <TableCell>{tarifa.numeroVueltas}</TableCell>
                  <TableCell>{tarifa.tiempoMax}</TableCell>
                  <TableCell>{tarifa.precio}</TableCell>
                  <TableCell>{tarifa.duracionReserva}</TableCell>
                  <TableCell>{tarifa.tipo}</TableCell>
                  <TableCell sx={{ color: "#fff", width: "20%", textAlign: "center" }}></TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </Box>
    </Box>
  );

  return (
    <Box
      sx={{
        padding: 4,
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "center",
      }}
    >
      <Typography variant="h4" sx={{ mb: 4 }}>
        Tarifas Disponibles
      </Typography>

      {loading ? (
        <Box sx={{ display: "flex", justifyContent: "center", mt: 4 }}>
          <CircularProgress />
        </Box>
      ) : (
        <Grid container spacing={4}>
          <Grid item xs={12}>
            {renderTabla("Tarifas Normales", tarifas)}
          </Grid>
          <Grid item xs={12}>
            {renderTabla("Tarifas Especiales", tarifasEspeciales)}
          </Grid>
        </Grid>
      )}
    </Box>
  );
};

export default TarifasUsuario;
