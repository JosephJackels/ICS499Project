import { Component, OnInit,  EventEmitter, Output  } from '@angular/core';
import { FormArray, FormControl, FormGroup } from '@angular/forms';


@Component({
  selector: 'app-stocks',
  templateUrl: './stocks.component.html',
  styleUrls: ['./stocks.component.css']
})
export class StocksComponent implements OnInit {
  StockData:any;
  form!: FormGroup;

  constructor() { }

  @Output() delete: EventEmitter<string> = new EventEmitter();

  ngOnInit(): void {
    this.getStockData();
    this.form = new FormGroup({
      widget: new FormArray([
        new FormGroup({
          stock: new FormControl('')
        })
      ])
    });
  }
 
  get widget(): FormArray{
    return this.form.get('widget') as FormArray;
  }

  getStockData() {
    let data = JSON.parse('{"Meta Data": { "1. Information": "Daily Prices (open, high, low, close) and Volumes", "2. Symbol": "IBM", "3. Last Refreshed": "2022-03-18", "4. Output Size": "Compact", "5. Time Zone": "US/Eastern" }, "Time Series (Daily)": { "2022-03-18": { "1. open": "127.3800", "2. high": "128.9300", "3. low": "126.3700", "4. close": "128.7600", "5. volume": "7400216" }}}');
    this.setStockData(data);
  }

  setStockData(data: any){
    this.StockData = data;
  }

  removeSelf(){
    this.delete.emit(this.StockData.name);
  }

  onSubmit(){
    this.widget.push(
      new FormGroup({
        stock: new FormControl('')
      })
    );
    this.StockData["Meta Data"]["2. Symbol"] = this.form.value['widget'][0]['stock'];
  }
}
