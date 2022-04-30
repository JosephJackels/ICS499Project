//object for the home component to display forecast data
export class ForecastDisplay {
    public name: string;
    public country: string;
    public calendar: string[];
    public days: string[];
    public temps: number[];
    public descrips: string[];
    public widgetId: number;
    public timezone: number;

    public constructor(forecast: any, widgetId: number) {
        let data = JSON.parse(forecast);
        this.name = data.city.name;
        this.country = data.city.country;
        this.calendar = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday']
        this.days = ['','','']
        this.temps = [0,0,0]
        this.descrips = ['','','']
        this.widgetId = widgetId;
        this.timezone = data.city.timezone;
        console.log(this.timezone);
        for (let i = 0; i < 3; i++) {
            this.days[i] = this.calendar[(Math.floor((data.list[i*8].dt-21600)/86400) + 5) % 7];
            this.temps[i] = data.list[i].main.temp;
            this.descrips[i] = data.list[i].weather[0].description; 
        }
    }
}