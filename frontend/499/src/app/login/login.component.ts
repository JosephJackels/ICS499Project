import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormBuilder, Validator, Validators, FormArray } from '@angular/forms';
import { Router } from '@angular/router';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm !: FormGroup;
  
  title:string = 'Login';
  

  constructor(private fb: FormBuilder, private router: Router) { }

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
  }
}
