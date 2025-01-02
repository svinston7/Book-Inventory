import { Component, OnInit } from '@angular/core';
import { ShowAllBooksService } from '../../../service/show-all-books.service';
import { Book } from '../../../model/Book';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-admin-book',
  imports: [FormsModule, CommonModule],
  templateUrl: './admin-book.component.html',
  styleUrls: ['./admin-book.component.css']
})
export class AdminBookComponent implements OnInit {
  books: Book[] = [];

  constructor(private showAllBooksService: ShowAllBooksService) {}

  ngOnInit(): void {
    this.fetchBooks();
  }

  fetchBooks(): void {
    this.showAllBooksService.showBooks().subscribe(
      (response: Book[]) => {
        this.books = response;
      },
      (error) => {
        alert('Error fetching books: ' + error);
      }
    );
  }

  updateBook(isbn: string): void {
    const updatedBook = this.books.find(book => book.isbn === isbn);

    if (updatedBook) {
      this.showAllBooksService.updateBook(isbn, updatedBook).subscribe(
        (response) => {
          alert('Book updated successfully!');
          this.fetchBooks(); // Refresh the book list
        },
        (error) => {
          alert('Error updating book: ' + error);
        }
      );
    }
  }
}
