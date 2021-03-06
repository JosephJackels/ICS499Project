import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Dashboard } from '../service/interfaces/dashboard';
import { DataServiceService } from '../service/data-service.service';
import { Widget } from '../service/interfaces/widget';
import { CreateWeatherWidgetDialog } from './dialogs/create-weather-widget-dialog';
import { CreateForecastWidgetDialog } from './dialogs/create-forecast-widget-dialog';
import { StockDisplay } from './displays/StockDisplay';
import { WeatherDisplay } from './displays/WeatherDisplay';
import { ForecastDisplay} from './displays/ForecastDisplay';
import { CreateStockWidgetDialog } from './dialogs/create-stock-widget-dialog';
import { ComicDisplay } from './displays/ComicDisplay';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  navTitle:string = 'Weather Dashboard';
  dashboard!: Dashboard;
  //lists to hold widget display objects 
  calendar_widgets: any[] = [0];
  weather_widgets: WeatherDisplay[] = [];
  forecast_widgets: ForecastDisplay[] = [];
  stock_widgets: StockDisplay[] = [];
  comic_widget: ComicDisplay[] = [];

  constructor(private router: Router, private data:DataServiceService, public dialog: MatDialog) { }

  ngOnInit(): void {
    if(localStorage.getItem("token") != null){
        this.data.getDashboardForUser(localStorage.getItem("token")!, localStorage.getItem("userId")!).subscribe(data => {
          this.dashboard = {
            dashboardId: (data as any).dashboardID,
            widgetList: (data as any).widgetList
          };
          this.populateWidgets();
          this.hideLoginAndSignUpTabs();
        },
        error => {
          console.log("Error - recieved response with code: " + error.status);
          this.router.navigate(['/login']);
          //create popup saying token expire/ user needs to login again?
        }); 
    } else {
      this.hideLogoutTab();
      this.router.navigate(['/login']);
    }
  }

  //call methods to contact backend and get data
  populateWidgets(){
    this.clearWidgetLists();
    this.showLogoutTab();
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
            this.addForecast(widget.payload.jsonResponse, widget.widgetID);
            break;
          }
          case "stock": {
            this.addStocks(widget.payload.jsonResponse, widget.widgetID);
            break;
          }
          case "comic": {
            this.addComic(widget.payload.jsonResponse, widget.widgetID);
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
    this.comic_widget = [];
  }

  //adds calendar widget, it will only let you add one
  addWidget(): void {
    if (this.calendar_widgets.length == 0){
      this.calendar_widgets.push(0);
    }
  }

  //remove calendar widget, only if there is one
  removeWidget(CalendarData: any){
    if (this.calendar_widgets.length == 1){
      this.calendar_widgets.pop();
    }  
  }

  //adds weather widget with desired data and id
  addWeather(widget: string, widgetId: number) {
    let obj = new WeatherDisplay(widget, widgetId);
    this.weather_widgets.push(obj);
  }

  //removes weather widget with the id passed into it
  removeWeather(weather:any){
    for (let i = 0; i < this.weather_widgets.length; i++){
      if(this.weather_widgets[i].widgetId==weather){
        this.removeWidgetAndDelete(this.weather_widgets[i].widgetId, this.dashboard.dashboardId);
        this.weather_widgets.splice(i,1);
      }
    }
  }

  //adds forecast widget with the desired data and id
  addForecast(widget: string, widgetId: number) {
    let obj = new ForecastDisplay(widget, widgetId);
    this.forecast_widgets.push(obj);
  }

  //removes forecast widget with the id passed into it
  removeForecast(forecast: any){
    for (let i = 0; i < this.forecast_widgets.length; i++){
      if(this.forecast_widgets[i].name==forecast){
        this.removeWidgetAndDelete(this.forecast_widgets[i].widgetId, this.dashboard.dashboardId);
        this.forecast_widgets.splice(i,1);
      }
    }
  }

  //adds stock widget with the desired data and id
  addStocks(widget: string, widgetId: any){
    let obj = new StockDisplay(widget, widgetId);
    this.stock_widgets.push(obj);
  }

  //removes stock widget with the id passed into it
  removeStocks(stockName:any){
    for (let i = 0; i < this.stock_widgets.length; i++){
      if(this.stock_widgets[i].name==stockName){
        this.removeWidgetAndDelete(this.stock_widgets[i].widgetId, this.dashboard.dashboardId);
        this.stock_widgets.splice(i,1);
      }
    }
  }

  //adds comic widget if one does not exist
   addComic(widget: string, widgetId: number) {
    if (this.comic_widget.length == 0){
      let obj = new ComicDisplay(widget, widgetId);
      this.comic_widget.push(obj);
    }
  }

  //removes comic widget if one exists
  removeComic(comic: any){
    if (this.comic_widget.length == 1){
      this.removeWidgetAndDelete(this.comic_widget[0].widgetId, this.dashboard.dashboardId);
      this.comic_widget = [];
    }  
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

  //checks to make sure the new widget is not a duplicate
  widgetExistsCheck(query:any, list:any[]): boolean{
    let found = false;
    list.forEach(widget => {
      if(widget.name == query){
        found = true;
      }
    });
    return found;
  }

  //creates a new forecast widget 
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

  //creates a new stock widget
  createNewStockWidget(){
    //todo
    //either create a new dialog or try to make the other one generic?
    const dialogRef = this.dialog.open(CreateStockWidgetDialog, {
      width: '250px',
      data: {query: ""},
    });
    dialogRef.afterClosed().subscribe(result => {
      if(result != null){
        let inputQuery = result;
        if(!this.widgetExistsCheck(inputQuery, this.stock_widgets)){
          this.createWidget(inputQuery, 'stock');
        } else {
          console.log("Widget: " + inputQuery + " already exists");
        }
      }
    });
  }

  createNewComicWidget(){
    if (this.comic_widget.length == 0)
      this.createWidget('', 'comic');
  }

  //creates a generic widget
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



  //after the widget is created, add it to the dashboard
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

  //remove widget
  removeWidgetAndDelete(widgetId: any, dashboardId: any){
    this.data.removeWidgetFromDashboard(localStorage.getItem("token")!, dashboardId, widgetId).subscribe(data => {
      //response here
      this.data.deleteWidgetFromBackend(localStorage.getItem("token")!, widgetId).subscribe(data => {
      });
    });
  }

  hideLoginAndSignUpTabs(){
    let tabElements = document.querySelectorAll("nav.mat-tab-nav-bar div.mat-tab-links>a") as NodeListOf<HTMLElement>;
    tabElements.forEach(tab => {
      if(tab.getAttribute("href") == "/login" || tab.getAttribute("href") == "/new-user"){
        tab.style.display = "none";
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

  showLoginAndSignUpTabs(){
    let tabElements = document.querySelectorAll("nav.mat-tab-nav-bar div.mat-tab-links>a") as NodeListOf<HTMLElement>;
    tabElements.forEach(tab => {
      if(tab.getAttribute("href") == "/login" || tab.getAttribute("href") == "/new-user"){
        tab.style.display = "flex";
      }
    });
  }

  showLogoutTab(){
    let tabElements = document.querySelectorAll("nav.mat-tab-nav-bar div.mat-tab-links>a") as NodeListOf<HTMLElement>;
    tabElements.forEach(tab => {
      if(tab.getAttribute("href") == "/logout"){
        tab.style.display = "flex";
      }
    });
  }
}
