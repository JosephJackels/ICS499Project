import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  navTitle:string = 'Weather Dashboard';
  constructor(private router: Router) { }
  calendar_widgets: number[] = [];
  weather_widgets: number[] = [];
  stock_widgets: number[] = [];


  ngOnInit(): void {
    console.log("nginit in home");

    //check if token exists

    //if it does -> call dashbaord info
    
    //else redirect to login
  }

  addWidget() {
    if (this.calendar_widgets.length == 0){
      this.calendar_widgets.push(0);
    }
  }

  removeWidget(CalendarData: any){
    if (this.calendar_widgets.length == 1){
      this.calendar_widgets.pop();
    }  
  }

  addWeather() {
    this.weather_widgets.push(0);
  }

  removeWeather(WeatherData:any){
    this.weather_widgets.pop();
  }

  addStocks(){
    this.stock_widgets.push(0);
  }

  removeStocks(StockData:any){
    this.stock_widgets.pop();
  }
}
