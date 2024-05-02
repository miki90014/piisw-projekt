export class Movie {
    id: number;
    movieName: string;
    movieDescription: string;
    movieRunningTime: string;
    movieThumbnailUrl: string;

    
    constructor() {
        this.id = 0
        this.movieName = "";
        this.movieDescription ="";
        this.movieRunningTime = "";
        this.movieThumbnailUrl= "";
    }
}
