import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  navTitle:string = 'Weather Dashboard';
  constructor() { }
  calendar_widgets = [0];
  weather_widgets = [0];


  ngOnInit(): void {
  }

  addWidget() {
    this.calendar_widgets.push(0);
  }

  removeWidget(CalendarData: any){
    this.calendar_widgets.pop();
  }

  addWeather() {
    this.weather_widgets.push(0);
  }

  removeWeather(WeatherData:any){
    this.weather_widgets.pop();
  }
}
