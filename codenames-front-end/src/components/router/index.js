import {Connect} from "@pages/connect";
import GameRoom from "@pages/GameRoom/GameRoom";
import SingUp from "@pages/SignUp/SingUp";
import SingIn from "@pages/SingIn/SingIn";


export const publicRouters = [
    { path: '/room/connect', component: <Connect/> },
    { path: '/room/:id', component: <GameRoom/> },
    { path: '/SignUp', component: <SingUp/>},
    { path: '/SignIn', component: <SingIn/>}
]

export const privateRouters = [

]