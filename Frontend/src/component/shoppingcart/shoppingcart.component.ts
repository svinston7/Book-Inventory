import { Component } from '@angular/core';
import { Cart } from '../../model/Cart';
import { CartService } from '../../service/cart.service';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { HomeComponent } from '../home/home.component';
//import * as bootstrap from 'bootstrap';
 
 
@Component({
  selector: 'app-shoppingcart',
  imports: [CommonModule,HomeComponent],
  templateUrl: './shoppingcart.component.html',
  styleUrl: './shoppingcart.component.css'
})
export class ShoppingcartComponent {
 
  toastMessage: string = '';
  isToastVisible: boolean = false;
  isPopupVisible = false;
  popupMessage: string = '';
 
constructor(private cartService:CartService,
  private http: HttpClient
){
 
}
cart:Cart={
  userName: '',
  isbn: '',
 
}
books:any[]=[];
username=localStorage.getItem("userName");
 
ngOnInit() {
  // Initialize the username
  if (this.username) {
    this.cart.userName = this.username;
    this.cartService.getCart(this.cart.userName).subscribe((data) => {
      this.books = data;
      console.log(data);
    });
  }
 
  // Initialize Bootstrap Toast
  // const toastTrigger = document.getElementById('liveToastBtn');
  // const toastLiveExample = document.getElementById('liveToast');
 
  // if (toastTrigger && toastLiveExample) {
  //   //const toastBootstrap = Toast.getOrCreateInstance(toastLiveExample);
  //   toastTrigger.addEventListener('click', () => {
  //     //toastBootstrap.show();
  //   });
  // }
}
 
 
getCart(userName:string){
  this.cartService.getCart(userName).subscribe((data:any[])=>
    { console.log('cart data are',data);
      this.books=data;
     
               
       
               
                });
            }
 
           
 
            showToast(message: string) {
              this.toastMessage = message;
              this.isToastVisible = true;
         
              // Automatically hide the toast after 3 seconds
              setTimeout(() => {
                this.isToastVisible = false;
              }, 3000);
            }
         
            removeBook(isbn: string) {
              const payload = { userName: this.username, isbn: isbn };
              console.log(this.username);
              console.log(isbn);
              // Prepare the payload for the POST request
              // this.toastMessage = `The book has been removed from the list.`;
              // this.isToastVisible = true;
           
              // Show the toast using Bootstrap's Toast instance
              //const toastElement = document.getElementById('liveToast');
              this.showToast("Book has been removed from the cart");
           
              // Automatically hide the toast after 3 seconds
              setTimeout(() => {
                this.isToastVisible = false;
              }, 3000);
 
              this.http
                .post('http://localhost:9090/api/shoppingcart/remove', payload,{
                  responseType:'text'
                })  // Ensure this endpoint is correct
                .subscribe({
                  next: () => {
                    //alert('Item removed successfully!');
                    this.popupMessage = 'Your Book has been sucessfully added to the cart';
                    this.isPopupVisible=true;
                   
                    this.books = this.books.filter((book) => book.isbn !== isbn); // Update the UI to reflect the removal
                  }
                });
               
            }
           
           
          }
       