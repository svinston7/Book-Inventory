import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin',
  imports:[CommonModule],
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent {
  currentPage: string = 'dashboard';

  constructor(private router: Router) {}

  navigate(page: string): void {
    this.currentPage = page;
  }

  logout(): void {
    localStorage.clear();
    this.router.navigate(['/login']);
  }

  addBook(): void {
    // Placeholder for adding a book
    alert('Redirect to Add Book Page');
  }
}
