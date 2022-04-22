import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Dashboard } from '../service/dashboard';
import { DataServiceService } from '../service/data-service.service';
import { Widget } from '../service/widget';
import { CreateWeatherWidgetDialog } from './dialogs/create-weather-widget-dialog';
import { CreateForecastWidgetDialog } from './dialogs/create-forecast-widget-dialog';
import { StockDisplay } from './displays/StockDisplay';
import { WeatherDisplay } from './displays/WeatherDisplay';
import { ForecastDisplay} from './displays/ForecastDisplay';

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
  forecast_widgets: ForecastDisplay[] = [];
  stock_widgets: StockDisplay[] = [];

  constructor(private router: Router, private data:DataServiceService, public dialog: MatDialog) { }

  ngOnInit(): void {
    console.log("nginit in home");
    if(localStorage.getItem("token") != null){
        this.data.getDashboardForUser(localStorage.getItem("token")!, localStorage.getItem("userId")!).subscribe(data => {
          this.dashboard = {
            dashboardId: (data as any).dashboardId,
            widgetList: (data as any).widgetList
          };
          this.populateWidgets();
          //uncommment this once logout works
          //this.hideLoginAndSignUpTabs();
        },
        error => {
          console.log("Error - recieved response with code: " + error.status);
          this.router.navigate(['/login']);
          //create popup saying token expire/ user needs to login again?
        }); 
    } else {
      this.router.navigate(['/login']);
    }
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
            this.addWeather(widget.payload.jsonResponse, widget.widgetID);
            break;
          }
          case "forecastWeather": {
            console.log(widget.payload.jsonResponse);
            this.addForecast(widget.payload.jsonResponse, widget.widgetID);
            break;
          }
          case "stock": {
            this.addStocks(widget.payload.jsonResponse, widget.widgetID);
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
    this.forecast_widgets = [];
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

  addWeather(widget: string, widgetId: number) {
    console.log(widget);
    let obj = new WeatherDisplay(widget, widgetId);
    this.weather_widgets.push(obj);
  }

  removeWeather(weather:any){
    for (let i = 0; i < this.weather_widgets.length; i++){
      if(this.weather_widgets[i].name==weather){
        this.removeWidgetAndDelete(this.weather_widgets[i].widgetId, this.dashboard.dashboardId);
        this.weather_widgets.splice(i,1);
      }
    }
  }

  addForecast(widget: string, widgetId: any) {
    let obj = new ForecastDisplay(widget, widgetId);
    this.forecast_widgets.push(obj);
  }

  removeForecast(forecast:any){
    for (let i = 0; i < this.forecast_widgets.length; i++){
      if(this.forecast_widgets[i].name==forecast){
        this.removeWidgetAndDelete(this.forecast_widgets[i].widgetId, this.dashboard.dashboardId);
        this.forecast_widgets.splice(i,1);
      }
    }
  }

  addStocks(widget: string, widgetId: any){
    let obj = new StockDisplay(widget, widgetId);
    this.stock_widgets.push(obj);
  }

  removeStocks(StockData:any){
    this.stock_widgets.pop();
  }

  createNewWeatherWidget(){
    const dialogRef = this.dialog.open(CreateWeatherWidgetDialog, {
      width: '250px',
      data: {query: ""},
    });
    dialogRef.afterClosed().subscribe(result => {
      if(result != null){
        let inputQuery = result;
        if(!this.widgetExistsCheck(inputQuery, this.weather_widgets)){
          this.createWidget(inputQuery, 'currentWeather');
        } else {
          console.log("Widget: " + inputQuery + " already exists");
        }
      }
    });
  }
  widgetExistsCheck(query:any, list:any[]): boolean{
    let found = false;
    list.forEach(widget => {
      if(widget.name == query){
        found = true;
      }
    });
    return found;
  }
  createNewForecastWidget(){
    const dialogRef = this.dialog.open(CreateForecastWidgetDialog, {
      width: '250px',
      data: {query: ""},
    });
    dialogRef.afterClosed().subscribe(result => {
      if(result != null){
        let inputQuery = result;
        if(!this.widgetExistsCheck(inputQuery, this.forecast_widgets)){
          this.createWidget(inputQuery, 'forecastWeather');
        } else {
          console.log("Widget: " + inputQuery + " already exists");
        }
      }
    });
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

  toggleButtonActionsVisible(val: any){
    
    //traverse upwards from button to get to parent mat-card element
    let element = val.target.parentElement;
    while(element.nodeName != "MAT-CARD"){
      element=element.parentElement;
    }
    
    //get the mat-card-actiona element that is within the parent card
    element = (element.querySelector("mat-card-actions") as HTMLElement);
    
    //if there, toggle display
    if(element != null){
      let currentVis = element.style.display;
      if(currentVis != "none"){
        element.style.display = "none";
      } else {
        element.style.display = "block";
      }
    }
  }

  removeWidgetAndDelete(widgetId: any, dashboardId: any){
    this.data.removeWidgetFromDashboard(localStorage.getItem("token")!, dashboardId, widgetId).subscribe(data => {
      //response here
      this.data.deleteWidgetFromBackend(localStorage.getItem("token")!, widgetId).subscribe(data => {
      })
    })
  }

  hideLoginAndSignUpTabs(){
    let tabElements = document.querySelectorAll("nav.mat-tab-nav-bar div.mat-tab-links>a") as NodeListOf<HTMLElement>;
    tabElements.forEach(tab => {
      if(tab.getAttribute("href") == "/login" || tab.getAttribute("href") == "/new-user"){
        tab.style.display = "none";
      }
    });
  }

  toggleButtonActionsVisibile(val: any){
    let element = val.target.parentElement;
    while(element.nodeName != "MAT-CARD"){
      element=element.parentElement;
    }
    
    //get the mat-card-actiona element that is within the parent card
    element = (element.querySelector("mat-card-actions") as HTMLElement);
    
    //if there, toggle display
    if(element != null){
      let currentVis = element.style.display;
      if(currentVis != "none"){
        element.style.display = "none";
      } else {
        element.style.display = "block";
      }
    }
  }
}
