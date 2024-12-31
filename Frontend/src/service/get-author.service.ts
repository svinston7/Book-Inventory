import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GetAuthorService {

  AUTHORBOOKURL:string="http://localhost:9090/api/bookauthors/";
  AUTHORURL:string="http://localhost:9090/api/author/"
  constructor(private httpClient:HttpClient) { }

  getAuthorBook(isbn:string):Observable<any>{
    return this.httpClient.get(this.AUTHORBOOKURL+"isbn/"+isbn)
  }

  getAuthor(id:number):Observable<any>{
    return this.httpClient.get(this.AUTHORURL+"authorid/"+id)
  }

  getAllAuthors():Observable<any>{
    return this.httpClient.get(this.AUTHORURL+"allauthors")
  }
  getBooksByAuthor(id:number):Observable<any>{
    return this.httpClient.get(this.AUTHORBOOKURL+"authorid/"+id)
  }
}
