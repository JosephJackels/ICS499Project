import { Component, OnInit } from '@angular/core';
import { DataServiceService } from '../service/data-service.service';
import { Login } from 'src/app/service/login';
import { User } from '../service/user';
@Component({
  selector: 'app-aboutus',
  templateUrl: './aboutus.component.html',
  styleUrls: ['./aboutus.component.css']
})
export class AboutusComponent implements OnInit {
  user!: User;
  login!: Login;

  constructor(private data:DataServiceService) {}

  ngOnInit(): void {
  }

  public getUser(){
    //change the 48 below to whatever id of user you want to get/have access to
    //right now login must be called before get user for this to work and must be the same user
    //as the id below {in this case (joe, password) is the lgoin info for user with id 48}
    return this.data.getUser(this.login.token, 48).subscribe(data => this.user = {
      username: (data as any).username,
      userID: (data as any).userID,
      password: (data as any).password
    });
  }

  public loginUser(){
    //change vals to whoever to login
    return this.data.loginUser('joe', 'password').subscribe(data => this.login = {
      username: (data as any).username,
      token: (data as any).type + ' ' + (data as any).token,
      userId: (data as any).userId
    });
  }
}

