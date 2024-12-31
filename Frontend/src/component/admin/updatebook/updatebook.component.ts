import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-updatebook',
  templateUrl: './updatebook.component.html',
  styleUrls: ['./updatebook.component.css']
})
export class UpdateBookComponent implements OnInit {
  updateBookForm!: FormGroup;
  categories: any[] = [];
  publishers: any[] = [];
  isbn!: string;
  errorMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private http: HttpClient,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.isbn = this.route.snapshot.paramMap.get('id')!;
    this.initializeForm();
    this.loadBookDetails();
    this.loadCategories();
    this.loadPublishers();
  }

  // Initialize form
  initializeForm() {
    this.updateBookForm = this.fb.group({
      isbn: [{ value: '', disabled: true }, Validators.required],
      title: ['', Validators.required],
      description: ['', Validators.required],
      categoryId: ['', Validators.required],
      edition: ['', Validators.required],
      publisherId: ['', Validators.required],
      image: ['', Validators.required],
    });
  }

  // Load book details by ISBN
  loadBookDetails() {
    this.http.get(`/api/book/isbn/${this.isbn}`).subscribe(
      (book: any) => {
        this.updateBookForm.patchValue(book);
      },
      (error) => {
        console.error('Error loading book details:', error);
        this.errorMessage = 'Failed to load book details.';
      }
    );
  }

  // Load categories
  loadCategories() {
    this.http.get('/api/category').subscribe(
      (data: any) => (this.categories = data),
      (error) => console.error('Error fetching categories:', error)
    );
  }

  // Load publishers
  loadPublishers() {
    this.http.get('/api/publisher').subscribe(
      (data: any) => (this.publishers = data),
      (error) => console.error('Error fetching publishers:', error)
    );
  }

  // Update book details
  onSubmit() {
    if (this.updateBookForm.valid) {
      const updatedBook = this.updateBookForm.getRawValue();
      this.http.put(`/api/book/update/${this.isbn}`, updatedBook).subscribe(
        (response: any) => {
          alert('Book updated successfully!');
          this.router.navigate(['/admin/inventory']);
        },
        (error) => {
          console.error('Error updating book:', error);
          this.errorMessage = error.error.message || 'Failed to update book.';
        }
      );
    }
  }
}
