import axiosConfig from "../config/axios.config";

export const performAddToCart = async (cart) => {
    try {
        const response = await axiosConfig.post("/cart", cart);
        return response.data;
    } catch (error) {
        console.error(error)
        return error.message;
    }
}