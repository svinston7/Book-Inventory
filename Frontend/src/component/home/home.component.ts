import { Component } from '@angular/core';

import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { ShoppingcartComponent } from '../shoppingcart/shoppingcart.component';

@Component({
  selector: 'app-home',
  imports: [CommonModule],
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
