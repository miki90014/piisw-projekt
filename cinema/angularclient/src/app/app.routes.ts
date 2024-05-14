import { Routes } from '@angular/router';
import { MovieListComponent } from './movie/movie-list/movie-list.component';
import { movieListResolverResolver } from './resolver/movie-list-resolver.resolver';
import { movieDetailResolver} from './resolver/movie-detail.resolver';
import { MovieDetailComponent } from './movie/movie-detail/movie-detail.component';
import { SeanceAvailableSeatsComponent } from './reserve-seats/seance-available-seats/seance-available-seats.component';
import { seanceAvailableSeatsResolver } from './resolver/seance-available-seats.resolver';
import { ReservationDetailComponent } from './reservation-detail/reservation-detail.component';
import { reservationDetailResolver} from './resolver/reservation-detail.resolver';
import { LoginComponent } from './login/login.component';
import { CinemaAttendantComponent } from './cinema-attendant/cinema-attendant.component';
import { authGuardGuard } from './auth/auth-guard.guard';
import { cinemaAttendantResolver } from './resolver/cinema-attendant.resolver';

export const routes: Routes = [
    {
        path: '',
        pathMatch: 'full',
        redirectTo: '/movies'
      },
      {
        path: 'movies',
        component: MovieListComponent,
        resolve: {
          movies: movieListResolverResolver
        }
      },
      {
        path: 'movies/:id',
        component: MovieDetailComponent,
        resolve: {
          seances: movieDetailResolver
        }
      },
      {
        path: 'available_seats/:id',
        component: SeanceAvailableSeatsComponent,
        resolve: {
          availableSeats: seanceAvailableSeatsResolver
        }
      },
      {
        path: 'reservations/:id',
        component: ReservationDetailComponent,
        resolve: {
          reservation: reservationDetailResolver
        }
      },
      {
        path: 'login',
        component: LoginComponent
      },
      {
        path: 'cinema-attendant',
        component: CinemaAttendantComponent,
        canActivate: [authGuardGuard],
        resolve: {
          reservation: cinemaAttendantResolver
        }
      }

];
