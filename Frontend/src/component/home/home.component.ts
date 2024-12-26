import { Component } from '@angular/core';

import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
<<<<<<< HEAD
import { ViewBookComponent } from "./book-showcase/view-book/view-book.component";
import { ShoppingcartComponent } from '../shoppingcart/shoppingcart.component';

@Component({
  selector: 'app-home',
  imports: [CommonModule, BookShowcaswComponent, ViewBookComponent,ShoppingcartComponent],
=======

@Component({
  selector: 'app-home',
  imports: [CommonModule],
>>>>>>> e714d80a7ae2fbaf27c441836b6b1ed353056613
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
