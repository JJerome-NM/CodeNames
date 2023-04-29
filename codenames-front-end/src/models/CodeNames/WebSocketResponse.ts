


class WebSocketResponse<T>{
    responsePath: string;
    responseBody: T;

    constructor(responsePath: string, responseBody: T ) {
        this.responsePath = responsePath;
        this.responseBody = responseBody;
    }

    public toJson(): string{
        return JSON.stringify(this)
    }
}

export default WebSocketResponse
