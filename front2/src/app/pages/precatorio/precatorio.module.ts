import { AppModule } from './../../app.module';
import { NgModule } from '@angular/core';

import { PrecatorioRoutingModule } from './precatorio-routing.module';
import { CadastroComponent } from './cadastro/cadastro.component';
import { CommonModule } from '@angular/common';
import { ListaComponent } from './lista/lista.component';
import { RelatorioComponent } from './relatorio/relatorio.component';
import { SharedModule } from '@shared/shared.module';
import { SelecaoProcessoComponent } from './selecao-processo/selecao-processo.component';


@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    PrecatorioRoutingModule,

  ],
  declarations: [
    CadastroComponent,
    ListaComponent,
    RelatorioComponent
  ],
  exports: []
})
export class PrecatorioModule { }
