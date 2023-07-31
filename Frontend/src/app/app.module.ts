import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { NgxSpinnerModule } from 'ngx-spinner';
import { LoadProtectorComponent } from './components/load-protector/load-protector.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import { NavbarComponent } from './components/client/navbar/navbar.component';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import {MatSidenavModule} from '@angular/material/sidenav';
import { LogoComponent } from './components/logo/logo.component';
import { HomeComponent } from './components/client/home/home.component';
import {MatButtonModule} from '@angular/material/button';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { PanelComponent } from './components/admin/panel/panel.component';
import { PanelClientComponent } from './components/client/panel-client/panel-client.component';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    LoadProtectorComponent,
    NavbarComponent,
    LogoComponent,
    HomeComponent,
    PanelComponent,
    PanelClientComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgxSpinnerModule,
    BrowserAnimationsModule,
    MatSlideToggleModule,
    MatListModule,
    MatIconModule,
    MatSidenavModule,
    MatButtonModule,
    FontAwesomeModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
