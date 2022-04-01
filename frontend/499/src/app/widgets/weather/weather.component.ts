import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-weather',
  templateUrl: './weather.component.html',
  styleUrls: ['./weather.component.css']
})
export class WeatherComponent implements OnInit {
  WeatherData:any;
  constructor() { }

  @Output() delete: EventEmitter<string> = new EventEmitter();

  ngOnInit(): void {
    this.getWeatherData();
  }

  getWeatherData() {
    let data = JSON.parse('{"coord":{"lon":-0.1257,"lat":51.5085},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"base":"stations","main":{"temp":280,"feels_like":276.15,"temp_min":277.97,"temp_max":281.21,"pressure":1031,"humidity":66},"visibility":10000,"wind":{"speed":6.69,"deg":90},"clouds":{"all":0},"dt":1647739724,"sys":{"type":2,"id":2019646,"country":"GB","sunrise":1647756215,"sunset":1647799947},"timezone":0,"id":2643743,"name":"London","cod":200}')
    this.setWeatherData(data);
  }

  setWeatherData(data: any){
    this.WeatherData = data;
  }

  removeSelf(){
    console.log('This is happening')
    this.delete.emit(this.WeatherData);
  }
}