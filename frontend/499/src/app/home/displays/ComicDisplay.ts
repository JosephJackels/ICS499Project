export class ComicDisplay{
	public title : string;
	public image : string;
	public text : string;
	public widgetId: number;

	public constructor(comic: any, widgetId: number) {
		let data = JSON.parse(comic);
		this.title = data.title;
		this.image = data.img;
		this.text = data.alt;
		this.widgetId = widgetId;
	}
}