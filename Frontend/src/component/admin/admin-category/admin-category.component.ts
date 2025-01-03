import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../../../service/category.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-admin-category',
  templateUrl: './admin-category.component.html',
  styleUrls: ['./admin-category.component.css'],
  imports: [CommonModule, FormsModule],
})
export class AdminCategoryComponent implements OnInit {
  categories: any[] = [];
  newCategory: { catId: number; catDescription: string } = { catId: 0, catDescription: '' };
  successMessage: string = '';
  errorMessage: string = '';
  editingCategory: any = null; // Track the category being edited
  selectedField: { [key: number]: string } = {}; // Field selected for each category
  newValues: { [key: number]: string } = {}; // New values for each category

  constructor(private categoryService: CategoryService) {}

  ngOnInit(): void {
    this.fetchCategories();
  }

  fetchCategories(): void {
    this.categoryService.getAllCat().subscribe(
      (data) => {
        this.categories = data;
      },
      (error) => {
        this.errorMessage = 'Failed to fetch categories.';
        console.error(error);
      }
    );
  }

  addCategory(): void {
    if (this.newCategory.catId <= 0 || !this.newCategory.catDescription.trim()) {
      this.errorMessage = 'Please fill out all fields.';
      return;
    }

    this.categoryService.addCategory(this.newCategory).subscribe(
      () => {
        this.successMessage = 'Category added successfully!';
        this.fetchCategories();
        this.newCategory = { catId: 0, catDescription: '' }; // Reset form
      },
      (error) => {
        this.errorMessage = error.error?.message || 'Failed to add category.';
      }
    );
  }

  startEditing(category: any): void {
    this.editingCategory = category; // Track the category being edited
    this.selectedField[category.catId] = ''; // Reset the selected field
    this.newValues[category.catId] = ''; // Reset the new value
  }
  updateCategory(catId: number): void {
    const newDescription = this.newValues[catId];
  
    if (!newDescription.trim()) {
      this.errorMessage = 'Please enter a new description.';
      return;
    }
  
    this.categoryService.updateCategory(catId, newDescription).subscribe(
      () => {
        this.successMessage = `Description updated successfully!`;
        this.fetchCategories();
        this.cancelEditing(); // Reset editing state
      },
      (error) => {
        this.errorMessage = error.error?.message || 'Failed to update description.';
      }
    );
  }
  cancelEditing(): void {
    this.editingCategory = null; // Reset the editing state
  }
  
}  
