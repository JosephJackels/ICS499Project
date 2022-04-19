import { Component, OnInit } from '@angular/core';
import { MatDialogRef} from '@angular/material/dialog'

@Component({
  selector: 'app-login-failed-dialog',
  templateUrl: './login-failed-dialog.component.html',
  styleUrls: ['./login-failed-dialog.component.css']
})
export class LoginFailedDialogComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<LoginFailedDialogComponent>) { }

  ngOnInit(): void {
  }
  closeDialog(){
    this.dialogRef.close();
}

}
