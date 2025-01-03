import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAuthorComponent } from './admin-author.component';

describe('AdminAuthorComponent', () => {
  let component: AdminAuthorComponent;
  let fixture: ComponentFixture<AdminAuthorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminAuthorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminAuthorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
