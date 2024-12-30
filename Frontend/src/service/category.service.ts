  import { HttpClient } from '@angular/common/http';
  import { Injectable } from '@angular/core';
  import { Observable, Subject } from 'rxjs';

  @Injectable({
    providedIn: 'root'
  })
  export class CategoryService {
    selectedCategorieSet!: Set<number>; 

    private invokeFunctionSource = new Subject<void>();
    invokeFunction$ = this.invokeFunctionSource.asObservable();
  
    constructor(private httpClient:HttpClient) { }
    CATURL:string = "http://localhost:9090/api/category/"

    getCategory(catId:number):Observable<any>{
      return this.httpClient.get(this.CATURL + catId);
    }

    getAllCat():Observable<any>{
      return this.httpClient.get(this.CATURL+"getall")
    }

    setSelectedCategories(selectedCategories:Set<number>){
      this.selectedCategorieSet = selectedCategories
      this.invokeFunctionSource.next();  // Emit event to notify about category selection change
    }

    getSelectedCategories():Set<number>{
      return this.selectedCategorieSet
    }


      // for search fn
    private searchQuerySource = new Subject<string>();
    searchQuery$ = this.searchQuerySource.asObservable();

    setSearchQuery(query: string): void {
      this.searchQuerySource.next(query); // Emit the search query
    } 
  }
