import httpClient from "../http-common";

const RACKSEMANAL_API_URL = "/rackSemanal";

const obtenerRackSemanal = () => {
    return httpClient.get(`${RACKSEMANAL_API_URL}/`);
    }

export default {
  obtenerRackSemanal,
};