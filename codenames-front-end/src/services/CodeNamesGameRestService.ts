import {CodeNamesRestUrlConfig} from "./config/CodeNamesRestUrlConfig";
import axios, {AxiosResponse} from "axios";


export class CodeNamesGameRestService {

    private static readonly REST_URL_CONFIG: CodeNamesRestUrlConfig = new CodeNamesRestUrlConfig("localhost", 8080);

    public static async createRoom(): Promise<AxiosResponse<number>> {
        return await axios.get<number>(this.REST_URL_CONFIG.createRoomUrl)
    }

    public static async tryConnectToRoom(roomID?: number): Promise<AxiosResponse<number>>{
        return await axios.get<number>(this.REST_URL_CONFIG.connectToRoomUrl + `/${roomID ? roomID : "0"}`)
    }
}
