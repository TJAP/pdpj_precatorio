import { NgModule } from '@angular/core';
import { Routes, RouterModule, PreloadAllModules } from '@angular/router';
import { EnvService } from './services/env.service';

//Força o carregamento das variáveis de ambiente
console.log("carregando envservice");
const env = new EnvService();

const routes: Routes = [
  {
    path: '',
    redirectTo: 'consulta',
    pathMatch: 'full'
  },
  {
    path: 'consulta',
    loadChildren: () => import('src/app/modules/tela-consulta/tela-consulta.module').then(m => m.TelaConsultaModule)
  },
  {
    path: 'cadastro',
    loadChildren: () => import('src/app/modules/cadastro/cadastro.module').then(m => m.CadastroModule)
  },
  { path: '**', redirectTo: 'not-found' } // NotFoundComponent está no UiKit (assim como sua rota)
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { paramsInheritanceStrategy: 'always' , preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
