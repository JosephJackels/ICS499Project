import { Component, OnInit } from '@angular/core';
import { Itablink } from '../itablink';

@Component({
  selector: 'app-tabs',
  templateUrl: './tabs.component.html',
  styleUrls: ['./tabs.component.css']
})
export class TabsComponent implements OnInit {

  tabLinks:Array<Itablink> = [
  {
    path: 'home',
    label: 'Home'
  }, 
  {
    path: 'aboutus',
    label: 'About Us'
  },
  {
    path: 'new-user',
    label: 'Sign Up'
  },
  {
    path: 'login',
    label: 'Log In'
  },
  {
    path: 'logout',
    label: 'Log Out'
  }

]

  constructor() { }

  ngOnInit(): void {
  }

}
