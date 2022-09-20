import axiosConfig from "../config/axios.config";

export const getAllBooks = async () => {
    try {
        const response = await axiosConfig.get("/books");
        console.log(response);
        return response.data;
    } catch (error) {
        console.error(error)
        return error.message;
    }
}