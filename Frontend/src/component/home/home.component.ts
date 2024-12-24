import { Component } from '@angular/core';

import { CommonModule } from '@angular/common';
import { BookShowcaswComponent } from './book-showcase/book-showcase.component';
import { Router } from '@angular/router';
import { ViewBookComponent } from "./book-showcase/view-book/view-book.component";

@Component({
  selector: 'app-home',
  imports: [CommonModule, BookShowcaswComponent, ViewBookComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  constructor(private router:Router){}

logout() {
  localStorage.removeItem('token');
  this.router.navigate(['']); 
}

}
