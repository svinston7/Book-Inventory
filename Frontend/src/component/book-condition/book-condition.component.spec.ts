import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookConditionComponent } from './book-condition.component';

describe('BookConditionComponent', () => {
  let component: BookConditionComponent;
  let fixture: ComponentFixture<BookConditionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BookConditionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BookConditionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
