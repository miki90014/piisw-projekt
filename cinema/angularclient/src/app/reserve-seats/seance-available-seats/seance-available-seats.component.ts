import { Component } from '@angular/core';
import { ActivatedRoute, RouterLink, Router } from '@angular/router';
import { CommonModule, NgForOf } from '@angular/common'; 
import { AvailableSeats } from '../../model/available-seats';
import { Reservation } from '../../model/reservation';
import { SeatFrontEnd } from '../../model/seat-front-end';
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
    seats: Map<string, SeatFrontEnd>;

  
    constructor(private router: Router, private readonly activatedRoute: ActivatedRoute, private readonly cinemaService: CinemaServiceService) {
      this.availableSeats = this.activatedRoute.snapshot.data['availableSeats'];
      this.seats = new Map<string, SeatFrontEnd>();
      this.availableSeats.forEach(availableSeat => {
        let newSeat = new SeatFrontEnd()
        newSeat.price = availableSeat.price
        newSeat.seat_id = availableSeat.id
        newSeat.seatStatus = availableSeat.seatStatus
        newSeat.seatObject = availableSeat
        let key = availableSeat.seat.seatRow + availableSeat.seat.number
        this.seats.set(key, newSeat)
      });
      this.reservation = {
        id: "",
        totalPrice: 0,
        personData: "",
        reservedSeats: [],
        ticket: "",  
      }
      console.log(this.seats)

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
    if (this.reservation.reservedSeats.length === 0) {
      alert("You must check at least one seat")
    } else {
      this.cinemaService.saveReservation(this.reservation).subscribe({
        next: (response: number) => {
          this.router.navigate([`/reservations/${response}`]);
        },
      });
    }
  }
  }
  