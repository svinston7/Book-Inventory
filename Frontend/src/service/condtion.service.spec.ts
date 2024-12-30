import { TestBed } from '@angular/core/testing';

import { CondtionService } from './condtion.service';

describe('CondtionService', () => {
  let service: CondtionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CondtionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
