import { Room } from "./room";

export class Seat {
    id: number;
    seatRow: string;
    number: number;
    room: Room;

    constructor() {
        this.id = 0
        this.seatRow = "";
        this.number = 0;
        this.room= new Room();
    }
}
