import './App.css'
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import Navbar from "./components/NavBar"
import Home from './components/Home';
import GestionTarifas from './components/GestionTarifas';
import NotFound from './components/NotFound';
import ModificarTarifa from './components/ModificarTarifa';
import CrearTarifa from './components/CrearTarifa';
import CrearTarifaEspecial from './components/CrearTarifaEspecial';
import ModificarTarifaEspecial from './components/ModificarTarifaEspecial';
import Karts from './components/Karts';
import RegistroUsuario from './components/RegistroUsuario';
import Login from './components/Login';
import Reporte from './components/Reporte';
import RackSemanal from './components/RackSemanal';
import Reserva from './components/Reserva';
import TarifasUsuario from './components/TarifasUsuario';
import CrearKart from './components/CrearKart';
import { FechaProvider } from "./components/FechaContext";
import { TarifaProvider } from "./components/TarifaContext";
import Calendario from './components/Calendario';
import EleccionDeTarifa from './components/EleccionDeTarifa';

function App() {
  return (
    <FechaProvider>
      <TarifaProvider>
      <Router>
        <div className="container">
          <Navbar />
          <Routes>
            <Route path="/home" element={<Home />} />
            <Route path="/tarifas" element={<GestionTarifas />} />
            <Route path="/crear-tarifa" element={<CrearTarifa />} />
            <Route path="/crear-tarifa-especial" element={<CrearTarifaEspecial />} />
            <Route path="/modificar-tarifa/:id" element={<ModificarTarifa />} />
            <Route path="/modificar-tarifa-especial/:id" element={<ModificarTarifaEspecial />} />
            <Route path="*" element={<NotFound />} />
            <Route path="/karts" element={<Karts />} />
            <Route path="/registro-usuario" element={<RegistroUsuario />} />
            <Route path="/login" element={<Login />} />
            <Route path="/reporte" element={<Reporte />} />
            <Route path="/rack-semanal" element={<RackSemanal />} />
            <Route path="/reserva" element={<Reserva />} />
            <Route path="/tarifas-cliente" element={<TarifasUsuario />} />
            <Route path="/nuevo-Kart" element={<CrearKart />} />
            <Route path="/calendario" element={<Calendario />} />
            <Route path="/eleccion-tarifa" element={<EleccionDeTarifa />} />
          </Routes>
        </div>
      </Router>
      </TarifaProvider>
    </FechaProvider>
  );
}

export default App
