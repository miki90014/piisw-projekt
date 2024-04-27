import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Movie } from '../movie/model/movie';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class CinemaServiceService {

  private movieUrl: string;

  constructor(private http: HttpClient) {
    this.movieUrl = 'http://localhost:8080/movies';
  }

  public findAllMovies(): Observable<Movie[]> {
    return this.http.get<Movie[]>(this.movieUrl);
  }

}
