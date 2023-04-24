import Connect from "../../pages/connect/Connect";
import GameRoom from "../../pages/GameRoom/GameRoom";


export const publicRouters = [
    { path: '/room/connect', component: <Connect/> },
    // { path: '/room/connect/:id', component: <GameRoom/> },
    { path: '/room/:id', component: <GameRoom/> }
]

export const privateRouters = [

]