import { Component, Inject } from "@angular/core";
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";

export interface CreateWeatherWidgetDialogData {
    query: string;
}

@Component({
    selector: 'create-weather-widget-dialog',
    templateUrl: 'create-weather-widget-dialog.html',
})
export class CreateWeatherWidgetDialog {
    constructor(
        public dialogRef: MatDialogRef<CreateWeatherWidgetDialog>,
        @Inject(MAT_DIALOG_DATA) public data:CreateWeatherWidgetDialogData,
    ){}

    cancelDialog(){
        this.dialogRef.close();
    }
}