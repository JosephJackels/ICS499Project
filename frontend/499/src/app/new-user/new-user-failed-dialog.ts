import { Component } from "@angular/core";
import { MatDialogRef} from '@angular/material/dialog'

@Component({
    selector: 'new-user-failed-dialog',
    templateUrl: './new-user-failed-dialog.html'
})
export class NewUserFailedDialog {
    constructor(
        public dialogRef: MatDialogRef<NewUserFailedDialog>
    ){}

    closeDialog(){
        this.dialogRef.close();
    }
}