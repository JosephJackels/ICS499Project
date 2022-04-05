import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { FormControl, FormGroup, FormBuilder, Validator, Validators } from '@angular/forms';
import { Router } from '@angular/router';



import { PasswordMatch } from './passwordMatch.validator';
/** import { User } from './user/user'; */


@Component({
  selector: 'app-new-user',
  templateUrl: './new-user.component.html',
  styleUrls: ['./new-user.component.css']
})
export class NewUserComponent implements OnInit {

  registerForm = this.fb.group({
    firstName: ['', Validators.required],
    lastName: ['', Validators.required],
    userName: [''],
    password: ['', 
        [Validators.required, Validators.minLength(10)]
      ],
    password2: ['',
        [Validators.required, Validators.minLength(10)]
      ],
    phone: ['',
        [Validators.required]
      ],
    email: ['', 
        [Validators.required, Validators.pattern('^(.+)(@)([^\.]*)\.([a-z]{2,})$')]
      ]
  }, {
    validator: PasswordMatch('password', 'password2')
  });
  
  title:string = 'Register';
  

  constructor(private fb: FormBuilder,
    /**  
    private authService: AuthService, 
    */
     private router: Router) { }

  ngOnInit(): void {
  }

/** 
onSubmit(user: User) {
  var isMatch = this.isPasswordMatch();
  if (isMatch) {
    this.authService.register(user).subscribe(
      (response: User) => {
        this.router.navigate(['/login'])
      },
      (errorResponse: HttpErrorResponse) => {
        console.log(errorResponse);
      }
    )
    
  }
}


isPasswordMatch() {
 
  var pass1 = this.registerForm.get('password').value;
  var pass2 = this.registerForm.get('password2').value;

  if (pass1 !== pass2) {
    return false;
  } 

  return true;
  
}
*/

}