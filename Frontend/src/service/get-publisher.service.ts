import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { text } from 'stream/consumers';

@Injectable({
  providedIn: 'root'
})
export class GetPublisherService {

  PUBLISHERURL:string = "http://localhost:9090/api/publisher"

  constructor(private httpClient:HttpClient) { }

  showPublishers():Observable<any>{
    return this.httpClient.get(this.PUBLISHERURL);
  }


  getPublisherById(id:number):Observable<any>{
    return this.httpClient.get(this.PUBLISHERURL+id,{
      responseType:'text'
    });
  }

}
