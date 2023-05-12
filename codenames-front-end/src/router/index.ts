import {ComponentType} from "react";
import {Connect} from "../pages";
import SingUp from "../pages/SignUp/SingUp";
import SingIn from "../pages/SingIn/SingIn";
import GameRoom from "../pages/GameRoom/GameRoom";

export const publicRouters: {path: string, component: ComponentType}[] = [
    { path: '/room/Connect', component: Connect },
    { path: '/room/:id', component: GameRoom },
    { path: '/SignUp', component: SingUp},
    { path: '/SignIn', component: SingIn}
]

export const privateRouters: {path: string, component: ComponentType}[] = []