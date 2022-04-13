import { Widget } from './widget'

export interface Dashboard {
    dashboardId: number;
    widgetList: Widget[];
}