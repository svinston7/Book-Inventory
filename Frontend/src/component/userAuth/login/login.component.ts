import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../../service/auth.service';
import { User } from '../../../model/User';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [FormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private authService: AuthService, private router: Router) { }

  user: User = {
    firstName: '',
    lastName: '',
    phoneNumber: '',
    userName: '',
    password: '',
    roleNumber: 3,
    userId: 0
  };

  token: any;
  trylogin: boolean = false;

  // Login form submission
  loginUser(event: Event) {
    event.preventDefault();

    // Validate form before proceeding
    if (!this.user.userName || !this.user.password) {
      this.trylogin = true; 
      return;
    }

    this.authService.login(this.user).subscribe(
      (response) => {
        this.token = response;
        localStorage.setItem('token', this.token);
        localStorage.setItem('userName', this.user.userName);

        if (this.isAuthenticated()) {
          this.router.navigate(['/home']);
        }
      },
      (error) => {
        this.trylogin = true;
      }
    );
  }

  // Check if user is authenticated by checking token in localStorage
  isAuthenticated(): boolean {
    return !!localStorage.getItem('token');
  }

  // Toggle password visibility
  isPasswordVisible: boolean = false;

  togglePasswordVisibility(passwordInput: HTMLInputElement): void {
    passwordInput.type = passwordInput.type === 'password' ? 'text' : 'password';
    this.isPasswordVisible = !this.isPasswordVisible;
  }

  // Remove token and username from localStorage if already logged in
  ngOnInit() {
    if (localStorage.getItem('token')) {
      localStorage.removeItem('token');
      localStorage.removeItem('userName');
    }
  }
}
