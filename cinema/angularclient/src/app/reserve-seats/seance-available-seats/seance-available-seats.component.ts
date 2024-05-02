import { Component } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common'; 
import { AvailableSeats } from '../../model/available-seats';

@Component({
  selector: 'app-seance-available-seats',
  standalone: true,
  imports: [RouterLink, CommonModule],
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
  
    constructor(private readonly activatedRoute: ActivatedRoute) {
      this.availableSeats = this.activatedRoute.snapshot.data['availableSeats'];
    }

}
