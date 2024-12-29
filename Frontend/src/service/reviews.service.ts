import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Review } from '../model/Review';

@Injectable({
  providedIn: 'root'
})
export class ReviewsService {

  
  constructor(private httpClient:HttpClient) { }

  REVIEWURL:string="http://localhost:9090/api/bookreview/"

  REVIEWERURL:string="http://localhost:9090/api/reviewer/"

  getReviewByISBN(isbn:string):Observable<any>{
    return this.httpClient.get(this.REVIEWURL+isbn,{
      responseType:'json'
    })
  }

  getReviewerByID(id:number):Observable<any>{
    return this.httpClient.get(this.REVIEWERURL+"employedby/"+id,{
      responseType:'json'
    })
  }

  postReview(review:any):Observable<any>{
    return this.httpClient.post(this.REVIEWURL+"post",review)
  }

  postReviewer(reviewer:any):Observable<any>{
    return this.httpClient.post(this.REVIEWERURL+"post",reviewer);
  }

}
