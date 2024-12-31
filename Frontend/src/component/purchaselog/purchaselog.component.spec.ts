import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PurchaselogComponent } from './purchaselog.component';

describe('PurchaselogComponent', () => {
  let component: PurchaselogComponent;
  let fixture: ComponentFixture<PurchaselogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PurchaselogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PurchaselogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
