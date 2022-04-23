import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TabsComponent } from './tabs/tabs.component';

import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatLineModule } from '@angular/material/core';
import { MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTabsModule } from '@angular/material/tabs';
import { MatToolbarModule } from '@angular/material/toolbar';
import { FormsModule } from '@angular/forms';
import { AboutusComponent } from './aboutus/aboutus.component';
import { NewUserComponent } from './new-user/new-user.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { ReactiveFormsModule } from '@angular/forms';
import { CalendarComponent } from './widgets/calendar/calendar.component';
import { WeatherComponent } from './widgets/weather/weather.component';
import { ForecastComponent } from './widgets/forecast/forecast.component';
import { StocksComponent } from './widgets/stocks/stocks.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { HttpClientModule, HttpXhrBackend } from '@angular/common/http';
import { DataServiceService } from './service/data-service.service';
import { LoginSuccessSnackbarComponent } from './login/login-success-snackbar/login-success-snackbar.component';
import { LoginFailedDialogComponent } from './login/login-failed-dialog/login-failed-dialog.component';
import { CreateWeatherWidgetDialog } from './home/dialogs/create-weather-widget-dialog';
import { CreateForecastWidgetDialog } from './home/dialogs/create-forecast-widget-dialog';

import { LogoutSuccessSnackbarComponent } from './logout/logout-success-snackbar/logout-success-snackbar.component';
import { CreateStockWidgetDialog } from './home/dialogs/create-stock-widget-dialog';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    TabsComponent,
    AboutusComponent,
    NewUserComponent,
    LoginComponent,
    LogoutComponent,
    CalendarComponent,
    WeatherComponent,
    ForecastComponent,
    StocksComponent,
    LoginSuccessSnackbarComponent,
    LoginFailedDialogComponent,
    CreateWeatherWidgetDialog,
    CreateForecastWidgetDialog,
    CreateStockWidgetDialog,
    LoginSuccessSnackbarComponent,
    LogoutSuccessSnackbarComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,  
    FormsModule,
    MatListModule,
    MatTabsModule,
    MatSnackBarModule,
    MatDialogModule,
    MatCardModule,
    MatIconModule,
    MatSlideToggleModule,
    MatButtonModule,
    MatLineModule,
    MatInputModule,
    MatToolbarModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    HttpClientModule,
  ],
  providers: [DataServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }