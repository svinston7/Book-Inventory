
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { User } from '../User';
import { AuthService } from '../../../service/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  imports: [FormsModule, CommonModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  imagePath: string = 'assets/images/book-bg.jpg';

  constructor(private authService: AuthService) {}

  user: User = {
    firstName: '',
    lastName: '',
    phoneNumber: '',
    userName: '',
    password: '',
    terms: false // Assuming terms is a boolean
  };

  registerUser(event: Event, registerForm: any) {
    event.preventDefault();
    if (registerForm.valid) {
      this.authService.register(this.user).subscribe((e) => {
        alert(e);
      });
    } else {
      alert('Please fill out all fields correctly');
    }
  }
}


