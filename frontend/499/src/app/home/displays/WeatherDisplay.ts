export class WeatherDisplay {
    public name: string;
    public temp: number;
    public country: string;
    public temp_min: number;
    public temp_max: number;
    public feels_like: number;
    public widgetId: number;

    public constructor(weather: any, widgetId: any) {
        let data = JSON.parse(weather);
        this.name = data.name;
        this.temp = data.main.temp;
        this.country = data.sys.country
        this.temp_max = data.main.temp_max;
        this.temp_min = data.main.temp_min;
        this.feels_like = data.main.feels_like;
        this.widgetId = widgetId;
    }
}