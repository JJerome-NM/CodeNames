import WebSocketRequest from "../models/CodeNames/WebSocketRequest";
import {IGameRoom} from "../models/CodeNames/IGameRoom";
import WebSocketResponse from "../models/CodeNames/WebSocketResponse";
import {Color} from "../models/CodeNames/Color";


class CodeNameGameWebSocketService {

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
        this.socket = new WebSocket("ws://26.193.49.236:8080/socket");
        console.log(2323)
        roomID ? this.roomId = roomID: this.roomId = -1;

        this.onNewRoomInfo = onNewRoomInfo.bind(this);
        this.onConnect = onConnect.bind(this);
        this.onClose = onClose.bind(this);

        this.socket.onopen = this.onConnectEvent.bind(this);
        this.socket.onmessage = this.onMessageEvent.bind(this);
        this.socket.onclose = this.onCloseEvent.bind(this);
        this.socket.onerror = this.onErrorEvent.bind(this);
    }

    private sendSocketRequest(requestPath: string, requestBody: any){
        this.socket.send(new WebSocketRequest(requestPath, requestBody).toJson())
    }

    private connectToRoom() {
        if (this.isConnected && this.roomId !== -1) {
            this.sendSocketRequest("/room/connect", this.roomId);
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

        if (messageData.responsePath === "/GameRoom/new/info"){
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
        this.sendSocketRequest("/room/admin/start", {})
    }

    public stopGame(){
        this.sendSocketRequest("/room/admin/stop", {})
    }

    public restartGame(){
        this.sendSocketRequest("/room/admin/restart", {})
    }

    public joinToSpectator(){
        this.sendSocketRequest("/room/select/role", 'SPECTATOR')
    }

    public selectMaster(team: Color.BLUE | Color.YELLOW){
        this.sendSocketRequest("/room/select/role", `${team}_MASTER`)
    }

    public joinToTeam(team: Color.BLUE | Color.YELLOW){
        this.sendSocketRequest("/room/select/role", `${team}_PLAYER`)
    }
}

export default CodeNameGameWebSocketService;
