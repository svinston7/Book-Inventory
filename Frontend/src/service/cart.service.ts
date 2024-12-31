import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Book } from '../model/Book';
import { Cart } from '../model/Cart';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  getBooksByIsbns(isbns: string[]){
    return this.httpClient.post(this.CARTurl+isbns,{isbns});
  }
  cartservice: any;

  constructor(private httpClient:HttpClient) { }
  CARTurl:string="http://localhost:9090/api/shoppingcart/"

  postCart(cart:Cart):Observable<any>{
    return this.httpClient.post(this.CARTurl+"post",cart,{
      responseType:'text'
    });
  }
   
  getCart(username:string):Observable<any>{
    return this.httpClient.get(this.CARTurl+username);
  }
  // items:Cart[]=[];
  // addtocart(product:Cart){
  //   this.items.push(product);
  //   window.alert("shopping cart added");
  // }
  // clearcart(){
  // this.items=[];
  // return this.items;
  // }

  // addtocart(isbn:string){
  //   const username=localStorage.getItem("username");
  //   if(!username){
  //     console.error("user is not logged in");
  //     return;
  //   }
  //   const cart:Cart={
  //     userName:username,
  //     isbn:isbn,
  //   };
  //   this.cartser
  // }

  
}
