//object for the home component to display stock data
export class StockDisplay{
    public name:string;
    public current_price:number;
    public daily_high: number;
    public daily_low: number;
    public volume: number;
    public company: string; 
    public widgetId: number;

    public constructor(data: any, widgetId: number){
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