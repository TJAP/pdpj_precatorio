import { SpinnerService } from './../../../services/spinner.service';
import { Component, CUSTOM_ELEMENTS_SCHEMA, EventEmitter, OnInit, Output } from '@angular/core';
import { Processo } from '@interface/processo.model';
import { AvisoService } from '@services/aviso.service';
import { Filtro } from '@util/filtro.util';
import { NzTableFilterList, NzTableSortOrder } from 'ng-zorro-antd/table';
import { ConsultaService } from './consulta.service';

interface ColumnItem {
  name: string;
  sortOrder: NzTableSortOrder | null;
  //sortFn: NzTableSortFn<Processo> | null;
  listOfFilter: NzTableFilterList;
  //filterFn: NzTableFilterFn<Processo> | null;
  filterMultiple: boolean;
  sortDirections: NzTableSortOrder[];
}

@Component({
  selector: 'app-processo-consulta',
  templateUrl: './processo-consulta.component.html',
  styleUrls: ['./processo-consulta.component.scss']
})
export class ProcessoConsultaComponent implements OnInit {

  listOfColumns: ColumnItem[] = [
    {
      name: 'Número processo',
      sortOrder: null,
      //sortFn: (a: Processo, b: Processo) => a.numero.localeCompare(b.numero),
      sortDirections: ['ascend', 'descend', null],
      filterMultiple: true,
      listOfFilter: [],
      //filterFn: (list: string[], item: Processo) => list.some(numero => item.numero.indexOf(numero) !== -1)
    },
    {
      name: 'Órgão julgador',
      sortOrder: 'descend',
      //sortFn: (a: Processo, b: Processo) => a.orgao.localeCompare(b.orgao),
      sortDirections: ['ascend','descend', null],
      listOfFilter: [],
      //filterFn: (list: string[], item: Processo) => list.some(orgao => item.orgao.indexOf(orgao) !== -1),
      filterMultiple: true
    },
    {
      name: 'Classe',
      sortOrder: null,
      sortDirections: ['ascend', 'descend', null],
      //sortFn: (a: Processo, b: Processo) => a.classe.localeCompare(b.classe),
      //sortFn: (a: Processo, b: Processo) => a.address.length - b.address.length,
      filterMultiple: false,
      listOfFilter: [],
      //filterFn: (address: string, item: Processo) => item.address.indexOf(address) !== -1
      //filterFn: (list: string[], item: Processo) => list.some(classe => item.classe.indexOf(classe) !== -1),
    },
    {
      name: 'Rito',
      sortOrder: null,
      sortDirections: ['ascend', 'descend', null],
      //sortFn: (a: Processo, b: Processo) => a.rito.localeCompare(b.rito),
      filterMultiple: false,
      listOfFilter: [],
      //filterFn: (list: string[], item: Processo) => list.some(rito => item.rito.indexOf(rito) !== -1),
    }
  ];

  processos: Processo[];
  processo: Processo;

  radioValue = 'A';

  @Output('onClick') private onClick: EventEmitter<Processo> = new EventEmitter();
  @Output('onClean') private onClean: EventEmitter<Processo> = new EventEmitter();

  filtro: Filtro = new Filtro();

  clickedRows = new Set<Processo>();

  constructor(
    protected consultaService: ConsultaService,
    private aviso: AvisoService,
    private spinner: SpinnerService
  ) { }

  pesquisarProcesso() {

    try {
      this.spinner.show();
      this.consultaService.buscarProcesso(this.filtro).subscribe(resultado => {

        if (!resultado.dados) {
          this.aviso.putError('Nenhum processo encontrado para o filtro informado.');
        }

        this.montaProcesso(resultado);

      });

    } catch (erro) {
      this.aviso.putError(erro.message);
    }
    this.spinner.hide();

  }

  montaProcesso(resultado) {

    // monta array com base na estrutura de dados do retorno;
    let data = resultado.dados.autos;
    //let processos = [];

    data.forEach(proc => {

      let processo:Processo;

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

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    /*his.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }*/
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
