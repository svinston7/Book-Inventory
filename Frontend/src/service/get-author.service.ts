import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GetAuthorService {

  AUTHORBOOKURL:string="http://localhost:9090/api/bookauthors/isbn/";
  AUTHORURL:string="http://localhost:9090/api/author/authorid/"
  constructor(private httpClient:HttpClient) { }

  getAuthorBook(isbn:string):Observable<any>{
    return this.httpClient.get(this.AUTHORBOOKURL+isbn)
  }

  getAuthor(id:number):Observable<any>{
    return this.httpClient.get(this.AUTHORURL+id)
  }
}
