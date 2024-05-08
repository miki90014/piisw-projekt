import { MovieSeance } from "./movie-seance";
import { Seat } from "./seat";

export class AvailableSeats {
    id: number;
    seance: MovieSeance;
    seat: Seat;
    seatStatus: string;
    price: number;
    selected: boolean;

    constructor() {
        this.id = 0;
        this.seat = new Seat();
        this.seance = new MovieSeance();
        this.seatStatus = "";
        this.price= 0;
        this.selected = false;
    }
}
