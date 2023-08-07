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
import { HttpClientModule } from '@angular/common/http';
import { ArticlesComponent } from './components/client/articles/articles.component';
import { CertificationsComponent } from './components/client/certifications/certifications.component';
import { FormationComponent } from './components/client/formation/formation.component';
import { PortfolioComponent } from './components/client/portfolio/portfolio.component';
import { PostComponent } from './components/client/post/post.component';
import { ScholarProjectsComponent } from './components/client/scholar-projects/scholar-projects.component';
import { WorkExperiencesComponent } from './components/client/work-experiences/work-experiences.component';
import { ToggleThemeComponent } from './components/toggle-theme/toggle-theme.component';

@NgModule({
  declarations: [
    AppComponent,
    LoadProtectorComponent,
    NavbarComponent,
    LogoComponent,
    HomeComponent,
    PanelComponent,
    ArticlesComponent,
    CertificationsComponent,
    FormationComponent,
    PortfolioComponent,
    PostComponent,
    ScholarProjectsComponent,
    WorkExperiencesComponent,
    ToggleThemeComponent,
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
