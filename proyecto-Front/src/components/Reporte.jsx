import React, { useState } from "react";
import {
  Box,
  Typography,
  CircularProgress,
  Button,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  TextField,
  MenuItem,
} from "@mui/material";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";
import { AdapterDateFns } from "@mui/x-date-pickers/AdapterDateFns";
import { es } from "date-fns/locale"; // para español
import reporteServicio from "../services/reporte.servicio";

const Reporte = () => {
  const [tipo, setTipo] = useState("Número de personas");
  const [fechaInicio, setFechaInicio] = useState(null);
  const [fechaFin, setFechaFin] = useState(null);
  const [reporte, setReporte] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const handleSubmit = async (event) => {
    event.preventDefault();
    if (!fechaInicio || !fechaFin) {
      setError("Por favor selecciona ambas fechas.");
      return;
    }

    setLoading(true);
    setError(null);

    try {
      const response = await reporteServicio.hacerReporte({
        tipo,
        fechaInicio: fechaInicio.toISOString().split("T")[0],
        fechaFin: fechaFin.toISOString().split("T")[0],
      });
      setReporte(response.data);
    } catch (error) {
      setError("Error al obtener el reporte.");
    } finally {
      setLoading(false);
    }
  };

  const renderTable = () => {
    if (!reporte) return null;

    const columnasMeses = reporte.ColumnasMeses || [];
    let rows = [];

    if (reporte.tipo === "Número de personas") {
      rows = [
        { label: "1-2 personas", values: reporte.personas1a2 },
        { label: "3-5 personas", values: reporte.personas3a5 },
        { label: "6-10 personas", values: reporte.personas6a10 },
        { label: "11-15 personas", values: reporte.personas11a15 },
        { label: "Total", values: reporte.totalesFilas },
      ];
    } else {
      rows = [
        { label: "Vueltas 10-10", values: reporte.vueltas1010 },
        { label: "Vueltas 15-15", values: reporte.vueltas1515 },
        { label: "Vueltas 20-20", values: reporte.vueltas2020 },
      ];
    }

    return (
      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Tipo</TableCell>
              {columnasMeses.map((mes, index) => (
                <TableCell key={index}>{mes}</TableCell>
              ))}
            </TableRow>
          </TableHead>
          <TableBody>
            {rows.map((row, rowIndex) => (
              <TableRow key={rowIndex}>
                <TableCell>{row.label}</TableCell>
                {row.values.map((value, colIndex) => (
                  <TableCell key={colIndex}>
                    {value !== null ? value : "N/A"}
                  </TableCell>
                ))}
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    );
  };

  return (
    <LocalizationProvider dateAdapter={AdapterDateFns} adapterLocale={es}>
      <Box sx={{ padding: 2 }}>
        <Typography variant="h4" gutterBottom>
          Generar Reporte
        </Typography>

        <form onSubmit={handleSubmit} style={{ marginBottom: "20px" }}>
          <TextField
            select
            label="Tipo de Reporte"
            value={tipo}
            onChange={(e) => setTipo(e.target.value)}
            fullWidth
            required
            sx={{ marginBottom: "20px" }}
          >
            <MenuItem value="Número de personas">Número de personas</MenuItem>
            <MenuItem value="Por cantidad de vueltas o tiempo máximo">
              Por cantidad de vueltas o tiempo máximo
            </MenuItem>
          </TextField>

          <DatePicker
            label="Fecha de Inicio"
            value={fechaInicio}
            onChange={(newValue) => setFechaInicio(newValue)}
            renderInput={(params) => (
              <TextField {...params} fullWidth sx={{ marginBottom: "20px" }} />
            )}
          />

          <DatePicker
            label="Fecha de Fin"
            value={fechaFin}
            onChange={(newValue) => setFechaFin(newValue)}
            renderInput={(params) => (
              <TextField {...params} fullWidth sx={{ marginBottom: "20px" }} />
            )}
          />

          <Button
            type="submit"
            variant="contained"
            color="primary"
            fullWidth={false}
            sx={{ display: 'block', margin: '0 auto', mt: 2 }}
          >
            Generar Reporte
          </Button>
        </form>

        {loading && <CircularProgress />}
        {error && <Typography color="error">{error}</Typography>}

        {reporte && (
          <>
            <Typography variant="body1">
              Fecha de Creación: {reporte.fechaCreacion}
            </Typography>
            <Typography variant="body1">
              Fecha de Inicio: {reporte.fechaInicio}
            </Typography>
            <Typography variant="body1">
              Fecha de Fin: {reporte.fechaFin}
            </Typography>

            {renderTable()}
          </>
        )}
      </Box>
    </LocalizationProvider>
  );
};

export default Reporte;
