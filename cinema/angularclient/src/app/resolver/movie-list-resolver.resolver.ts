import {inject} from '@angular/core';
import {ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot} from '@angular/router';
import { CinemaServiceService } from '../cinema-service/cinema-service.service';
import {Movie} from '../movie/model/movie';


export const movieListResolverResolver: ResolveFn<Movie[]> = (
  route: ActivatedRouteSnapshot, 
  state: RouterStateSnapshot,
) => {
  return inject(CinemaServiceService).findAllMovies();
};