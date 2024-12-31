import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-addbook',
  templateUrl: './addbook.component.html',
  styleUrls: ['./addbook.component.css']
})
export class AddbookComponent implements OnInit {
  addBookForm!: FormGroup;
  categories: any[] = [];
  publishers: any[] = [];
  errorMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.initializeForm();
    this.loadCategories();
    this.loadPublishers();
  }

  // Initialize form
  initializeForm() {
    this.addBookForm = this.fb.group({
      isbn: ['', Validators.required],
      title: ['', Validators.required],
      description: ['', Validators.required],
      categoryId: ['', Validators.required],
      edition: ['', Validators.required],
      publisherId: ['', Validators.required],
      image: ['', Validators.required],
    });
  }

  // Load categories
  loadCategories() {
    this.http.get('/api/categories').subscribe(
      (data: any) => (this.categories = data),
      (error) => console.error('Error fetching categories:', error)
    );
  }

  // Load publishers
  loadPublishers() {
    this.http.get('/api/publishers').subscribe(
      (data: any) => (this.publishers = data),
      (error) => console.error('Error fetching publishers:', error)
    );
  }

  // Add book
  onSubmit() {
    if (this.addBookForm.valid) {
      this.http.post('/api/book/post', this.addBookForm.value).subscribe(
        (response: any) => {
          alert('Book added successfully!');
          this.router.navigate(['/admin/inventory']);
        },
        (error) => {
          console.error('Error adding book:', error);
          this.errorMessage = error.error.message || 'Failed to add book.';
        }
      );
    }
  }
}
