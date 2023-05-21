import {ComponentType} from "react";
import {Connect} from "../pages";
import GameRoom from "../pages/GameRoom/GameRoom";
import SignUp from "../pages/Authentication/SignUp/SignUp";
import SignIn from "../pages/Authentication/SignIn/SignIn";

export const publicRouters: {path: string, component: ComponentType}[] = [
    { path: '/sign_up', component: SignUp},
    { path: '/sign_in', component: SignIn}
]

export const privateRouters: {path: string, component: ComponentType}[] = [
    { path: '/room', component: Connect },
    { path: '/room/:id', component: GameRoom },
]