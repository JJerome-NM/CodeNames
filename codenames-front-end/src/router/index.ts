import {ComponentType} from "react";
import {Connect} from "../pages";
import GameRoom from "../pages/GameRoom/GameRoom";
import SingUp from "../pages/Authentication/SignUp/SingUp";
import SingIn from "../pages/Authentication/SignIn/SingIn";

export const publicRouters: {path: string, component: ComponentType}[] = [
    { path: '/sign_up', component: SingUp},
    { path: '/sign_in', component: SingIn}
]

export const privateRouters: {path: string, component: ComponentType}[] = [
    { path: '/room', component: Connect },
    { path: '/room/:id', component: GameRoom },
]