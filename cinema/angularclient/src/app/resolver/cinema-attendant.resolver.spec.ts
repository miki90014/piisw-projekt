import { TestBed } from '@angular/core/testing';
import { ResolveFn } from '@angular/router';

import { cinemaAttendantResolver } from './cinema-attendant.resolver';

describe('cinemaAttendantResolver', () => {
  const executeResolver: ResolveFn<boolean> = (...resolverParameters) => 
      TestBed.runInInjectionContext(() => cinemaAttendantResolver(...resolverParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeResolver).toBeTruthy();
  });
});
