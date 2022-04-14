import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Dashboard } from '../service/dashboard';
import { DataServiceService } from '../service/data-service.service';
import { Widget } from '../service/widget';
import { WeatherComponent } from '../widgets/weather/weather.component';
import { CreateWeatherWidgetDialog } from './create-weather-widget-dialog';
import { StockDisplay } from './StockDisplay';
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
  stock_widgets: StockDisplay[] = [];

  constructor(private router: Router, private data:DataServiceService, public dialog: MatDialog) { }

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

    this.clearWidgetLists();
    
    console.log("starting to populate lists");
    this.dashboard.widgetList.forEach(widget => {
      this.data.getWidgetPayload(localStorage.getItem("token")!, widget.widgetID).subscribe(data => {
        widget.payload = {
          payloadId: (data as any).payloadId,
          jsonResponse: (data as any).jsonResponse,
          lastUpdatedTime: (data as any).lastUpdatedTime,
          updateFrequency: (data as any).updateFrequency
        };
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
            this.addStocks(widget.payload.jsonResponse);
            break;
          }
          default: { 
             console.error("Widget type: " + widget.type + " does not exist!");
             break; 
          }
        } 
      });
    });
  }

  clearWidgetLists() {
    this.calendar_widgets = [0];
    this.weather_widgets = [];
    this.stock_widgets = [];
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

  addStocks(widget: string){
    let obj = new StockDisplay(widget);
    this.stock_widgets.push(obj);
  }

  removeStocks(StockData:any){
    this.stock_widgets.pop();
  }

  createNewWeatherWidget(){
    const dialogRef = this.dialog.open(CreateWeatherWidgetDialog, {
      width: '250px',
      data: {query: "Enter a city"},
    });
    dialogRef.afterClosed().subscribe(result => {
      let inputQuery = result;
      this.createWidget(inputQuery, 'currentWeather');
    });
  }

  createNewForecastWidget(){
    //todo
    //either create a new dialog or try to make the other one generic?
  }

  createNewStockWidget(){
    //todo
    //either create a new dialog or try to make the other one generic?
  }

  createWidget(query: string, type: string){
    this.data.createWidget(localStorage.getItem("token")!, query, type).subscribe(data => {
      let widgetResponse: Widget = {
        widgetID: (data as any).widgetID,
        query: (data as any).query,
        type: (data as any).type,
        payload: (data as any).payload
      };
      //let dashBoard: Dashboard = this.getDashboardForCurrentUser();
      this.addWidgetToDashboard(widgetResponse.widgetID);
    });
  }


  addWidgetToDashboard(widgetId: any){
    this.data.getDashboardForUser(localStorage.getItem("token")!, localStorage.getItem("userId")).subscribe(data => {
      let dashboardResponse: Dashboard = {
        dashboardId: (data as any).dashboardID,
        widgetList: (data as any).widgetList
      };
      this.data.addWidgetToDashboard(localStorage.getItem("token")!, dashboardResponse.dashboardId, widgetId).subscribe(data => {
        this.dashboard = {
          dashboardId: (data as any).dashboardID,
          widgetList: ((data as any).widgetList)
        };
        console.log(this.dashboard);
        this.populateWidgets();
      });
    });
  }
}
