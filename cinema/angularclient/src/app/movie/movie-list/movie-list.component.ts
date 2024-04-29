import { Component } from '@angular/core';
import { Movie } from '../model/movie';
import { CinemaServiceService } from '../../cinema-service/cinema-service.service';
import { ActivatedRoute, RouterLink } from '@angular/router';

@Component({
  selector: 'app-movie-list',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './movie-list.component.html',
  styleUrl: './movie-list.component.css'
})
export class MovieListComponent {
  readonly movies: Movie[];

  constructor(private readonly activatedRoute: ActivatedRoute) {
    this.movies = this.activatedRoute.snapshot.data['movies'];
  }

}
