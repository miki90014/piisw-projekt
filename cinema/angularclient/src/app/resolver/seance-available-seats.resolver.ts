import { ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot } from '@angular/router';
import { CinemaServiceService } from '../cinema-service/cinema-service.service';
import {inject} from '@angular/core';
import { AvailableSeats } from '../model/available-seats';

export const seanceAvailableSeatsResolver: ResolveFn<AvailableSeats[]> = (  
  route: ActivatedRouteSnapshot, 
  state: RouterStateSnapshot,
) => {
  return inject(CinemaServiceService).getEmptyAvailbleSeatsForSeance(route.paramMap.get('id'));
};
