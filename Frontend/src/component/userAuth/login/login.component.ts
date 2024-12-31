
import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../../service/auth.service';
import { User } from '../User';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [FormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  constructor(private authService: AuthService, private router: Router) { }

  user: User = {
    userid:0,
    firstName: '',
    lastName: '',
    phoneNumber: '',
    userName: '',
    password: '',
    roleNumber:3 //default user
  };
  token: any;
  trylogin: boolean = false;

  loginUser(event: Event) {
    event.preventDefault();
    console.log(this.user);

    // Only proceed if all fields are filled
    if (!this.user.userName || !this.user.password) {
      this.trylogin = true;
      return;
    }

    this.authService.login(this.user).subscribe((e) => {
      this.token = e;
      localStorage.setItem('token', this.token);
      localStorage.setItem('username',this.user.userName);
      if (this.isAuthenticated()) {
        this.router.navigate(['/home']);
      }
    }, (error) => {
      this.trylogin = true;
    });
  }

  isAuthenticated(): boolean {
    return !!localStorage.getItem('token');
  }

  isPasswordVisible: boolean = false;

  togglePasswordVisibility(passwordInput: HTMLInputElement): void {
    passwordInput.type = passwordInput.type === 'password' ? 'text' : 'password';
    this.isPasswordVisible = !this.isPasswordVisible;
  }

  ngOnInit(){
    if(localStorage.getItem('token')){
      localStorage.removeItem('token')
    }
  }
}



