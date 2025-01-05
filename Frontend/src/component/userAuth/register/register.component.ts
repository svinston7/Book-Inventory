
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { User } from '../../../model/User';
import { AuthService } from '../../../service/auth.service';
import { CommonModule } from '@angular/common';
import { response } from 'express';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  imports: [FormsModule, CommonModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  imagePath: string = 'assets/images/book-bg.jpg';
  isPopupVisible = false;
  popupMessage: string = ''; 
  constructor(private authService: AuthService,private router :Router) {}

  user: User = {
    firstName: '',
    lastName: '',
    phoneNumber: '',
    userName: '',
    password: '',
    terms: false,
    
    roleNumber:3,
    userId: 0
  };

  
  registerUser(event: Event, registerForm: any) {
    event.preventDefault();
    if (registerForm.valid) {
      
     
      this.authService.register(this.user).subscribe((response) => {
        this.popupMessage = 'Your account has been created successfully!🎉';
        this.isPopupVisible=true;
       

      },
      (error) => {
        // Handle error (optional)
        this.popupMessage = 'User Already Exists. Please try again.';
        this.isPopupVisible = true;
      });
    } else {
      alert('Please fill out all fields correctly');
    }
    
  }
  closePopup() {
    this.isPopupVisible = false;
    if(this.popupMessage==='Your account has been created successfully!🎉'){
      this.router.navigate(["/login"])
    }
  }
   
  
}


