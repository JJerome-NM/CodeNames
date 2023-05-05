import {MutableRefObject, useEffect, useRef, useState} from "react";
import {WebSocketConfig} from "../config/RestConfig";
import {Color} from "../models/CodeNames/Color";
import WebSocketRequest from "../models/CodeNames/WebSocketRequest";
import {IGameRoom} from "../models/CodeNames/IGameRoom";
import WebSocketResponse from "../models/CodeNames/WebSocketResponse";
import useFetching from "./useFetching";
import {useCodeNamesRestService} from "./useCodeNamesRestService";
import {useNavigate} from "react-router-dom";
import {notify} from "../models/notifications/Notifications";


export interface CodeNameWsRoomRequests {
    sendSocketRequest: (requestPath: string, requestBody: any) => void;
    startGame: () => void;
    stopGame: () => void;
    restartGame: () => void;
    joinToSpectator: () => void;
    selectMaster: (team: Color.BLUE | Color.YELLOW) => void;
    joinToTeam: (team: Color.BLUE | Color.YELLOW) => void;
}

const buildRequestMethods = (webSocket: WebSocket | undefined): CodeNameWsRoomRequests => {
    return {
        sendSocketRequest(requestPath: string, requestBody: any = {}): void {
            if (webSocket?.readyState === 1) {
                webSocket.send(new WebSocketRequest(requestPath, requestBody).toJson())
            } else {
                console.error("WebSocket session is not connected")
            }
        },
        joinToSpectator(): void {
            this.sendSocketRequest(WebSocketConfig.paths.request.selectRole, 'SPECTATOR')
        },
        joinToTeam(team: Color.BLUE | Color.YELLOW): void {
            this.sendSocketRequest(WebSocketConfig.paths.request.selectRole, `${team}_PLAYER`)
        },
        selectMaster(team: Color.BLUE | Color.YELLOW): void {
            this.sendSocketRequest(WebSocketConfig.paths.request.selectRole, `${team}_MASTER`)
        },
        startGame(): void {
            this.sendSocketRequest(WebSocketConfig.paths.request.startGame, {})
        },
        restartGame(): void {
            this.sendSocketRequest(WebSocketConfig.paths.request.restartGame, {})
        },
        stopGame(): void {
            this.sendSocketRequest(WebSocketConfig.paths.request.stopGame, {})
        }
    }
}


export const useCodeNamesWsRoomConnect = (
    roomID: number,
    onNewRoomInfo: (room: IGameRoom) => void = () => {
    },
    onSocketConnect: (event: Event) => void = () => {
    },
    onSocketMessage: (message: MessageEvent) => void = () => {
    },
    onSocketClose: (event: Event) => void = () => {
    },
    onSocketError: (event: Event) => void = () => {
    }
): [
    MutableRefObject<WebSocket | undefined>,
    CodeNameWsRoomRequests | undefined,
    boolean
] => {
    const navigate = useNavigate();
    const [createRoom, tryConnectToRoom] = useCodeNamesRestService();
    const [isConnected, setIsConnected] = useState<boolean>(false);
    const webSocket = useRef<WebSocket>();
    const webSocketRequests = useRef<CodeNameWsRoomRequests>();

    const [fetchConnectToRoom] = useFetching(async () => {
        try {
            const response = await tryConnectToRoom(roomID);
            if (response.data === -1) {
                navigate("/room/connect");
            }
        } catch (e) {
            notify.error("Something went wrong while connecting to the server, perhaps the server is down.")
            notify.default("Reconnecting to the server")

            await new Promise(resolve => setTimeout(resolve, 5000))
            return fetchConnectToRoom();
        }

        webSocket.current = new WebSocket(WebSocketConfig.connectPath)
        webSocketRequests.current = buildRequestMethods(webSocket.current)

        webSocket.current.onopen = (event: Event) => {
            setIsConnected(true);
            webSocketRequests.current?.sendSocketRequest(WebSocketConfig.paths.request.connect, roomID);

            onSocketConnect(event)
        }

        webSocket.current.onmessage = (message: MessageEvent) => {
            const messageData: WebSocketResponse<IGameRoom> = JSON.parse(message.data)

            if (messageData.responsePath === WebSocketConfig.paths.response.newRoomInfo) {
                onNewRoomInfo(messageData.responseBody)
            }
            onSocketMessage(message)
        }

        webSocket.current.onclose = onSocketClose.bind(this);
        webSocket.current.onerror = async (event: Event) => {
            onSocketError(event)

            await new Promise(resolve => setTimeout(resolve, 1000))
            fetchConnectToRoom()
        }
    })

    useEffect(() => {
        fetchConnectToRoom()
    }, [])

    return [webSocket, webSocketRequests.current, isConnected]
}