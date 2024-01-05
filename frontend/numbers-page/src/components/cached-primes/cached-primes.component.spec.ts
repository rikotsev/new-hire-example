import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CachedPrimesComponent } from './cached-primes.component';

describe('CachedPrimesComponent', () => {
  let component: CachedPrimesComponent;
  let fixture: ComponentFixture<CachedPrimesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CachedPrimesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CachedPrimesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
