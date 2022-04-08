import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { FormControl, FormGroup, FormBuilder, Validator, Validators, FormArray } from '@angular/forms';
import { Router } from '@angular/router';
import { PasswordMatch } from './passwordMatch.validator';
import { DataServiceService } from '../service/data-service.service';
import { User } from '../service/user';
import { Login } from '../service/login';

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

  constructor(private fb: FormBuilder, private router: Router, private data:DataServiceService) { }

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
    
  }

  get form(): FormArray{
    return this.registerForm.get('register') as FormArray;
  }

  onSubmit() {
    this.form.push(
      new FormGroup({
        username: new FormControl('', Validators.required),
        password: new FormControl('', Validators.minLength(10)),
        password2: new FormControl('', Validators.minLength(10)),
    }));
    
    var isMatch = this.isPasswordMatch();
    console.log("new user form " + this.registerForm.value.register[0].username)
    console.log("new user form " + this.registerForm.value.register[0].password)


    if (isMatch) {
      this.data.createUser(this.registerForm.value.register[0].username, this.registerForm.value.register[0].password).subscribe(data =>
        this.user = {
          username: (data as any).username,
          userID: (data as any).userID,
          password: (data as any).password
      });
    } else{
      this.message = 'Passwords do not match';
    }
  }

  
  isPasswordMatch() {
    var pass1 = this.registerForm.value.register[0].password;
    var pass2 = this.registerForm.value.register[0].password;
    if (pass1 !== pass2) {
      return false;
    } 
    return true;
  }
  

}

