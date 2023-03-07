import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { MatAccordion } from '@angular/material/expansion';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Processo } from '@interface/processo.model';
import { AvisoService } from '@services/aviso.service';
import { ConsultaService } from '@services/consulta.service';
import { Filtro } from '@util/filtro.util';

@Component({
  selector: 'app-consulta-processo',
  templateUrl: './consulta-processo.component.html',
  styleUrls: ['./consulta-processo.component.css']
})
export class ConsultaProcessoComponent implements OnInit {

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatAccordion) accordion: MatAccordion;

  dataSource: MatTableDataSource<Processo>;
  processo: Processo = new Processo();

  @Output('onClick') private onClick: EventEmitter<Processo> = new EventEmitter();
  @Output('onClean') private onClean: EventEmitter<Processo> = new EventEmitter();

  filtro: Filtro = new Filtro();
  textoBusca: string;

  columnsToDisplay: string[] = [
    'processo',
    'lotacao',
    'classe',
    'rito'
  ];

  clickedRows = new Set<Processo>();

  poloAtivo: any[] = [];
  poloPassivo: any[] = [];

  constructor(
    protected consultaService: ConsultaService,
    private aviso: AvisoService,
  ) { }

  pesquisarProcesso() {

    this.filtro.valor = this.textoBusca;

    try {
      this.consultaService.buscarProcesso(this.filtro).subscribe(resultado => {

        if (!resultado.dados) {
          this.aviso.putError('Nenhum processo encontrado para o filtro informado.', 10000);
        }

        this.montaProcesso(resultado);

      });

    } catch (erro) {
      this.aviso.putError(erro.message, 10000);
    }

  }

  montaProcesso(resultado) {

    // monta array com base na estrutura de dados do retorno;
    let data = resultado.dados.autos;
    let processos = [];

    data.forEach(proc => {

      let processo = new Processo();

      console.log('proc', proc);

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

      processos.push(processo);
    });

    this.dataSource = new MatTableDataSource(processos);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;

  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
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
    this.dataSource = null;
    this.processo = null;
    this.onClean.emit(null);
  }

  ngOnInit(): void {
    this.limpar();
  }

}
