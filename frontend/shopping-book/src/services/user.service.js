import { v4 as uuidv4 } from 'uuid';

export const getSessionToken = () => {
    const token = localStorage.getItem("token");
    if (token) {
        return token;
    } else {
        const uuid = uuidv4();
        localStorage.setItem("token", uuid);
        return uuid;
    }

}