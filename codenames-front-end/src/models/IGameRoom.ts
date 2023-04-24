import {Status} from "./Status";
import {GameTurn} from "./GameTurn";
import {ITeam} from "./ITeam";
import {IUser} from "./IUser";
import {IWord} from "./IWord";


export interface IGameRoom{
    status: Status;
    gameTurn: GameTurn;
    blueTeam: ITeam;
    yellowTeam: ITeam;
    spectators: IUser[];
    words: IWord[];
    wordsCount: number;
    timer: number;
}