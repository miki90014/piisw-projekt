import { ResolveFn } from '@angular/router';
import { CinemaServiceService } from '../cinema-service/cinema-service.service';
import { Movie } from '../movie/model/movie';
import {inject} from '@angular/core';

export const movieDetailResolver: ResolveFn<Movie> = (route, state) => {
  return inject(CinemaServiceService).getMovieById(route.paramMap.get('id'));
};
