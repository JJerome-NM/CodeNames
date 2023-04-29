
export class CodeNamesRestUrlConfig{
    private readonly serverAddress: string;
    private readonly port: number;

    public readonly createRoomUrl: string;
    public readonly connectToRoomUrl: string;

    constructor(serverAddress: string, port: number) {
        this.serverAddress = serverAddress;
        this.port = port;

        this.createRoomUrl = `http://${this.serverAddress}:${this.port}/room/create`;
        this.connectToRoomUrl = `http://${this.serverAddress}:${this.port}/room/connect`;
    }



}
