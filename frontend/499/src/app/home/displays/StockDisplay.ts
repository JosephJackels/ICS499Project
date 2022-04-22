export class StockDisplay{
    public name:string;
    public current_price:number;
    public daily_high: number;
    public daily_low: number;
    public volume: number;
    public company: string; 
    public widgetId: number;

    public constructor(data: any, widgetId: number){
        //this might not work immediately and may require some tweaking in regards to accessing the data that 'stock' is
        let stock = JSON.parse(data);
        this.name = stock.quoteResponse.result[0].symbol;
        this.current_price = stock.quoteResponse.result[0].regularMarketPrice;
        this.daily_high = stock.quoteResponse.result[0].regularMarketDayHigh;
        this.daily_low = stock.quoteResponse.result[0].regularMarketDayLow;
        this.volume = stock.quoteResponse.result[0].regularMarketVolume;
        this.company = stock.quoteResponse.result[0].shortName;
        this.widgetId = widgetId;
    }
}