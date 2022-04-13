export class WeatherDisplay {
    public name: string;
    public temp: string;
    public country: string;



    public constructor(weather: any) {
        let data = JSON.parse(weather);
        this.name = data.name;
        this.temp = data.main.temp;
        this.country = data.sys.country
    }
   
  }