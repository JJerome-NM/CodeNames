import {Color} from "./Color";
import {IUser} from "./IUser";


export interface ITeam{
    color: Color;
    score: number;
    master: IUser;
    players: IUser[];
    message: string[];
}