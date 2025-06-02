import React, { useState } from "react";
import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFns';
import { LocalizationProvider, DateCalendar } from '@mui/x-date-pickers';
import { Box, Button, Typography } from "@mui/material";
import esLocale from 'date-fns/locale/es';
import { useNavigate } from "react-router-dom";
import { useFecha } from "../components/FechaContext";
import addDays from "date-fns/addDays";
import isBefore from "date-fns/isBefore";
import formatISO from "date-fns/formatISO";

const Calendario = () => {
  const [fecha, setFecha] = useState(null);
  const { setFechaSeleccionada } = useFecha();
  const navigate = useNavigate();

  const hoy = new Date();
  const minimo = addDays(hoy, 1); // Día siguiente

  const handleChange = (nuevaFecha) => {
    if (isBefore(nuevaFecha, minimo)) {
      setFecha(null);
    } else {
      setFecha(nuevaFecha);
    }
  };

  const handleReservar = () => {
    if (fecha) {
      const localDate = formatISO(fecha, { representation: "date" }); // YYYY-MM-DD
      setFechaSeleccionada(localDate);
      navigate("/eleccion-tarifa");
    }
  };

  return (
    <LocalizationProvider dateAdapter={AdapterDateFns} adapterLocale={esLocale}>
      <Box display="flex" flexDirection="column" alignItems="center" mt={4}>
        <Typography variant="h5" gutterBottom>
          Selecciona una fecha para reservar
        </Typography>

        <Box
          sx={{
            transform: 'scale(1.5)',
            transformOrigin: 'top left',
          }}
        >
          <DateCalendar
            value={fecha}
            onChange={handleChange}
            disablePast
            minDate={minimo}
            sx={{
                width: 350,
                height: 350,
                mt: 2,   // margen arriba
                ml: -10,   // margen izquierda (mover a la derecha)
            }}
            />
        </Box>

        {fecha && (
          <Button
            variant="contained"
            color="primary"
            onClick={handleReservar}
            sx={{ mt: 20, ml: 5 }} // mt = margin-top, ml = margin-left
            >
            Hacer reserva este día
            </Button>
        )}
      </Box>
    </LocalizationProvider>
  );
};

export default Calendario;
