import { TestBed } from '@angular/core/testing';

import { PurchaselogService } from './purchaselog.service';

describe('PurchaselogService', () => {
  let service: PurchaselogService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PurchaselogService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
