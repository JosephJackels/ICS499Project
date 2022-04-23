import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { FormControl, FormGroup, FormBuilder, Validator, Validators, FormArray } from '@angular/forms';
import { Router } from '@angular/router';
import { PasswordMatch } from './passwordMatch.validator';
import { DataServiceService } from '../service/data-service.service';
import { User } from '../service/interfaces/user';
import { Login } from '../service/interfaces/login';
import {MatDialog} from '@angular/material/dialog'
import { NewUserFailedDialog } from './new-user-failed-dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LoginSuccessSnackbarComponent } from '../login/login-success-snackbar/login-success-snackbar.component';
/** import { User } from './user/user'; */


@Component({
  selector: 'app-new-user',
  templateUrl: './new-user.component.html',
  styleUrls: ['./new-user.component.css']
})
export class NewUserComponent implements OnInit {
  registerForm !: FormGroup;

  title:string = 'Register';
  user!: User;
  login!: Login;
  message:string = 'Passwords must match'

  constructor(private fb: FormBuilder, private router: Router, private data:DataServiceService, public dialog: MatDialog, public snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.registerForm = new FormGroup({
      register: new FormArray([
        new FormGroup({
          username: new FormControl('', Validators.required),
          password: new FormControl('', Validators.minLength(10)),
          password2: new FormControl('', Validators.minLength(10)),
        })
      ])
    });
    this.registerForm.statusChanges.subscribe(newStatus =>{
      if(newStatus == "VALID" && this.isPasswordMatch){
        this.registerForm.markAsPristine();
      } else {
        this.registerForm.markAsDirty();
      }
    })
  }

  get form(): FormArray{
    return this.registerForm.get('register') as FormArray;
  }

  openSnackBar(){
    this.snackBar.openFromComponent(LoginSuccessSnackbarComponent, {
      duration: 5000,
    });
  }

  onSubmit() {
    this.form.push(
      new FormGroup({
        username: new FormControl('', Validators.required),
        password: new FormControl('', Validators.minLength(10)),
        password2: new FormControl('', Validators.minLength(10)),
      })
    );
    
    var isMatch = this.isPasswordMatch();
    console.log("new user form " + this.registerForm.value.register[0].username)
    console.log("new user form " + this.registerForm.value.register[0].password)


    if (isMatch) {
      this.data.createUser(this.registerForm.value.register[0].username, this.registerForm.value.register[0].password).subscribe(data => {
        this.user = {
          username: (data as any).username,
          userID: (data as any).userID,
          password: (data as any).password
        };
        this.data.loginUser(this.user.username, this.registerForm.value.register[0].password).subscribe(data => {
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
            this.openSnackBar();
            this.hideLoginAndSignUpTabs();
          }
        });
      });
    } else{
      this.openNewUserFailedDialog();
    }
  }

  openNewUserFailedDialog() {
    const dialogRef =this.dialog.open(NewUserFailedDialog, {width: '500px'});
    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.ngOnInit();
    })
  }
  
  isPasswordMatch() {
    var pass1 = this.registerForm.value.register[0].password;
    var pass2 = this.registerForm.value.register[0].password2;
    if (pass1 !== pass2) {
      return false;
    } 
    return true;
  }
  
  hideLoginAndSignUpTabs(){
    let tabElements = document.querySelectorAll("nav.mat-tab-nav-bar div.mat-tab-links>a") as NodeListOf<HTMLElement>;
    tabElements.forEach(tab => {
      if(tab.getAttribute("href") == "/login" || tab.getAttribute("href") == "/new-user"){
        tab.style.display = "none";
      }
    });
  }
}

