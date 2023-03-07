import { RelatorioComponent } from './relatorio/relatorio.component';
import { ListaComponent } from './lista/lista.component';
import { CadastroComponent } from './../precatorio/cadastro/cadastro.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
    {
      path: 'cadastro',
      data: {
        breadcrumb: 'Cadastro'
      },
      component: CadastroComponent,
    },
    {
      path: 'lista',
      data: {
        breadcrumb: 'Lista'
      },
      component: ListaComponent,
    },
    {
      path: 'relatorio',
      data: {
        breadcrumb: 'Relatório'
      },
      component: RelatorioComponent,
    }
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PrecatorioRoutingModule { }
