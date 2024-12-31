import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,FormsModule],
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
