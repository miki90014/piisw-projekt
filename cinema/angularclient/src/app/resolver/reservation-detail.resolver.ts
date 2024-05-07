import {ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot} from '@angular/router';
import { CinemaServiceService } from '../cinema-service/cinema-service.service';
import { Reservation } from '../model/reservation';
import {inject} from '@angular/core';

export const reservationDetailResolver: ResolveFn<Reservation[]> = (  
  route: ActivatedRouteSnapshot, 
  state: RouterStateSnapshot,
) => {
  return inject(CinemaServiceService).getReservationById(route.paramMap.get('id'));
};
