import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../../service/auth.service';
import { User } from '../User';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [FormsModule,CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  constructor(private authService:AuthService,private router:Router){}
  user:User={
    firstName: '',
    lastName: '',
    phoneNumber: '',
    userName: '',
    password: ''
  }
  token:any

  trylogin:boolean=false;


loginUser(event:Event) {
  event.preventDefault()
  console.log(this.user)
  this.authService.login(this.user).subscribe((e)=>{
    this.token = e;
    localStorage.setItem('token',this.token);
      if(this.isAuthenticated()){
        this.router.navigate(['/home'])
      }
      else{
        this.trylogin=true;
      }
  })
}



isAuthenticated():boolean{
  if( localStorage.getItem('token')){
    console.log("true");
    return true;
  }
  return false;
}



isPasswordVisible: boolean = false;

  togglePasswordVisibility(passwordInput:HTMLInputElement) :void{
    passwordInput.type = passwordInput.type === 'password' ? 'text' : 'password';
    this.isPasswordVisible = !this.isPasswordVisible;
  }


}
