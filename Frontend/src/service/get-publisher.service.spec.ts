import { TestBed } from '@angular/core/testing';

import { GetPublisherService } from './get-publisher.service';

describe('GetPublisherService', () => {
  let service: GetPublisherService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetPublisherService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
