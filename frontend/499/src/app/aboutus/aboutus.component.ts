import { Component, OnInit } from '@angular/core';
import { DataServiceService } from '../service/data-service.service';
import { User } from '../service/user';
@Component({
  selector: 'app-aboutus',
  templateUrl: './aboutus.component.html',
  styleUrls: ['./aboutus.component.css']
})
export class AboutusComponent implements OnInit {
  user!: User;

  constructor(private data:DataServiceService) {}

  ngOnInit(): void {
  }

  public getUser(){
    //change the 48 below to whatever id of user you want to get/have access to
    return this.data.getUser(48).subscribe({next: (value: User) => this.user = value});
  }
}

