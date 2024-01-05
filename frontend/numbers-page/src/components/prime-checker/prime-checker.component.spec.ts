import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrimeCheckerComponent } from './prime-checker.component';

describe('PrimeCheckerComponent', () => {
  let component: PrimeCheckerComponent;
  let fixture: ComponentFixture<PrimeCheckerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PrimeCheckerComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PrimeCheckerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
