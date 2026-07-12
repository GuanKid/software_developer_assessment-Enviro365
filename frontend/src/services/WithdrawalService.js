import axios from "axios";

const API = "http://localhost:8080/api/withdrawals";

export const withdraw = async (request) => {
    const response = await axios.post(API, request);
    return response.data;
};

export const getWithdrawals = async () => {
    const response = await axios.get(API);
    return response.data;
};

export const exportCsv = () => {
    window.open(`${API}/export`);
};