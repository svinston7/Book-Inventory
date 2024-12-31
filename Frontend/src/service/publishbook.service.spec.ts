import { TestBed } from '@angular/core/testing';

import { PublishbookService } from './publishbook.service';

describe('PublishbookService', () => {
  let service: PublishbookService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PublishbookService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
