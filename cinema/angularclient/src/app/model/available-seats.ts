import { MovieSeance } from "./movie-seance";
import { Seat } from "./seat";

export class AvailableSeats {
    id: number;
    seance: MovieSeance;
    seat: Seat;
    seatStatus: string;
    ticket: string;
    price: number;
    selected: boolean; // Add this line

    constructor() {
        this.id = 0;
        this.seat = new Seat();
        this.seance = new MovieSeance();
        this.seatStatus = "";
        this.ticket= "";
        this.price= 0;
        this.selected = false;
}
}
