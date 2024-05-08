import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Movie } from '../model/movie';
import { MovieSeance } from '../model/movie-seance';
import { Observable } from 'rxjs';
import { AvailableSeats } from '../model/available-seats';
import { Reservation } from '../model/reservation';

@Injectable({
  providedIn: 'root'
})

export class CinemaServiceService {

  private movieUrl: string;
  private availableSeatsUrl: string;
  private reservationUrl:string;

  constructor(private http: HttpClient) {
    this.movieUrl = 'http://localhost:8080/movies';
    this.availableSeatsUrl = 'http://localhost:8080/available_seats'
    this.reservationUrl = 'http://localhost:8080/reservations';
  }

  public findAllMovies(): Observable<Movie[]> {
    return this.http.get<Movie[]>(this.movieUrl);
  }

  public getMovieById(id: any): Observable<MovieSeance[]> {
    return this.http.get<MovieSeance[]>(this.movieUrl + "/" + id + "/seances");
  }

  public getEmptyAvailbleSeatsForSeance(id: any): Observable<AvailableSeats[]> {
    return this.http.get<AvailableSeats[]>(this.availableSeatsUrl + "/seance/" + id);
  }

  public getReservationById(id: any): Observable<Reservation> {
    return this.http.get<Reservation>(this.reservationUrl + "/" + id );
  }

  public saveReservation(reservation: Reservation): Observable<number>{
    return this.http.post<number>(this.reservationUrl, reservation);
  }

}
