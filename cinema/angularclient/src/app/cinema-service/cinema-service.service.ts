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
  private loggedIn: boolean

  constructor(private http: HttpClient) {
    this.movieUrl = 'http://localhost:8080/movies';
    this.availableSeatsUrl = 'http://localhost:8080/available_seats'
    this.reservationUrl = 'http://localhost:8080/reservations';
    this.loggedIn = false;
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

  public login(username: string, password: string): boolean {
    // In a real-world scenario, you would make an HTTP request to your backend
    // to authenticate the user. For simplicity, let's just simulate a successful login.
    if (username === 'example' && password === 'password') {
      this.loggedIn = true;
      return true;
    }
    return false;
  }

  // Method to simulate user logout
  public logout(): void {
    // In a real-world scenario, you might need to clear user session data
    this.loggedIn = false;
  }

  // Method to check if the user is logged in
  public isLoggedIn(): boolean {
    return this.loggedIn;
  }

}
