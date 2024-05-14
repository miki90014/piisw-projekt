import { Component } from '@angular/core';
import { CommonModule, NgForOf } from '@angular/common'; 
import { Reservation } from '../model/reservation';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, RouterLink, Router } from '@angular/router';
import { CinemaServiceService } from '../cinema-service/cinema-service.service';

@Component({
  selector: 'app-cinema-attendant',
  standalone: true,
  imports: [RouterLink, FormsModule, CommonModule],
  templateUrl: './cinema-attendant.component.html',
  styleUrl: './cinema-attendant.component.css'
})
export class CinemaAttendantComponent {
  
  ticketInput: string;
  reservation: Reservation | undefined;

  constructor(private router: Router, private readonly activatedRoute: ActivatedRoute, private readonly cinemaService: CinemaServiceService) {
    this.ticketInput = ''
  }

  checkTicket() {
    if(this.ticketInput === "") {
      alert("You mast enter ticket number to check")
    } else {
      this.cinemaService.checkTicket(this.ticketInput).subscribe({
        next: (response: Reservation) => {
          this.reservation = response
        },
      });
    }
  }
  
}
