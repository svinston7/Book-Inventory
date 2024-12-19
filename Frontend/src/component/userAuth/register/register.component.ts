import { Component } from '@angular/core';
import {FormsModule} from '@angular/forms'
import { User } from '../User';
import { AuthService } from '../../../service/auth.service';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-register',
  imports: [FormsModule,CommonModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  imagePath: string = 'assets/images/book-bg.jpg';
  constructor(private authService:AuthService){}

  user:User={
    firstName: '',
    lastName: '',
    phoneNumber: '',
    userName: '',
    password: ''
  }

registerUser(event:Event) {
  event.preventDefault();
  this.authService.register(this.user).subscribe((e)=>{
    alert(e)
  });
  

}
  isPasswordVisible: boolean = false;

  togglePasswordVisibility(passwordInput:HTMLInputElement) :void{
    passwordInput.type = passwordInput.type === 'password' ? 'text' : 'password';
    this.isPasswordVisible = !this.isPasswordVisible;
  }
}
