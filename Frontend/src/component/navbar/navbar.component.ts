import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HomeComponent } from "../home/home.component";
import { BookShowcaseComponent } from "../book-showcase/book-showcase.component";

@Component({
  selector: 'app-navbar',
  imports: [HomeComponent, BookShowcaseComponent, BookShowcaseComponent],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  constructor(private router:Router){}

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['']); 
  }
  
}
