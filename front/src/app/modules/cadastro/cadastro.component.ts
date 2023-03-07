import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Processo } from '@interface/processo.model';
import { AvisoService } from '@services/aviso.service';
//import { ConsultaService } from '@services/consulta.service';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent implements OnInit {

  processo: Processo = new Processo();

  processoFormGroup: FormGroup;

  precatorioFormGroup: FormGroup;
  credorFormGroup: FormGroup;
  devedorFormGroup: FormGroup;
  magistradoFormGroup: FormGroup;


  isEditable = false;

  tipos = {
    cod_regime_pagamento: [
      { valor: 'C', descricao: 'Comum' },
      { valor: 'E', descricao: 'Especial' }
    ],
    cod_esfera_ente_devedor: [
      { valor: 'F', descricao: 'Federal' },
      { valor: 'E', descricao: 'Estadual' },
      { valor: 'M', descricao: 'Municipal' }
    ],
    cod_tipo_entidade_devedora: [
      { valor: 'D', descricao: 'Administração Direta' },
      { valor: 'I', descricao: 'Administração Indireta' }
    ],
    cod_tipo_despesas: [
      { valor: '11', descricao: 'Natureza alimentícia. Salários, vencimentos, proventos, pensões e suas complementações (servidores da adm. Direta da União)' },
      { valor: '12', descricao: 'Natureza alimentícia - Benefícios previdenciários e indenizações por morte ou invalidez' },
      { valor: '13', descricao: 'Acidentário' },
      { valor: '21', descricao: 'Natureza não alimentícia' },
      { valor: '31', descricao: 'Desapropriações - Único imóvel residencial do credor' },
      { valor: '32', descricao: 'Desapropriações - Demais' },
    ],
    cod_natureza_valor: [
      { valor: 'OC', descricao: 'Objeto da causa julgada' },
      { valor: 'HS', descricao: 'Honorários Sucumbenciais' },
      { valor: 'HC', descricao: 'Honorários Contratuais' },
    ],
    cod_prioridades: [
      { valor: 99, descricao: 'Sem prioridade' },
      { valor: 1, descricao: 'IDADE-60 ANOS' },
      { valor: 2, descricao: 'DOENÇA GRAVE}' },
      { valor: 4, descricao: 'DEFICIÊNCIA' },
      { valor: 8, descricao: 'IDADE-80 ANOS' }
    ]

  }



  constructor(
    //protected consultaService: ConsultaService,
    private aviso: AvisoService,
    private fb: FormBuilder
  ) {

  }

  ngOnInit(): void {
    this.limparProcesso();
    this.criarFormulario();
  }


  limparProcesso() { this.processo = null; }

  selecaoProcesso(processo) {

    this.processo = processo;

  }

  criarFormulario() {


    this.precatorioFormGroup = this.fb.group({
      pag_parcela_superpreferencial: ['', Validators.required],
      cessao_credito: ['', Validators.required],
      averbacao_penhora: ['', Validators.required],
      tipo_parcela: ['', Validators.required],
      dt_parcela_incontroversa: ['', Validators.required],
    });

    /*
        this.processoFormGroup = this.fb.group({
          precatorio: this.fb.group({
            pag_parcela_superpreferencial: ['', Validators.required],
            cessao_credito: ['', Validators.required],
            averbacao_penhora: ['', Validators.required],
            tipo_parcela: ['', Validators.required],
            dt_parcela_incontroversa: ['', Validators.required],
          }),
          cessaocredito: this.fb.group({
            tipo_cessao_credito: ['', Validators.required],
            natureza_tipo_cessao_credito: ['', Validators.required],
            vl_cessao_credito: ['', Validators.required],
            parte_cessionario: ['', Validators.required]
          }),
          penhora: this.fb.group({
            vl_penhora: ['', Validators.required]
          }),
          natureza_precatorio: this.fb.group({
            tipo_titulo: ['', Validators.required],
            prec_natureza: ['', Validators.required],
            prioridades: ['', Validators.required],
            tipo_previdencia: ['', Validators.required],
            situacao_funcional_credor: ['', Validators.required],
            orgao_vinculo_credor: ['', Validators.required],
          }),
          natureza_obrigacao: this.fb.group({
            isencao_imposto_renda: ['', Validators.required],
            valores_submetidos_rra: ['', Validators.required],
            qtd_meses_rra: ['', Validators.required]
          }),
          credor: ['', Validators.required],
          devedor: ['', Validators.required],
          finalizar: this.fb.group({
            magistrado: ['', Validators.required],

          }),
        });
        */
  }


}
