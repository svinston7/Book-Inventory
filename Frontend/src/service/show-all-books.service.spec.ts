import { TestBed } from '@angular/core/testing';

import { ShowAllBooksService } from './show-all-books.service';

describe('ShowAllBooksService', () => {
  let service: ShowAllBooksService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ShowAllBooksService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
