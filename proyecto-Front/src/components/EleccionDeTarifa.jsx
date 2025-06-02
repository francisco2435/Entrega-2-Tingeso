// src/pages/EleccionDeTarifa.jsx
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useFecha } from "../components/FechaContext";
import { useTarifa } from "../components/TarifaContext";
import tarifaServicio from "../services/tarifa.servicio";
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Button,
  Typography,
  Box,
  CircularProgress,
} from "@mui/material";

const EleccionDeTarifa = () => {
  const { fechaSeleccionada } = useFecha();
  const { setTarifaSeleccionada } = useTarifa(); // 👈 usar contexto
  const [tipoTarifa, setTipoTarifa] = useState(null);
  const [tarifas, setTarifas] = useState([]);
  const [tarifasEspeciales, setTarifasEspeciales] = useState([]);
  const [todasLasTarifas, setTodasLasTarifas] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const navigate = useNavigate();

  useEffect(() => {
    if (!fechaSeleccionada) return;

    const fetchTarifas = async () => {
      setLoading(true);
      setError(null);

      try {
        const resTipo = await tarifaServicio.obtenerTipoTarifaPorFecha(fechaSeleccionada);
        const tipo = resTipo.data;

        if (!tipo) {
          setError("No se pudo obtener el tipo de tarifa.");
          return;
        }

        setTipoTarifa(tipo);

        const resTarifas = await tarifaServicio.obtenerTarifaPorTipo(tipo);
        const resTarifasEspeciales = await tarifaServicio.obtenerTarifaEspecialPorTipo(tipo);

        const tarifasNormales = resTarifas.data || [];
        const tarifasEspeciales = resTarifasEspeciales.data || [];

        setTarifas(tarifasNormales);
        setTarifasEspeciales(tarifasEspeciales);
        setTodasLasTarifas([...tarifasNormales, ...tarifasEspeciales]);
      } catch (err) {
        console.error("Error al obtener tarifas:", err);
        setError("Error al cargar las tarifas. Intenta nuevamente.");
      } finally {
        setLoading(false);
      }
    };

    fetchTarifas();
  }, [fechaSeleccionada]);

  const handleSeleccionar = (tarifa) => {
    setTarifaSeleccionada(tarifa); // 👈 guardar en contexto
    navigate("/reserva"); // 👈 navegar sin pasar props
  };

  const renderTablaTarifas = (titulo, listaTarifas) => (
    <>
      <Typography variant="h6" gutterBottom align="center" mt={4}>
        {titulo}
      </Typography>
      <TableContainer component={Paper} sx={{ mb: 4 }}>
        <Table>
          <TableHead sx={{ backgroundColor: "#1976d2" }}>
            <TableRow>
              <TableCell sx={{ color: "#fff" }}>ID</TableCell>
              <TableCell sx={{ color: "#fff" }}>Número de vueltas</TableCell>
              <TableCell sx={{ color: "#fff" }}>Tiempo Máximo</TableCell>
              <TableCell sx={{ color: "#fff" }}>Precio</TableCell>
              <TableCell sx={{ color: "#fff" }}>Duración de reserva</TableCell>
              <TableCell sx={{ color: "#fff" }}>Tipo</TableCell>
              <TableCell sx={{ color: "#fff" }}>Acción</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {listaTarifas.length === 0 ? (
              <TableRow>
                <TableCell colSpan={7} align="center">
                  No hay tarifas disponibles.
                </TableCell>
              </TableRow>
            ) : (
              listaTarifas.map((tarifa) => (
                <TableRow key={tarifa.id}>
                  <TableCell>{tarifa.id}</TableCell>
                  <TableCell>{tarifa.numeroVueltas}</TableCell>
                  <TableCell>{tarifa.tiempoMax}</TableCell>
                  <TableCell>{tarifa.precio}</TableCell>
                  <TableCell>{tarifa.duracionReserva}</TableCell>
                  <TableCell>{tarifa.tipo}</TableCell>
                  <TableCell>
                    <Button variant="contained" color="primary" onClick={() => handleSeleccionar(tarifa)}>
                      Seleccionar
                    </Button>
                  </TableCell>
                </TableRow>
              ))
            )}
          </TableBody>
        </Table>
      </TableContainer>
    </>
  );

  if (!fechaSeleccionada) {
    return (
      <Typography variant="h6" color="error" align="center" mt={4}>
        Por favor selecciona una fecha antes de elegir una tarifa.
      </Typography>
    );
  }

  if (loading) {
    return (
      <Box display="flex" justifyContent="center" mt={4}>
        <CircularProgress />
      </Box>
    );
  }

  if (error) {
    return (
      <Typography variant="h6" color="error" align="center" mt={4}>
        {error}
      </Typography>
    );
  }

  return (
    <Box mt={4} mx="auto" maxWidth={800}>
      <Typography variant="h5" gutterBottom align="center">
        Tarifas para {tipoTarifa?.toUpperCase() || "..."}
      </Typography>
      {renderTablaTarifas("Todas las tarifas disponibles", todasLasTarifas)}
    </Box>
  );
};

export default EleccionDeTarifa;
