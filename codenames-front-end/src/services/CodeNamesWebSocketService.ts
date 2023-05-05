import WebSocketRequest from "../models/CodeNames/WebSocketRequest";
import {IGameRoom} from "../models/CodeNames/IGameRoom";
import WebSocketResponse from "../models/CodeNames/WebSocketResponse";
import {Color} from "../models/CodeNames/Color";
import {WebSocketConfig} from "../config/RestConfig";


class CodeNamesWebSocketService {

    private readonly wsServerIp = WebSocketConfig.ip;

    private readonly wsServerPort = WebSocketConfig.port;

    private readonly pathsConfig = WebSocketConfig.paths;

    private socket: WebSocket;

    private isConnected: boolean = false;

    private readonly roomId: number;

    private readonly onNewRoomInfo: (newInfo: IGameRoom) => void;

    private readonly onConnect: () => void;

    private readonly onClose: () => void;

    constructor(
        roomID: number | undefined,
        onNewRoomInfo: (newInfo: IGameRoom) => void,
        onConnect: () => void,
        onClose: () => void,
    ) {
        this.socket = new WebSocket(WebSocketConfig.connectPath);
        this.roomId = roomID ? roomID: -1;

        this.onNewRoomInfo = onNewRoomInfo.bind(this);
        this.onConnect = onConnect.bind(this);
        this.onClose = onClose.bind(this);

        this.socket.onopen = this.onConnectEvent.bind(this);
        this.socket.onmessage = this.onMessageEvent.bind(this);
        this.socket.onclose = this.onCloseEvent.bind(this);
        this.socket.onerror = this.onErrorEvent.bind(this);
    }

    private sendSocketRequest(requestPath: string, requestBody: any = {}){
        this.socket.send(new WebSocketRequest(requestPath, requestBody).toJson())
    }

    private connectToRoom() {
        if (this.isConnected && this.roomId !== -1) {
            this.sendSocketRequest(this.pathsConfig.request.connect, this.roomId);
        } else {
            console.error("Something went wrong");
        }
    }

    private onConnectEvent(event: Event) {
        this.isConnected = true;
        this.onConnect();
        this.connectToRoom();
    }

    private onMessageEvent(message: MessageEvent) {
        const messageData: WebSocketResponse<IGameRoom> = JSON.parse(message.data)

        console.log(messageData.responsePath)

        if (messageData.responsePath === this.pathsConfig.response.newRoomInfo){
            this.onNewRoomInfo(messageData.responseBody)
        }
    }

    private onCloseEvent(event: Event) {
        console.log("Close")

        this.onClose();
    }

    private onErrorEvent(error: Event) {
        console.log(error)
    }

    public startGame(){
        this.sendSocketRequest(this.pathsConfig.request.startGame)
    }

    public stopGame(){
        this.sendSocketRequest(this.pathsConfig.request.stopGame)
    }

    public restartGame(){
        this.sendSocketRequest(this.pathsConfig.request.restartGame)
    }

    public joinToSpectator(){
        this.sendSocketRequest(this.pathsConfig.request.selectRole, 'SPECTATOR')
    }

    public selectMaster(team: Color.BLUE | Color.YELLOW){
        this.sendSocketRequest(this.pathsConfig.request.selectRole, `${team}_MASTER`)
    }

    public joinToTeam(team: Color.BLUE | Color.YELLOW){
        this.sendSocketRequest(this.pathsConfig.request.selectRole, `${team}_PLAYER`)
    }
}

export default CodeNamesWebSocketService;
