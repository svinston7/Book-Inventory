import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
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
  books: any[] = [];
  popupMessage: string = '';
  isPopupVisible: boolean = false;
  

  constructor(
    private showAllBooksService: ShowAllBooksService,
  private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.fetchBooks();
  }

  fetchBooks(): void {
    this.showAllBooksService.showBooks().subscribe(
      (books) => {
        this.books = books;
        this.cdr.detectChanges();
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
          //alert('Book updated successfully!');
          this.popupMessage='Book updated successfully!';
            this.isPopupVisible=true;
          this.fetchBooks(); // Refresh the book list
        },
        (error) => {
          alert('Error updating book: ' + error);
        }
      );
    }
  }
}
