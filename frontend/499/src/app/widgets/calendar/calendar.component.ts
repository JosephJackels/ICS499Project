import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent implements OnInit {
  CalendarData: any;
  months: string[]  = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];

  constructor() { }
  @Output() delete: EventEmitter<string> = new EventEmitter();

  ngOnInit(): void {
    this.getCalendarData();
  }

  getCalendarData(){
    let dateTime = new Date();
    this.CalendarData = dateTime;
  }
  
  removeSelf(){
    this.delete.emit(this.CalendarData);
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
