import { TestBed } from '@angular/core/testing';
import { ResolveFn } from '@angular/router';
import { CinemaServiceService } from '../cinema-service/cinema-service.service';
import { movieListResolverResolver } from './movie-list-resolver.resolver';

describe('movieListResolverResolver', () => {
  const movieListResolverResolver: ResolveFn<boolean> = (...resolverParameters) => 
      TestBed.runInInjectionContext(() => movieListResolverResolver(...resolverParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(movieListResolverResolver).toBeTruthy();
  });
});
