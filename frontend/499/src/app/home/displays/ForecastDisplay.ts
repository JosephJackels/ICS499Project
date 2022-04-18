export class ForecastDisplay {
    public name: string;
    public country: string;
    public temps: number[];
    public descrips: string[];

    public constructor(forecast: any) {
        let data = JSON.parse(forecast);
        this.name = data.name;
        this.country = data.sys.country;
        this.temps = [0,0,0]
        this.descrips = ['','','']
        var i: number;
        for (i = 0; i < 3; i++) {
            this.temps[i] = data.list[i].main.temp;
            this.descrips[i] = data.list[i].main.description; 
        }
    }
}