


class WebSocketRequest<T>{
    private requestPath: string;
    private requestBody: T;

    constructor(path: string, body: T ) {
        this.requestPath = path;
        this.requestBody = body;
    }

    public toJson(): string{
        return JSON.stringify(this)
    }
}

export default WebSocketRequest;