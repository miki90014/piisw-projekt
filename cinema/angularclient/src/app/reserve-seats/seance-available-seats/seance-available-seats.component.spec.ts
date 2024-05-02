import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SeanceAvailableSeatsComponent } from './seance-available-seats.component';

describe('SeanceAvailableSeatsComponent', () => {
  let component: SeanceAvailableSeatsComponent;
  let fixture: ComponentFixture<SeanceAvailableSeatsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SeanceAvailableSeatsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SeanceAvailableSeatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
