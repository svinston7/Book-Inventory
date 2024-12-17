import { Component } from '@angular/core';
import {FormsModule} from '@angular/forms'
import { User } from '../User';
import { AuthService } from '../../../service/auth.service';
@Component({
  selector: 'app-register',
  imports: [FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

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

}
