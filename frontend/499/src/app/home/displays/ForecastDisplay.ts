export class ForecastDisplay {
    public name: string;
    public country: string;
    public temps: number[];
    public descrips: string[];
    public widgetId: number;

    public constructor(forecast: any, widgetId: number) {
        let data = JSON.parse(forecast);
        this.name = data.city.name;
        this.country = data.city.country;
        this.temps = [0,0,0]
        this.descrips = ['','','']
        this.widgetId = widgetId;
        for (let i = 0; i < 3; i++) {
            this.temps[i] = data.list[i].main.temp;
            this.descrips[i] = data.list[i].weather[0].description; 
        }
    }
}