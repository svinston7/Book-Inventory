import { Component } from '@angular/core';
import { GetAuthorService } from '../../service/get-author.service';
import { CommonModule } from '@angular/common';
import { Author } from '../../model/Author';
import { BookAuthor } from '../../model/BookAuthor';
import { Book } from '../../model/Book';
import { HomeComponent } from '../home/home.component';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-author-view',
  imports: [CommonModule,HomeComponent,RouterModule],
  templateUrl: './author-view.component.html',
  styleUrl: './author-view.component.css'
})
export class AuthorViewComponent {
  author:BookAuthor={
    id: 0,
    isbn: '',
    authorId: 0,
    primaryAuthor: false,
    BookList: []
  }
  ListOfBook:Book[]=[]
  AuthorList:Author[]=[]
  constructor(private authorService:GetAuthorService){}


  ngOnInit(){
    this.authorService.getAllAuthors().subscribe((data)=>{
      this.AuthorList = data
      for(let i=0;i<this.AuthorList.length;i++){
        this.authorService.getBooksByAuthor(this.AuthorList[i].authorId).subscribe((e)=>{
          this.AuthorList[i].bookList = e;
        })
      }
      
    })
  }
  // Store the active collapse state
  activeCollapseId: number | null = null;

  toggleCollapse(authorId: number) {
    // Toggle the collapse for the specific authorId
    this.activeCollapseId = this.activeCollapseId === authorId ? null : authorId;
  }

  isCollapseVisible(authorId: number): boolean {
    return this.activeCollapseId === authorId;
  }
}
