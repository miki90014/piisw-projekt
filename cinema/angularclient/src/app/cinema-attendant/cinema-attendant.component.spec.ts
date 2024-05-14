import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CinemaAttendantComponent } from './cinema-attendant.component';

describe('CinemaAttendantComponent', () => {
  let component: CinemaAttendantComponent;
  let fixture: ComponentFixture<CinemaAttendantComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CinemaAttendantComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CinemaAttendantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
