import { Component } from '@angular/core';
import { Cart } from '../../model/Cart';
import { CartService } from '../../service/cart.service';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { HomeComponent } from '../home/home.component';
import { Router } from '@angular/router';
//import * as bootstrap from 'bootstrap';
 
 declare var Razorpay:any;
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
  bookCost:number=0;
  GSTCost:number=0;
totalCost:number=0;
 
constructor(private cartService:CartService,
  private http: HttpClient,
  private router:Router
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
      console.log(data);this.calculateTotalCost();
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
 
 calculateTotalCost():void{
  this.bookCost=this.books.reduce((sum,book)=>sum+book.price,0);
  this.GSTCost = parseFloat((this.bookCost / 12).toFixed(2)); // Ensures 2 decimal places
  this.totalCost = parseFloat((this.bookCost + this.GSTCost).toFixed(2));
  console.log(this.totalCost)

 }
getCart(userName:string){
  this.cartService.getCart(userName).subscribe((data:any[])=>
    { console.log('cart data are',data);
      this.books=data;
      this.calculateTotalCost();
               
       
               
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
                    this.popupMessage = 'Your Book has been removed ';
                    this.isPopupVisible=true;
                   
                    // this.books = this.books.filter((book) => book.isbn !== isbn); // Update the UI to reflect the removal
                    this.getCart(this.username??'');
                  }
                });
               
            }
           

            

































payNow() {
const RazorpayOptions = {
  key: 'rzp_test_zWhcqYLonnFntk',
  amount: this.totalCost * 100, 
  currency: 'INR',
  name: 'Book Inventory',
  description: 'Sample Razorpay demo',
  image: '',
      prefill: {
        name: 'Book Inventory',
        email: 'sam@gmail.com',
        contact: '9898989898'
      },
      theme: {
        color: '#6466e3'
      },
      handler: (response: any) => {
        
        console.log('Payment successful. Payment ID:', response.razorpay_payment_id);

        // Navigate to home after successful payment
        this.router.navigate(['/home']);
      },
      modal: {
        ondismiss: () => {
          console.log('Payment modal dismissed');
        }
      }
    };

    try {
      const rzp = new Razorpay(RazorpayOptions);
      rzp.open();

      
      
    } catch (error) {
      console.error('Error initializing Razorpay:', error);
    }
  }

}
       
