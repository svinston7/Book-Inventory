import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReviewsService {

  
  constructor(private httpClient:HttpClient) { }

  REVIEWURL:string="http://localhost:9090/api/bookreview/"

  REVIEWERURL:string="http://localhost:9090/api/reviewer/employedby/"

  getReviewByISBN(isbn:string):Observable<any>{
    return this.httpClient.get(this.REVIEWURL+isbn,{
      responseType:'json'
    })
  }

  getReviewerByID(id:number):Observable<any>{
    return this.httpClient.get(this.REVIEWERURL+id,{
      responseType:'json'
    })
  }

}
