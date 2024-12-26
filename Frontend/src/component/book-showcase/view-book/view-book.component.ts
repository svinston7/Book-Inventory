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

@Component({
  selector: 'app-view-book',
  imports: [CommonModule, HomeComponent],
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

  reviewer:Reviewer={
    reviewerId: 0,
    name: '',
    employedBy: null
  }

  reviewList:Review[]=[]

  constructor(
    private bookService:ShowAllBooksService,
    private route:ActivatedRoute,
    private publisherService: GetPublisherService,
    private catService:CategoryService,
    private reviewService:ReviewsService
    ){}

  ngOnInit(){
    this.route.params.subscribe(params => this.getBookByISBN(params['isbn']))
  }


  getBookByISBN(isbn:string){
    this.bookService.getByISBN(isbn).subscribe((data:any)=>{
    this.book = data
    this.getPublisherId(this.book.publisherId);
    this.getCategory(this.book.categoryId)
    this.getReviews(this.book.isbn)
  })
  }

  getCategory(categoryId:number){
    this.catService.getCategory(categoryId).subscribe(
      (data)=> {
        this.cat = data;
      }
    )
  }

  getPublisherId(id:number){
    this.publisherService.getPublisherById(id).subscribe(
      (data)=>{
        this.publisher = data;
      }
    )
  }

  getReviews(isbn: string) {
    this.reviewService.getReviewByISBN(isbn).subscribe((data) => {
      this.reviewList = data;
  
      if (this.reviewList.length > 0) {
        for (let i = 0; i < this.reviewList.length; i++) {
          const reviewerId = this.reviewList[i].reviewerId;
          
  
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

}