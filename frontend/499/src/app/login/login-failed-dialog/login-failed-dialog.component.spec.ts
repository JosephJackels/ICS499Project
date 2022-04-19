import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginFailedDialogComponent } from './login-failed-dialog.component';

describe('LoginFailedDialogComponent', () => {
  let component: LoginFailedDialogComponent;
  let fixture: ComponentFixture<LoginFailedDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginFailedDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginFailedDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
