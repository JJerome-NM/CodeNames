import axios, {AxiosResponse} from "axios";
import {RestConfig} from "../config/RestConfig";


export class CodeNamesRestService {

    private static readonly REST_PATHS = RestConfig.paths;

    public static async createRoom(): Promise<AxiosResponse<number>> {
        return await axios.get<number>(this.REST_PATHS.request.createRoom)
    }

    public static async tryConnectToRoom(roomID?: number): Promise<AxiosResponse<number>>{
        return await axios.get<number>(this.REST_PATHS.request.connectToRoom + `/${roomID ? roomID : "0"}`)
    }
}
