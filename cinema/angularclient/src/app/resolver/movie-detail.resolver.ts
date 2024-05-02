import {ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot} from '@angular/router';
import { CinemaServiceService } from '../cinema-service/cinema-service.service';
import { MovieSeance } from '../model/movie-seance';
import {inject} from '@angular/core';

export const movieDetailResolver: ResolveFn<MovieSeance[]> = (  
  route: ActivatedRouteSnapshot, 
  state: RouterStateSnapshot,
) => {
  return inject(CinemaServiceService).getMovieById(route.paramMap.get('id'));
};
