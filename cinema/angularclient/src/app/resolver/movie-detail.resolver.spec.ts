import { TestBed } from '@angular/core/testing';
import { ResolveFn } from '@angular/router';

import { movieDetailResolver } from './movie-detail.resolver';
import { Movie } from '../movie/model/movie';

describe('movieDetailResolver', () => {
  const executeResolver: ResolveFn<Movie> = (...resolverParameters) => 
      TestBed.runInInjectionContext(() => movieDetailResolver(...resolverParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeResolver).toBeTruthy();
  });
});
