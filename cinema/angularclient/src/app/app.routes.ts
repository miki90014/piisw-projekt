import { Routes } from '@angular/router';
import { MovieListComponent } from './movie/movie-list/movie-list.component';
import { movieListResolverResolver } from './resolver/movie-list-resolver.resolver';

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
      }
];
