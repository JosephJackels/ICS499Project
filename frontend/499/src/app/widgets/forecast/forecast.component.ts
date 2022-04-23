import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { HomeComponent } from 'src/app/home/home.component';
import { DataServiceService } from 'src/app/service/data-service.service';
import { Widget } from 'src/app/service/interfaces/widget';

@Component({
  selector: 'app-forecast',
  templateUrl: './forecast.component.html',
  styleUrls: ['./forecast.component.css']
})
export class ForecastComponent implements OnInit {
  ForecastData:any;
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
    let data = '{\"list\":[{\"main\":{\"temp\": 42.0},\"weather\":[{\"description\":\"broken clouds\"}],\"dt_txt\": \"2022-03-15 12:00:00\"},{\"main\":{\"temp\": 37.9},\"weather\":[{\"description\":\"overcast\"}],\"dt_txt\": \"2022-03-16 12:00:00\"},{\"main\":{\"temp\": 55.5},\"weather\":[{\"description\":\"sunny\"}],\"dt_txt\": \"2022-03-17 12:00:00\"}]}';
    this.setWeatherData(data);
  }

  setWeatherData(data: any){
    let newdata = JSON.parse(data);
    this.ForecastData = newdata;
  }

  removeSelf(){
    this.delete.emit(this.ForecastData.name);
    console.log("weather class " + this.ForecastData.name);
  }

  onSubmit(){
    this.widget.push(
      new FormGroup({
        city: new FormControl('')
      })
    );
    this.ForecastData.name = this.form.value['widget'][0]['city'];
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
