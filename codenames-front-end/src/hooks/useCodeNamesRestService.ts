import axios, {AxiosResponse} from "axios";
import {RestConfig} from "../config/RestConfig";

export const useCodeNamesRestService = (): [
    () => Promise<AxiosResponse<number>>,
    (roomID: number) => Promise<AxiosResponse<number>>
] => {

    const restPaths = RestConfig.paths;

    const createRoom = async () => {
        return await axios.get<number>(restPaths.request.createRoom)
    }

    const tryConnectToRoom = async (roomID: number) => {
        return await axios.get<number>(restPaths.request.connectToRoom + `/${roomID ? roomID : "0"}`)
    }

    return [createRoom, tryConnectToRoom]
}


// roomID: number,
//     onSocketConnect?: (event: Event) => void,
//     onSocketMessage?: (message: MessageEvent) => void,
//     onSocketClose?: (event: Event) => void,
//     onSocketError?: (event: Event) => void,
// ) => {
//
//     const socketService = useRef<CodeNamesWebSocketService>(new CodeNamesWebSocketService(roomID))
//
//
//     return []
// }