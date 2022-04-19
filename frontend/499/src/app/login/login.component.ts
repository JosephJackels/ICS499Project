import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormBuilder, Validator, Validators, FormArray } from '@angular/forms';
import { Router } from '@angular/router';
import { DataServiceService } from '../service/data-service.service';
import { Login } from '../service/login';
import { User } from '../service/user';
import { LoginFailedDialogComponent } from './login-failed-dialog/login-failed-dialog.component';
import { MatDialog } from '@angular/material/dialog';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm !: FormGroup;
  title:string = 'Login';
  login!: Login;
  

  constructor(private fb: FormBuilder, private router: Router, private data:DataServiceService, public dialog:MatDialog) { }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      login: new FormArray([
        new FormGroup({
          username: new FormControl('', Validators.required),
          password: new FormControl('', Validators.minLength(10)),
        })
      ])
    });
  }

  get form(): FormArray{
    return this.loginForm.get('login') as FormArray;
  }

  onSubmit(){
    this.form.push(
      new FormGroup({
        username: new FormControl('', Validators.required),
        password: new FormControl('', Validators.minLength(10)),
    }));

    console.log(this.loginForm.value.login[0].username)
    console.log(this.loginForm.value.login[0].password)

    this.data.loginUser(this.loginForm.value.login[0].username, this.loginForm.value.login[0].password).subscribe(data => {
      this.login = {
        username: (data as any).username,
        token: (data as any).type + ' ' + (data as any).token,
        userId: (data as any).userId
      };
      if(this.login.token.length != null){
        localStorage.setItem('token', this.login.token);
        console.log(this.login);
        localStorage.setItem('userId', this.login.userId);
        this.router.navigate(['/home']);
      } else {
        //createFailedLoginDialog()
        this.loginFailedDialog();
      }
    });
  }
  loginFailedDialog() {
    const dialogRef =this.dialog.open(LoginFailedDialogComponent, {width: '500px'});
    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.ngOnInit();
    })
  }
}
