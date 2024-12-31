import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet,CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Frontend';


  // getDataService():Observable<any>{
  //   const token = localStorage.getItem('token')
  //   const headers = new HttpHeaders({
  //     Authorization :`Bearer ${token}`,
  //   });
  //   return this.httpClient.post(this.AUTHURL+'getmessage',null,{headers})
  // }
}
