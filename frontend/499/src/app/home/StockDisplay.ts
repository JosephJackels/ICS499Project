export class StockDisplay{
    public ticker:string;
    public current_price:number;
    public daily_high: number;
    public daily_low: number;
    public volume: number;

    public constructor(stock: any){
        //this might not work immediately and may require some tweaking in regards to accessing the data that 'stock' is
        this.ticker = stock.ticker;
        this.current_price = stock.current_price;
        this.daily_high = stock.daily_high;
        this.daily_low = stock.daily_low;
        this.volume = stock.volume;
    }
}