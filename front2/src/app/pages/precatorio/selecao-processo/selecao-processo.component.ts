import { AvisoService } from './../../../services/aviso.service';
import { Filtro } from './../../../utils/filtro.util';
import { Processo } from './../../../interfaces/processo.model';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ConsultaService } from './consulta.service';

@Component({
  selector: 'app-selecao-processo',
  templateUrl: './selecao-processo.component.html',
  styleUrls: ['./selecao-processo.component.scss']
})
export class SelecaoProcessoComponent implements OnInit {

  @Output('onClick') private onClick: EventEmitter<Processo> = new EventEmitter();
  @Output('onClean') private onClean: EventEmitter<Processo> = new EventEmitter();

  constructor(
    private consultaService: ConsultaService,
    private aviso: AvisoService
    ) { }

  processo: Processo = new Processo();
  processos: Processo[] = [];

  filtro = new Filtro();


  linhaSelecionada = new Set<Processo>();

  pesquisarProcesso() {

    try {
      this.consultaService.buscarProcesso(this.filtro).subscribe(resultado => {

        if (!resultado.dados) {
          this.aviso.putError('Nenhum processo encontrado para o filtro informado.');
          throw new Error("Nenhum processo encontrado para o filtro informado");
        }
        this.montaProcesso(resultado);
      });

    } catch (erro) {
      this.aviso.putError(erro.message);
      throw new Error(erro.message);
    }

  }

  montaProcesso(resultado) {

    // monta array com base na estrutura de dados do retorno;
    let data = resultado.dados.autos;

    this.processos = [];

    data.forEach(proc => {

      let processo = new Processo();

      processo.id = proc.cabecalho.id;
      processo.numero = proc.cabecalho.numero_cnj;
      processo.orgao = proc.cabecalho.lotacao;
      processo.classe = (proc.cabecalho.cnj_classe && proc.cabecalho.cnj_classe.length) ? proc.cabecalho.cnj_classe[0].descricao : proc.cabecalho.classe;
      processo.assunto = (proc.cabecalho.cnj_assunto && proc.cabecalho.cnj_assunto.length) ? proc.cabecalho.cnj_assunto[0].descricao : 'Não informado';
      processo.comarca = proc.cabecalho.comarca;
      processo.rito = proc.cabecalho.rito;
      processo.valor = proc.cabecalho.valor_causa;
      processo.dt_distribuicao = proc.cabecalho.dt_distribuicao;
      processo.partes = proc.cabecalho.partes;
      processo.segredo = proc.cabecalho.segredo_justica;

      this.processos.push(processo);
    });


  }

  selecaoLinhaProcesso(linha: any) {

    this.processo = linha;

    this.processo.polo_ativo = this.processo.partes.filter(pa => pa.tipo_parte == 0);
    this.processo.polo_passivo = this.processo.partes.filter(pa => pa.tipo_parte == 1);

    this.processo.descricao_detalhe = this.processo.polo_ativo[0].nome + (this.processo.polo_ativo.length === 1 ? '' : ' e outros') + ' X ' + this.processo.polo_passivo[0].nome

    this.onClick.emit(this.processo);
  }

  limpar() {
    this.filtro = new Filtro();
    this.processos = [];
    this.processo = null;
    this.onClean.emit(null);
  }

  ngOnInit(): void {
    this.limpar();
  }

}
