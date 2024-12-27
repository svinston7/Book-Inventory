import { TestBed } from '@angular/core/testing';

import { GetAuthorService } from './get-author.service';

describe('GetAuthorService', () => {
  let service: GetAuthorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetAuthorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
