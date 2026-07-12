import axios from "axios";

const API = "http://localhost:8080/api/investors";

export const getInvestor = async (id) => {
    const response = await axios.get(`${API}/${id}`);
    return response.data;
};