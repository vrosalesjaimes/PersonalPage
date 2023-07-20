import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoadProtectorComponent } from './load-protector.component';

describe('LoadProtectorComponent', () => {
  let component: LoadProtectorComponent;
  let fixture: ComponentFixture<LoadProtectorComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LoadProtectorComponent]
    });
    fixture = TestBed.createComponent(LoadProtectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
