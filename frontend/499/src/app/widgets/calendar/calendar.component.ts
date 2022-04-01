import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent implements OnInit {
  CalendarData: any;
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
}
