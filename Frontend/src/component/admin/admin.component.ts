import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { HomeComponent } from '../home/home.component';
@Component({
  selector: 'app-admin',
  
  imports: [RouterModule,CommonModule,HomeComponent],
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css'
})
export class AdminComponent {
currentPage: string = 'dashboard';
isSidebarOpen: boolean = false;
constructor(private router: Router) {}

navigate(page: string): void {
  this.currentPage = page;
}
// Toggle sidebar visibility
toggleSidebar(): void {
  this.isSidebarOpen = !this.isSidebarOpen;
}

logout(): void {
  localStorage.clear();
  this.router.navigate(['/login']);
}

addBook(): void {
  // Placeholder for adding a book
  //alert('Redirect to Add Book Page');
  this.router.navigate(['/admin-add-book']);
}
}
