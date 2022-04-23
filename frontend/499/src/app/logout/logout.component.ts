import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';
import { LogoutSuccessSnackbarComponent } from './logout-success-snackbar/logout-success-snackbar.component';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(private router: Router, public snackBar: MatSnackBar) { }

  ngOnInit(): void {
    localStorage.clear();
    this.showLoginAndSignUpTabs();
    this.hideLogoutTab();
    this.openSnackBar();
    this.router.navigate(['/login']);
  }

  openSnackBar(){
    this.snackBar.openFromComponent(LogoutSuccessSnackbarComponent, {
      duration: 3000,
    });
  }

  showLoginAndSignUpTabs(){
    let tabElements = document.querySelectorAll("nav.mat-tab-nav-bar div.mat-tab-links>a") as NodeListOf<HTMLElement>;
    tabElements.forEach(tab => {
      if(tab.getAttribute("href") == "/login" || tab.getAttribute("href") == "/new-user"){
        tab.style.display = "flex";
      }
    });
  }

  hideLogoutTab(){
    let tabElements = document.querySelectorAll("nav.mat-tab-nav-bar div.mat-tab-links>a") as NodeListOf<HTMLElement>;
    tabElements.forEach(tab => {
      if(tab.getAttribute("href") == "/logout"){
        tab.style.display = "none";
      }
    });
  } 
}


