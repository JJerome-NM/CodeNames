import {AxiosResponse} from "axios";
import {RestConfig} from "../config";
import {authRequest} from "../helper";

export const useCodeNamesRestRequests = (): [
    () => Promise<AxiosResponse<number>>,
    (roomID: number) => Promise<AxiosResponse<number>>
] => {

    const restPaths = RestConfig.paths;

    const createRoom = async () => {
        return await authRequest("GET", restPaths.request.createRoom, {})
    }

    const tryConnectToRoom = async (roomID: number) => {
        return await authRequest("GET", restPaths.request.connectToRoom + `/${roomID ? roomID : "0"}`, {})
    }

    return [createRoom, tryConnectToRoom]
}
