import { TestBed } from '@angular/core/testing';
import { ResolveFn } from '@angular/router';

describe('movieDetailResolver', () => {
  const movieDetailResolver: ResolveFn<boolean> = (...resolverParameters) => 
      TestBed.runInInjectionContext(() => movieDetailResolver(...resolverParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(movieDetailResolver).toBeTruthy();
  });
});