import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  selectedCategorieSet!: Set<number>;

  private invokeFunctionSource = new Subject<void>();
  invokeFunction$ = this.invokeFunctionSource.asObservable();

  constructor(private httpClient: HttpClient) {}

  CATURL: string = 'http://localhost:9090/api/category/';

  // Fetch a single category by ID
  getCategory(catId: number): Observable<any> {
    return this.httpClient.get(`${this.CATURL}${catId}`);
  }

  // Fetch all categories
  getAllCat(): Observable<any> {
    return this.httpClient.get(`${this.CATURL}getall`);
  }

  // Add a new category
  addCategory(category: { catId: number; catDescription: string }): Observable<any> {
    return this.httpClient.post(`${this.CATURL}post`, category);
  }
// Update a category description
updateCategory(catId: number, description: string): Observable<any> {
  return this.httpClient.put(`${this.CATURL}update/description/${catId}`, description, {
    headers: { 'Content-Type': 'application/json' },
  });
}


  // Store selected categories
  setSelectedCategories(selectedCategories: Set<number>) {
    this.selectedCategorieSet = selectedCategories;
    this.invokeFunctionSource.next(); // Notify listeners about the change
  }

  // Retrieve selected categories
  getSelectedCategories(): Set<number> {
    return this.selectedCategorieSet;
  }

  // For search functionality
  private searchQuerySource = new Subject<string>();
  searchQuery$ = this.searchQuerySource.asObservable();

  setSearchQuery(query: string): void {
    this.searchQuerySource.next(query); // Emit the search query
  }
}
