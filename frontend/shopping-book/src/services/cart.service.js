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



export const performRemoveToCart = async (cart) => {
    try {
        const response = await axiosConfig.delete("/cart/" + cart.sessionToken,  { data: cart.item });
        return response.data;
    } catch (error) {
        console.error(error)
        return error.message;
    }
}

export const getCart = async (sessionToken) => {
    try {
        const response = await axiosConfig.get("/cart/" + sessionToken);
        return response.data;
    } catch (error) {
        console.error(error)
        return error.message;
    }
}