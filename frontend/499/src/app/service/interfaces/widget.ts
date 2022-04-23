import { Payload } from "./payload";

export interface Widget{
    widgetID: number;
    query: string;
    type: string;
    payload: Payload;
}