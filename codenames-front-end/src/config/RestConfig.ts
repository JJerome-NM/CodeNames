import {getAuthToken} from "../helper";

const restIP = "localhost";
const restPort = 8080;

const wsIP = "localhost"
const wsPort = 8080

export const RestConfig = {
    ip: restIP,
    port: restPort,
    paths: {
        response: {},
        request: {
            createRoom: `http://${restIP}:${restPort}/room/create`,
            connectToRoom: `http://${restIP}:${restPort}/room/connect`,
            userIsAuth: `http://${restIP}:${restPort}/check_user_is_auth`,
            signIn: `http://${restIP}:${restPort}/login`,
            signUp: `http://${restIP}:${restPort}/register`
        }
    }
}

export const WebSocketConfig = {
    ip: wsIP,
    port: wsPort,
    connectPath: `ws://${wsIP}:${wsPort}/socket?auth_token=${getAuthToken()}`,
    paths: {
        response: {
            newRoomInfo: "/room/new/info"
        },
        request: {
            connect: "/room/connect",
            startGame: "/room/admin/start",
            stopGame: "/room/admin/stop",
            pauseGame: "/room/admin/pause",
            restartGame: "/room/admin/restart",
            selectRole: "/room/select/role"
        }
    }
}