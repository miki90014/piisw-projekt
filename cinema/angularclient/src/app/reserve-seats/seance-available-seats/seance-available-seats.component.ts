import { Component } from '@angular/core';
import { ActivatedRoute, RouterLink, Router } from '@angular/router';
import { CommonModule, NgForOf } from '@angular/common'; 
import { AvailableSeats } from '../../model/available-seats';
import { Reservation } from '../../model/reservation';
import { CinemaServiceService } from '../../cinema-service/cinema-service.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-seance-available-seats',
  standalone: true,
  imports: [RouterLink, FormsModule, CommonModule, NgForOf],
  templateUrl: './seance-available-seats.component.html',
  styleUrl: './seance-available-seats.component.css'
})
export class SeanceAvailableSeatsComponent {
  convert(dateString: string) {
    const date = new Date(dateString);
    const day = date.getDate();
    const month = date.getMonth() + 1;
    const year = date.getFullYear().toString().substr(-2);
    const hours = date.getHours();
    const minutes = date.getMinutes();
    const formattedDay = day < 10 ? '0' + day : day;
    const formattedMonth = month < 10 ? '0' + month : month;
    const formattedHours = hours < 10 ? '0' + hours : hours;
    const formattedMinutes = minutes < 10 ? '0' + minutes : minutes;
    const formattedDate = `${formattedDay}.${formattedMonth}.${year} ${formattedHours}:${formattedMinutes}`;
  
    return formattedDate
  }
    readonly availableSeats: AvailableSeats[];
    readonly reservation: Reservation;
  
    constructor(private router: Router, private readonly activatedRoute: ActivatedRoute, private readonly cinemaService: CinemaServiceService) {
      this.availableSeats = this.activatedRoute.snapshot.data['availableSeats'];
      this.reservation = {
      id: 0,
      totalPrice: 0,
      personData: "",
      reservedSeats: []    
    }
  }
  ngOnInit() {
    this.calculateTotalPrice();  // Oblicz totalPrice przy inicjalizacji komponentu
  }
  calculateTotalPrice() {
    const ticketPrice = 20.50;
    const randomTicketCount = Math.floor(Math.random() * 10) + 1; // Losowa liczba biletów od 1 do 10
    this.reservation.totalPrice = randomTicketCount * ticketPrice;
  }

  onSubmit(): void {
    this.cinemaService.saveReservation(this.reservation).subscribe({
      next: () => {
        this.router.navigate([`/reservations/${this.reservation.id}`]);
      }
    });
  }
  }
  