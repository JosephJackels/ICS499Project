import { Component, OnInit } from '@angular/core';
import { Itablink } from '../itablink';
import { LogoutSuccessSnackbarComponent } from './logout-success-snackbar/logout-success-snackbar.component';
import { MatSnackBar } from '@angular/material/snack-bar';
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

  constructor(public snackBar: MatSnackBar) {}

  ngOnInit(): void {
  }

  checkIfUserLoggedIn(): boolean{
    return localStorage.getItem("token") != null;
  }

  openSnackBar(){
    this.snackBar.openFromComponent(LogoutSuccessSnackbarComponent, {
      duration: 5000,
    });
  }

  showLoginAndSignUpTabs(){
    let tabElements = document.querySelectorAll("nav.mat-tab-nav-bar div.mat-tab-links>a") as NodeListOf<HTMLElement>;
    tabElements.forEach(tab => {
      if(tab.getAttribute("href") == "/login" || tab.getAttribute("href") == "/new-user"){
        tab.style.display = "";
      }
    });
  }
}
