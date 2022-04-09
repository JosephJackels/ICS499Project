import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormArray, FormControl, FormGroup } from '@angular/forms';


@Component({
  selector: 'app-weather',
  templateUrl: './weather.component.html',
  styleUrls: ['./weather.component.css']
})
export class WeatherComponent implements OnInit {
  WeatherData:any;
  form!: FormGroup;

  constructor() { }

  @Output() delete: EventEmitter<string> = new EventEmitter();

  ngOnInit(): void {
    this.getWeatherData();
    this.form = new FormGroup({
      widget: new FormArray([
        new FormGroup({
          city: new FormControl('')
        })
      ])
    });
  }
 
  get widget(): FormArray{
    return this.form.get('widget') as FormArray;
  }

  getWeatherData() {
    let data = JSON.parse('{"coord":{"lon":-0.1257,"lat":51.5085},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"base":"stations","main":{"temp":280,"feels_like":276.15,"temp_min":277.97,"temp_max":281.21,"pressure":1031,"humidity":66},"visibility":10000,"wind":{"speed":6.69,"deg":90},"clouds":{"all":0},"dt":1647739724,"sys":{"type":2,"id":2019646,"country":"GB","sunrise":1647756215,"sunset":1647799947},"timezone":0,"id":2643743,"name":"London","cod":200}')
    this.setWeatherData(data);
  }

  setWeatherData(data: any){
    this.WeatherData = data;
  }

  removeSelf(){
    this.delete.emit(this.WeatherData.name);
    console.log("weather class " + this.WeatherData.name);
  }

  onSubmit(){
    this.widget.push(
      new FormGroup({
        city: new FormControl('')
      })
    );
    this.WeatherData.name = this.form.value['widget'][0]['city'];
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
}
