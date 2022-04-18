import { Component, Inject } from "@angular/core";
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";

export interface CreateForecastWidgetDialogData {
    query: string;
}

@Component({
    selector: 'create-forecast-widget-dialog',
    templateUrl: 'create-forecast-widget-dialog.html',
})
export class CreateForecastWidgetDialog {
    constructor(
        public dialogRef: MatDialogRef<CreateForecastWidgetDialog>,
        @Inject(MAT_DIALOG_DATA) public data:CreateForecastWidgetDialogData,
    ){}

    cancelDialog(){
        this.dialogRef.close();
    }
}