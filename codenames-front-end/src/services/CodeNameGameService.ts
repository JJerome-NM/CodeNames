import WebSocketRequest from "../models/WebSocketRequest";
import {IGameRoom} from "../models/IGameRoom";
import WebSocketResponse from "../models/WebSocketResponse";
import {Color} from "../models/Color";


class CodeNameGameService {

    private socket: WebSocket;

    private isConnected: boolean = false;

    private roomId: number;

    private onNewRoomInfo: (newInfo: IGameRoom) => void;

    private onConnect: () => void;

    constructor(
        onNewRoomInfo: (newInfo: IGameRoom) => void,
        onConnect: () => void
    ) {
        this.socket = new WebSocket("ws://localhost:8080/socket");
        this.roomId = -1;

        this.onNewRoomInfo = onNewRoomInfo.bind(this);
        this.onConnect = onConnect.bind(this);

        this.socket.onopen = this.onConnectEvent.bind(this);
        this.socket.onmessage = this.onMessageEvent.bind(this);
        this.socket.onclose = this.onCloseEvent.bind(this);
        this.socket.onerror = this.onErrorEvent.bind(this);
    }

    private onConnectEvent(event: Event) {
        this.isConnected = true;
        this.onConnect();

        console.log("connected")
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
    }

    private onErrorEvent(error: Event) {
        console.log(error)
    }

    private sendRequest(requestPath: string, requestBody: any){
        this.socket.send(new WebSocketRequest(requestPath, requestBody).toJson());
    }

    public createRoom(){
        console.log(`create room - ${this.isConnected}`)
        if (this.isConnected){
            this.sendRequest("/room/create", "");
        }
    }

    public startGame(){
        this.sendRequest("/room/admin/start", {})
    }

    public joinToSpectator(){
        this.sendRequest("/room/select/role", '')
    }

    public selectMaster(team: Color.BLUE | Color.YELLOW){
        this.sendRequest("/room/select/role", `${team}_MASTER`)
    }

    public joinToTeam(team: Color.BLUE | Color.YELLOW){
        this.sendRequest("/room/select/role", `${team}_PLAYER`)
    }

    public connectToRoom(){
        this.sendRequest("/room/connect", this.roomId)
    }
}

export default CodeNameGameService;
