import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Book } from '../model/Book';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ShowAllBooksService {

  constructor(private httpClient:HttpClient) { }

  BOOKURL:string = "http://localhost:9090/api/book"

  showBooks():Observable<any>{
    return this.httpClient.get(this.BOOKURL);
  }

  // getByISBN(isbn:string){
  //   return this.httpClient.get(this.BOOKURL+"/isbn/"+isbn);
  // }
  // updateBook(isbn: string, bookData: any): Observable<any> {
  //   return this.httpClient.put(`${this.BOOKURL}/update/${isbn}`, bookData);
  // }

   // Fetch a book by ISBN
   getByISBN(isbn: string): Observable<Book> {
    return this.httpClient.get<Book>(`${this.BOOKURL}/isbn/${isbn}`);
  }

  // Update the entire book object
  updateBook(isbn: string, bookData: Book): Observable<Book> {
    return this.httpClient.put<Book>(`${this.BOOKURL}/update/${isbn}`, bookData);
  }
}
