import { Movie } from './movie';
import { Room } from './room';

export class MovieSeance {
    id: number;
    movie: Movie
    room: Room
    dateOfSeance: string;

    
    constructor() {
        this.id = 0
        this.movie = new Movie();
        this.room = new Room();
        this.dateOfSeance= "";
    }
}