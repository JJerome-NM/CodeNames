import axios from "axios";

export default class RoomService{

    static async getRoomByID(roomID: string){
        return await axios.get(`http://localhost:8080/room/connect/${(roomID)}`);
    }
}