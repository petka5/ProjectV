import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {appRoutingModule} from './app.routing';
import {AppComponent} from './app.component';
import {HomeComponent} from './home';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register';
import {AlertComponent} from './_components/alert.component'

@NgModule({
            declarations: [
              AppComponent,
              HomeComponent,
              LoginComponent,
              RegisterComponent,
              AlertComponent
            ],
            imports: [
              BrowserModule,
              ReactiveFormsModule,
              HttpClientModule,
              appRoutingModule
            ],
            providers: [],
            bootstrap: [AppComponent,]
          })
export class AppModule {
}
