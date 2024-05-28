import { Component } from '@angular/core';
import { MovieSeance } from '../../model/movie-seance';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';  
import { CinemaServiceService } from '../../cinema-service/cinema-service.service';

@Component({
  selector: 'app-movie-detail',
  standalone: true,
  imports: [RouterLink, CommonModule],
  templateUrl: './movie-detail.component.html',
  styleUrl: './movie-detail.component.css'
})
export class MovieDetailComponent {
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

shouldCreateNewDiv(index: number): boolean {
  if (index === 0) return false;
  const current = this.getFirstTwoCharacters(this.seances[index].dateOfSeance);
  const previous = this.getFirstTwoCharacters(this.seances[index - 1].dateOfSeance);
  return current !== previous;
}

getFirstTwoCharacters(dateOfSeance: string): string {
  const date = new Date(dateOfSeance);
  const day = date.getDate().toString().padStart(2, '0');
  return day;
}

  readonly seances: MovieSeance[];

  constructor(private readonly activatedRoute: ActivatedRoute) {
    this.seances = this.activatedRoute.snapshot.data['seances'];
  }
}
