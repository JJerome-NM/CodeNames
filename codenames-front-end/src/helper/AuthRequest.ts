import axios, {Method} from "axios";


export const getAuthToken = (): string | null => {
    return window.localStorage.getItem("auth_token")
}

export const removeAuthToken = (): void => {
    window.localStorage.removeItem("auth_token")
}

export const setAuthToken = (jwtToken: string): void => {
    window.localStorage.setItem("auth_token", jwtToken)
}
export const authRequest = async (method: Method, url: string, data: any) => {
    let headers = {}

    if (getAuthToken() != null){
        headers = {"Authorization": `Bearer ${getAuthToken()}`}
    }

    return axios({
        method: method,
        headers: headers,
        url: url,
        data: data
    })
}