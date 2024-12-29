import { Component } from '@angular/core';
import { ShowAllBooksService } from '../../../service/show-all-books.service';
import { ActivatedRoute } from '@angular/router';
import { Book } from '../../../model/Book';
import { CommonModule } from '@angular/common';
import { HomeComponent } from "../../home/home.component";
import { GetPublisherService } from '../../../service/get-publisher.service';
import { Publisher } from '../../../model/Publisher';
import { CategoryService } from '../../../service/category.service';
import { Category } from '../../../model/Category';
import { ReviewsService } from '../../../service/reviews.service';
import { Review } from '../../../model/Review';
import { Reviewer } from '../../../model/Reviewer';
import { Author } from '../../../model/Author';
import { GetAuthorService } from '../../../service/get-author.service';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { randomInt } from 'crypto';
declare var bootstrap: any; 
@Component({
  selector: 'app-view-book',
  imports: [CommonModule, HomeComponent,FormsModule,ReactiveFormsModule ],
  templateUrl: './view-book.component.html',
  styleUrl: './view-book.component.css'
})

export class ViewBookComponent {
  book:Book ={
    isbn: '',
    title: '',
    description: '',
    categoryId: 0,
    edition: '',
    publisherId: 0,
    image: ''
  }
  publisher: Publisher={
    publisherId: 0,
    name: '',
    city: '',
    stateCode: ''
  }
  cat:Category={
    catId: 0,
    catDescription: ''
  }
  review:Review={
    isbn: '',
    reviewerId: 0,
    rating: 0,
    comments: '',
    name: '',
    employedBy: ''
  }
  author:Author={
    authorId: 0,
    firstName: '',
    lastName: '',
    photo: '',
    bookList: [],
    primary: false
  }

  reviewer:Reviewer={
    reviewerId: 0,
    name: '',
    employedBy: null
  }
  dummyReview = {
    isbn: this.review.isbn,
    reviewerId: this.review.reviewerId,
    rating: this.review.rating,
    comments: this.review.comments,
  };
  authorList:Author[]=[]
  reviewList:Review[]=[]
  successMessage: string | undefined;

  constructor(
    private bookService:ShowAllBooksService,
    private route:ActivatedRoute,
    private publisherService: GetPublisherService,
    private catService:CategoryService,
    private reviewService:ReviewsService,
    private authorService:GetAuthorService,
    
    ){
      
    }

  ngOnInit(){
    this.route.params.subscribe(params => this.getBookByISBN(params['isbn']))
  }


  //fetch book and init all other fn
  getBookByISBN(isbn:string){
    this.bookService.getByISBN(isbn).subscribe((data:any)=>{
    this.book = data
    this.getPublisherId(this.book.publisherId);
    this.getCategory(this.book.categoryId)
    this.getReviews(this.book.isbn)
    this.getAuthor(this.book.isbn)
  })
  }

  //fetch category
  getCategory(categoryId:number){
    this.catService.getCategory(categoryId).subscribe(
      (data)=> {
        this.cat = data;
      }
    )
  }
 //fetch publishers
  getPublisherId(id:number){
    this.publisherService.getPublisherById(id).subscribe(
      (data)=>{
        this.publisher = data;
      }
    )
  }


  //fetch reviews with reviewers
  getReviews(isbn: string) {
    this.reviewService.getReviewByISBN(isbn).subscribe((data) => {
      this.reviewList = data;
  
      if (this.reviewList.length > 0) {
        for (let i = 0; i < this.reviewList.length; i++) {
          const reviewerId = this.reviewList[i].reviewerId;
          localStorage.setItem('reviewerId',reviewerId+"")
  
          // Fetch the reviewer for each review
          this.reviewService.getReviewerByID(reviewerId).subscribe((reviewerData) => {
            // Assign the reviewer's name to the review
            this.reviewList[i].name = reviewerData.name;
            this.reviewList[i].employedBy = reviewerData.employedBy
          });
        }
      }
    });
  }


  //get author with details
  getAuthor(isbn:string){
    this.authorService.getAuthorBook(isbn).subscribe((authBookData)=>{
      this.authorList=authBookData;
      

    })
  }
  postReview(): void {
    
    const storedName = localStorage.getItem('userName');
    this.reviewer.name = storedName ? storedName : 'Anonymous';
    this.reviewer.reviewerId = Math.floor(Math.random() * (1000 - 100 + 1)) + 100;
    this.reviewer.employedBy = 'Anonymous'
    this.reviewService.postReviewer(this.reviewer).subscribe(
      (e)=>{
        this.dummyReview.isbn = this.book.isbn
        this.dummyReview.reviewerId = this.reviewer.reviewerId
        this.reviewService.postReview(this.dummyReview).subscribe();
      }
    )
    this.successMessage = 'Posted successfully!';
    const modal = document.getElementById('staticBackdrop') as any;
    const modalInstance = bootstrap.Modal.getInstance(modal);
    modalInstance.hide();
  }
}