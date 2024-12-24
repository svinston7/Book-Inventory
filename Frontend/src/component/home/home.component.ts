import { Component } from '@angular/core';

import { CommonModule } from '@angular/common';
import { BookShowcaswComponent } from './book-showcase/book-showcase.component';

@Component({
  selector: 'app-home',
  imports: [CommonModule,BookShowcaswComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
