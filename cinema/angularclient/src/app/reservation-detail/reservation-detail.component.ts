import { Component, Sanitizer } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common'; 
import { Reservation } from '../model/reservation';
import { delay } from 'rxjs/operators';
import { Observable, timer } from 'rxjs';

@Component({
  selector: 'app-reservation-detail',
  standalone: true,
  imports: [RouterLink, CommonModule],
  templateUrl: './reservation-detail.component.html',
  styleUrl: './reservation-detail.component.css'
})
export class ReservationDetailComponent {
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
    readonly reservation: Reservation;
    qrCodeImageUrl: string;
    isLoading = true;
    imageLoadError: boolean = false;
    retries: number = 0;

    ngOnInit(): void {
      this.loadImage();
    }
  
    loadImage(): void {
      setTimeout(() => {
        this.qrCodeImageUrl = `http://localhost:8080/tickets/${this.reservation.ticket}`;
        this.isLoading = false;
      }, 2000);
    }

    handleImageError(): void {
      this.retries++;
      if (this.retries <= 1) {
        this.qrCodeImageUrl = '';
        this.isLoading = true;
        this.loadImage();
      }
    }
  
    constructor(private readonly activatedRoute: ActivatedRoute) {
      this.reservation = this.activatedRoute.snapshot.data['reservation'];
      this.qrCodeImageUrl = `assets/qr-ticket/${this.reservation.ticket}.png`;
    }
}
