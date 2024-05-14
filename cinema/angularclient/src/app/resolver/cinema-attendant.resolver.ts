import { ResolveFn } from '@angular/router';
import { CinemaServiceService } from '../cinema-service/cinema-service.service';
import { Reservation } from '../model/reservation';
import {inject} from '@angular/core';

export const cinemaAttendantResolver: ResolveFn<Reservation> = (route, state) => {
  return inject(CinemaServiceService).checkTicket(route.queryParamMap.get('ticket'));
};
