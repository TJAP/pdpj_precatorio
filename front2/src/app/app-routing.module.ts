import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/precatorio/cadastro' },
  {
    path: 'home',
    loadChildren:() => import('./pages/welcome/welcome.module').then(m => m.WelcomeModule)
  },
  {
    path: 'precatorio',
    loadChildren: () => import('./pages/precatorio/precatorio.module').then(m => m.PrecatorioModule),
    data: {
      breadcrumb: 'Precatorio'
    }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
