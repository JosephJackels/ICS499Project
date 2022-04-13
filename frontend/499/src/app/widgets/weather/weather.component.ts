import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { HomeComponent } from 'src/app/home/home.component';
import { DataServiceService } from 'src/app/service/data-service.service';
import { Widget } from 'src/app/service/widget';

@Component({
  selector: 'app-weather',
  templateUrl: './weather.component.html',
  styleUrls: ['./weather.component.css']
})
export class WeatherComponent implements OnInit {
  WeatherData:any;
  form!: FormGroup;

  constructor(private data:DataServiceService) { }

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
    let data = '{\"coord\":{\"lon\":-121.9358,\"lat\":37.7021},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01n\"}],\"base\":\"stations\",\"main\":{\"temp\":281.94,\"feels_like\":278.33,\"temp_min\":280.28,\"temp_max\":283.5,\"pressure\":1020,\"humidity\":65},\"visibility\":10000,\"wind\":{\"speed\":7.72,\"deg\":260},\"clouds\":{\"all\":0},\"dt\":1649819788,\"sys\":{\"type\":2,\"id\":2016191,\"country\":\"US\",\"sunrise\":1649770591,\"sunset\":1649817599},\"timezone\":-25200,\"id\":5344157,\"name\":\"FUckkkkkk\",\"cod\":200}';
    this.setWeatherData(data);
  }

  setWeatherData(data: any){
    let newdata = JSON.parse(data);
    this.WeatherData = newdata;
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
