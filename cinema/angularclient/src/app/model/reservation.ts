import { AvailableSeats } from "./available-seats";

export class Reservation {
    id: number;
    totalPrice: number;
    personData: string;
    reservedSeats: AvailableSeats[]; 

    
    constructor() {
        this.id = 0
        this.totalPrice = 0 ;
        this.personData ="";
        this.reservedSeats = []; 

    }
}
