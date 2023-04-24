import WebSocketRequest from "../models/WebSocketRequest";


class CodeNameGameService {

    private socket: WebSocket;

    private isConnected: boolean = false;

    private roomId: number;

    constructor(roomID?: number) {
        this.socket = new WebSocket("ws://localhost:8080/socket");
        this.roomId = roomID ? roomID : -1;

        this.socket.onopen = this.onConnect.bind(this);
        this.socket.onmessage = this.onMessage.bind(this);
        this.socket.onclose = this.onClose.bind(this);
        this.socket.onerror = this.onError.bind(this);
    }

    private onConnect(event: Event) {
        this.isConnected = true;

        console.log("connected")
    }

    private onMessage(message: MessageEvent) {
        console.log(JSON.parse(message.data))
    }

    private onClose(event: Event) {
        console.log("Close")
    }

    private onError(error: Event) {
        console.log(error)
    }

    public createRoom(){
        console.log(`create room - ${this.isConnected}`)
        if (this.isConnected){
            this.socket.send(new WebSocketRequest("/room/create", "").toJson());
        }
    }

    public connectToRoom(){
        this.socket.send(new WebSocketRequest("/room/connect", this.roomId).toJson())
    }
}

export default CodeNameGameService;
