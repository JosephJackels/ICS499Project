import { Component, OnInit } from '@angular/core';
import { DataServiceService } from '../service/data-service.service';

@Component({
  selector: 'app-aboutus',
  templateUrl: './aboutus.component.html',
  styleUrls: ['./aboutus.component.css']
})
export class AboutusComponent implements OnInit {
  user!: User;

  constructor(private data:DataServiceService) {}

  ngOnInit(): void {}

  public getUser(){
    this.user = this.data.getUser();
  }
}

export interface User{
  userID: number;
  username: string;
  password: string;
}

