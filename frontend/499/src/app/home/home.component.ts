import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Dashboard } from '../service/dashboard';
import { DataServiceService } from '../service/data-service.service';
import { Widget } from '../service/widget';
import { WeatherComponent } from '../widgets/weather/weather.component';
import { WeatherDisplay } from './WeatherDisplay';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  navTitle:string = 'Weather Dashboard';
  dashboard!: Dashboard;
  calendar_widgets: any[] = [0];
  weather_widgets: WeatherDisplay[] = [];
  stock_widgets: Widget[] = [];

  constructor(private router: Router, private data:DataServiceService) { }

  ngOnInit(): void {
    console.log("nginit in home");

    //check if token exists
    if(localStorage.getItem("token") != null){
      this.data.getDashboardForUser(localStorage.getItem("token")!, localStorage.getItem("userId")!).subscribe(data => {
        this.dashboard = {
          dashboardId: (data as any).dashboardID,
          widgetList: ((data as any).widgetList)
        };
        console.log(this.dashboard);
        this.populateWidgets();
      });
    }
    //if it does -> call dashbaord info
    
    //else redirect to login
  }

  populateWidgets(){
    this.dashboard.widgetList.forEach(widget => {
      console.log("starting to populate lists");
      switch(widget.type) { 
        case "currentWeather": {
          this.addWeather(widget.payload.jsonResponse);
          break;
        }
        case "forecastWeather": {
          this.addWeather("");
          break;
        }
        case "stock": {
          this.addStocks(widget);
          break;
        }
        default: { 
           console.error("Widget type: " + widget.type + " does not exist!");
           break; 
        }
      } 
    });
  }

  addWidget(): void {
    if (this.calendar_widgets.length == 0){
      this.calendar_widgets.push(0);
    }
  }

  removeWidget(CalendarData: any){
    if (this.calendar_widgets.length == 1){
      this.calendar_widgets.pop();
    }  
  }

  addWeather(widget: string) {
    let obj = new WeatherDisplay(widget);
    this.weather_widgets.push(obj);
  }

  removeWeather(WeatherData:any){
    this.weather_widgets.pop();
  }

  addStocks(widget: Widget){
    this.stock_widgets.push(widget);
  }

  removeStocks(StockData:any){
    this.stock_widgets.pop();
  }
}
