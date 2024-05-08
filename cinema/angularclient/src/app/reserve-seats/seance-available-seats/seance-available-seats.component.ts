import { Component } from '@angular/core';
import { ActivatedRoute, RouterLink, Router } from '@angular/router';
import { CommonModule, NgForOf } from '@angular/common'; 
import { AvailableSeats } from '../../model/available-seats';
import { Reservation } from '../../model/reservation';
import { CinemaServiceService } from '../../cinema-service/cinema-service.service';
import { FormsModule } from '@angular/forms';
import { response } from 'express';

@Component({
  selector: 'app-seance-available-seats',
  standalone: true,
  imports: [RouterLink, FormsModule, CommonModule, NgForOf],
  templateUrl: './seance-available-seats.component.html',
  styleUrl: './seance-available-seats.component.css'
})
export class SeanceAvailableSeatsComponent {

    readonly availableSeats: AvailableSeats[];
    readonly reservation: Reservation;
    totalPrice: number = 0;
  
    constructor(private router: Router, private readonly activatedRoute: ActivatedRoute, private readonly cinemaService: CinemaServiceService) {
      this.availableSeats = this.activatedRoute.snapshot.data['availableSeats'];
      this.reservation = {
      id: "",
      totalPrice: 0,
      personData: "",
      reservedSeats: [],
      ticket: "",  
    }
  }

  updateTotalPrice(event: any, seat: any) {
    if (event.target.checked) {
      this.totalPrice += seat.price;
      this.reservation.totalPrice = this.totalPrice
      this.reservation.reservedSeats.push(seat)
    } else {
      this.totalPrice -= seat.price;
      this.reservation.totalPrice = this.totalPrice
      let index = this.reservation.reservedSeats.indexOf(seat);
      this.reservation.reservedSeats = this.reservation.reservedSeats.splice(index, 1)
    }

  }
  
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


  onSubmit(): void {
    console.log('Returned string:');
    this.cinemaService.saveReservation(this.reservation).subscribe({
      next: (response: number) => {
        console.log('Returned string:', response);
        this.router.navigate([`/reservations/${response}`]);
      },
    });
  }
  }
  