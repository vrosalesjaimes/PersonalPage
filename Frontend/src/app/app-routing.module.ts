import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PanelComponent } from './components/admin/panel/panel.component';
import { PanelClientComponent } from './components/client/panel-client/panel-client.component';

const routes: Routes = [
  { path: '', component: PanelClientComponent, pathMatch: 'full'},
  { path: 'admin/panel', component: PanelComponent, pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
