import { AvailableSeats } from "./available-seats";

export class SeatFrontEnd {
    seatStatus: string;
    price: number;
    seat_id: number;
    seatObject: AvailableSeats;
  
    constructor() {
      this.seatStatus= '';
      this.price = 0 ;
      this.seat_id = 0;
      this.seatObject = new AvailableSeats;
    }
}
