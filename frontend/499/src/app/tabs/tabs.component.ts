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
          path: 'login',
          label: 'Log In'
  },
  {
    path: 'new-user',
    label: 'Sign Up'
  }
]

  constructor() { }

  ngOnInit(): void {
  }

}
