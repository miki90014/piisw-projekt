import { Bytes } from "node-forge";
import { AvailableSeats } from "./available-seats";

export class Reservation {
    id: string;
    totalPrice: number;
    personData: string;
    reservedSeats: AvailableSeats[]; 
    ticket: string;

    
    constructor() {
        this.id = ""
        this.totalPrice = 0 ;
        this.personData ="";
        this.reservedSeats = [];
        this.ticket = "";

    }
}
