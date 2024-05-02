import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Movie } from '../model/movie';
import { MovieSeance } from '../model/movie-seance';
import { Observable } from 'rxjs';
import { AvailableSeats } from '../model/available-seats';

@Injectable({
  providedIn: 'root'
})

export class CinemaServiceService {

  private movieUrl: string;
  private availableSeatsUrl: string;

  constructor(private http: HttpClient) {
    this.movieUrl = 'http://localhost:8080/movies';
    this.availableSeatsUrl = 'http://localhost:8080/available_seats'
  }

  public findAllMovies(): Observable<Movie[]> {
    return this.http.get<Movie[]>(this.movieUrl);
  }

  public getMovieById(id: any): Observable<MovieSeance[]> {
    return this.http.get<MovieSeance[]>(this.movieUrl + "/" + id + "/seances");
  }

  public getEmptyAvailbleSeatsForSeance(id: any): Observable<AvailableSeats[]> {
    return this.http.get<AvailableSeats[]>(this.availableSeatsUrl + "/seance/" + id + "/empty");
  }

}
