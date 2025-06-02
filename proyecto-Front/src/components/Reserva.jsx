import React, { useState, useEffect } from 'react';
import {
  Box,
  TextField,
  Button,
  Typography,
  Stack,
  IconButton,
  Snackbar,
  Alert,
} from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import reservaServicio from '../services/reserva.servicio';
import { useTarifa } from "../components/TarifaContext";
import { useFecha } from "../components/FechaContext";
import { MenuItem, Select, FormControl, InputLabel } from '@mui/material';


const Reserva = () => {
  const usuario = JSON.parse(localStorage.getItem('usuario')) || {};

  const [fechaReserva, setFechaReserva] = useState('');
  const [horaInicio, setHoraInicio] = useState('');
  const [tiempoMax, setTiempoMax] = useState('');
  const [numVueltas, setNumVueltas] = useState('');
  const [precioTarifa, setPrecioTarifa] = useState('');
  const [duracionReserva, setDuracionReserva] = useState('');
  const [tipoTarifa, setTipoTarifa] = useState('');
  const { tarifaSeleccionada } = useTarifa();
  const { fechaSeleccionada } = useFecha();

  const [rutAmigo, setRutAmigo] = useState('');
  const [nombreAmigo, setNombreAmigo] = useState('');
  const [rutsAmigos, setRutsAmigos] = useState([]);
  const [nombres, setNombres] = useState([]);

  const [mensajeConfirmacion, setMensajeConfirmacion] = useState(false);

  if (!tarifaSeleccionada) {
    return <p>No hay tarifa seleccionada.</p>;
  }

  const agregarAmigo = () => {
    if (rutAmigo && nombreAmigo) {
      setRutsAmigos([...rutsAmigos, rutAmigo]);
      setNombres([...nombres, nombreAmigo]);
      setRutAmigo('');
      setNombreAmigo('');
    }
  };

  const eliminarAmigo = (index) => {
    const nuevosRuts = [...rutsAmigos];
    const nuevosNombres = [...nombres];
    nuevosRuts.splice(index, 1);
    nuevosNombres.splice(index, 1);
    setRutsAmigos(nuevosRuts);
    setNombres(nuevosNombres);
  };

  const handleReserva = async () => {
    const reserva = {
      rutCliente: usuario.rut,
      nombreCliente: usuario.nombre,
      correoCliente: usuario.correo,
      fechaReserva,
      horaInicio,
      tiempoMax: parseInt(tiempoMax),
      numVueltas: parseInt(numVueltas),
      precioTarifa: parseFloat(precioTarifa),
      duracionReserva: parseInt(duracionReserva),
      tipoTarifa,
      rutsAmigos,
      nombres,
    };

    try {
      await reservaServicio.hacerReserva(reserva);
      setMensajeConfirmacion(true);
      setFechaReserva('');
      setHoraInicio('');
      setTiempoMax('');
      setNumVueltas('');
      setPrecioTarifa('');
      setDuracionReserva('');
      setTipoTarifa('');
      setRutsAmigos([]);
      setNombres([]);
    } catch (error) {
      console.error('Error al realizar la reserva:', error);
    }
  };

  useEffect(() => {
    if (fechaSeleccionada) {
      setFechaReserva(fechaSeleccionada);
    }
  }, [fechaSeleccionada]);

  useEffect(() => {
    if (tarifaSeleccionada) {
      setTiempoMax(tarifaSeleccionada.tiempoMax || '');
      setNumVueltas(tarifaSeleccionada.numeroVueltas || '');
      setPrecioTarifa(tarifaSeleccionada.precio || '');
      setDuracionReserva(tarifaSeleccionada.duracionReserva || '');
      setTipoTarifa(tarifaSeleccionada.tipo || '');
    }
  }, [tarifaSeleccionada]);

  const generarHorasDisponibles = () => {
    const horas = [];
    let inicio = 14;
    let fin = 22;

    if (tipoTarifa.toLowerCase() === 'fin de semana' || tipoTarifa.toLowerCase() === 'día especial') {
      inicio = 10;
    }

    for (let h = inicio; h <= fin; h++) {
      const hora = h.toString().padStart(2, '0') + ':00';
      horas.push(hora);
    }

    return horas;
  };

  return (
    <Box sx={{ p: 4, maxWidth: 600, margin: 'auto' }}>
      <Typography variant="h5" mb={2}>
        Realizar Reserva
      </Typography>

      <TextField
        fullWidth
        label="Fecha de Reserva"
        type="date"
        value={fechaReserva}
        onChange={(e) => setFechaReserva(e.target.value)}
        sx={{ mb: 2 }}
        InputLabelProps={{ shrink: true }}
        disabled
      />

      <FormControl fullWidth sx={{ mb: 2 }}>
        <InputLabel id="hora-inicio-label">Hora de Inicio</InputLabel>
        <Select
          labelId="hora-inicio-label"
          value={horaInicio}
          label="Hora de Inicio"
          onChange={(e) => setHoraInicio(e.target.value)}
        >
          {generarHorasDisponibles().map((hora) => (
            <MenuItem key={hora} value={hora}>
              {hora}
            </MenuItem>
          ))}
        </Select>
      </FormControl>

      <TextField
        fullWidth
        label="Tiempo Máximo (minutos)"
        type="number"
        value={tiempoMax}
        sx={{ mb: 2 }}
        disabled
      />

      <TextField
        fullWidth
        label="Número de Vueltas"
        type="number"
        value={numVueltas}
        sx={{ mb: 2 }}
        disabled
      />

      <TextField
        fullWidth
        label="Precio Tarifa"
        type="number"
        value={precioTarifa}
        sx={{ mb: 2 }}
        disabled
      />

      <TextField
        fullWidth
        label="Duración Reserva (minutos)"
        type="number"
        value={duracionReserva}
        sx={{ mb: 2 }}
        disabled
      />

      <TextField
        fullWidth
        label="Tipo de Tarifa"
        value={tipoTarifa}
        sx={{ mb: 2 }}
        disabled
      />

      <Typography variant="h6" mt={3}>
        Añadir Amigos
      </Typography>

      <Stack direction="row" spacing={2} sx={{ mt: 1, mb: 2 }}>
        <TextField
          label="RUT Amigo"
          value={rutAmigo}
          onChange={(e) => setRutAmigo(e.target.value)}
        />
        <TextField
          label="Nombre Amigo"
          value={nombreAmigo}
          onChange={(e) => setNombreAmigo(e.target.value)}
        />
        <Button variant="contained" onClick={agregarAmigo}>
          Añadir
        </Button>
      </Stack>

      {rutsAmigos.map((rut, index) => (
        <Box
          key={index}
          display="flex"
          justifyContent="space-between"
          alignItems="center"
          sx={{
            border: '1px solid #ccc',
            borderRadius: 2,
            p: 1,
            mb: 1,
          }}
        >
          <Typography>
            {rut} - {nombres[index]}
          </Typography>
          <IconButton onClick={() => eliminarAmigo(index)} color="error">
            <DeleteIcon />
          </IconButton>
        </Box>
      ))}

      <Button
        variant="contained"
        color="primary"
        fullWidth
        sx={{ mt: 3 }}
        onClick={handleReserva}
      >
        Realizar Reserva
      </Button>

      <Snackbar
        open={mensajeConfirmacion}
        autoHideDuration={6000}
        onClose={() => setMensajeConfirmacion(false)}
      >
        <Alert
          onClose={() => setMensajeConfirmacion(false)}
          severity="success"
          sx={{ width: '100%' }}
        >
          El comprobante de su reserva ha sido enviado a su correo electrónico: {usuario.correo}
        </Alert>
      </Snackbar>
    </Box>
  );
};

export default Reserva;
