import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  imports:[FormsModule,CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {

  constructor(private router: Router) {}

  navigateTo(module: string): void {
    this.router.navigate([`/admin/${module}`]);
  }
}
