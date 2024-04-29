import { Routes } from '@angular/router';
import { MovieListComponent } from './movie/movie-list/movie-list.component';
import { movieListResolverResolver } from './resolver/movie-list-resolver.resolver';
import { movieDetailResolver} from './resolver/movie-detail.resolver';
import { MovieDetailComponent } from './movie/movie-detail/movie-detail.component';

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
          movie: movieDetailResolver
        }
      }
];
