import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Dashboard } from '../service/dashboard';
import { DataServiceService } from '../service/data-service.service';
import { Widget } from '../service/widget';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  navTitle:string = 'Weather Dashboard';
  dashboard!: Dashboard;
  calendar_widgets: Widget[] = [];
  weather_widgets: Widget[] = [];
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
        console.log(this.dashboard.widgetList);
        this.populateWidgets();
      });
    }
    //if it does -> call dashbaord info
    
    //else redirect to login
  }

  populateWidgets(){
    this.dashboard.widgetList.forEach(widget => {
      console.log("starting to populate lists");
      console.log(widget.type);
      switch(widget.type) { 
        case "calendar": { 
           this.addWidget(widget);
           break; 
        } 
        case "currentWeather": {
          this.addWeather(widget);
          break;
        }
        case "forecastWeather": {
          this.addWeather(widget);
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

  addWidget(widget: Widget) {
    if (this.calendar_widgets.length == 0){
      this.calendar_widgets.push(widget);
    }
  }

  removeWidget(CalendarData: any){
    if (this.calendar_widgets.length == 1){
      this.calendar_widgets.pop();
    }  
  }

  addWeather(widget: Widget) {
    this.weather_widgets.push(widget);
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
