import { Component, Inject } from "@angular/core";
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";

export interface CreateStockWidgetDialogData {
    query: string;
}

@Component({
    selector: 'create-forecast-widget-dialog',
    templateUrl: 'create-stock-widget-dialog.html',
})
export class CreateStockWidgetDialog {
    constructor(
        public dialogRef: MatDialogRef<CreateStockWidgetDialog>,
        @Inject(MAT_DIALOG_DATA) public data:CreateStockWidgetDialogData,
    ){}

    cancelDialog(){
        this.dialogRef.close();
    }
}