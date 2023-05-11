import {Color} from "./Color";
import {IUser} from "./IUser";


export interface ITeam{
    color: Color.BLUE | Color.YELLOW;
    score: number;
    master: IUser;
    players: IUser[];
    message: string[];
}