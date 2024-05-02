import { TestBed } from '@angular/core/testing';
import { ResolveFn } from '@angular/router';

describe('seanceAvailableSeatsResolver', () => {
  const seanceAvailableSeatsResolver: ResolveFn<boolean> = (...resolverParameters) => 
      TestBed.runInInjectionContext(() => seanceAvailableSeatsResolver(...resolverParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(seanceAvailableSeatsResolver).toBeTruthy();
  });
});
