import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormBuilder, Validator, Validators } from '@angular/forms';
import { Router } from '@angular/router';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  registerForm = this.fb.group({
    username: ['', Validators.required],
    password: ['', 
        [Validators.required, Validators.minLength(10)]
      ],
  });
  
  title:string = 'Login';
  

  constructor(private fb: FormBuilder,
    /**  
    private authService: AuthService, 
    */
     private router: Router) { }

  ngOnInit(): void {
  }
}
