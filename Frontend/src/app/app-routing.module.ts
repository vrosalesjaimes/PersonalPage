import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PanelComponent } from './components/admin/panel/panel.component';
import { ContactComponent } from './components/client/contact/contact.component';
import { HomeComponent } from './components/client/home/home.component';
import { ArticlesComponent } from './components/client/articles/articles.component';
import { FormationComponent } from './components/client/formation/formation.component';
import { PortfolioComponent } from './components/client/portfolio/portfolio.component';
import { PostComponent } from './components/client/post/post.component';
import { ScholarProjectsComponent } from './components/client/scholar-projects/scholar-projects.component';
import { WorkExperiencesComponent } from './components/client/work-experiences/work-experiences.component';
import { CertificationsComponent } from './components/client/certifications/certifications.component';

const routes: Routes = [
  { path: '', component: HomeComponent, pathMatch: 'full'},
  { path: 'articles', component: ArticlesComponent, pathMatch: 'full'},
  { path: 'certifications', component: CertificationsComponent, pathMatch: 'full'},
  { path: 'formation', component: FormationComponent, pathMatch: 'full'},
  { path: 'portfolio', component: PortfolioComponent, pathMatch: 'full'},
  { path: 'posts', component: PostComponent, pathMatch: 'full'},
  { path: 'school-project', component: ScholarProjectsComponent, pathMatch: 'full'},
  { path: 'work-experience', component: WorkExperiencesComponent, pathMatch: 'full'},
  { path: 'contact', component: ContactComponent, pathMatch: 'full' },
  { path: 'admin/panel', component: PanelComponent, pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
