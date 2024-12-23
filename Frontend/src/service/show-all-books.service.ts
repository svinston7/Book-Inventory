import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Book } from '../component/home/Book';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ShowAllBooksService {

  constructor(private httpClient:HttpClient) { }

  BOOKURL:string = "http://localhost:9090/api/book"

  showBooks(book:any):Observable<any>{
    return this.httpClient.get(this.BOOKURL,book);
  }


}
