import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Processo } from '@interface/processo.model';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.scss']
})
export class CadastroComponent implements OnInit {

  constructor(private fb: FormBuilder) { }

  processo: Processo;
  precatorioForm: FormGroup;
  tipoPrecatorio: string = '';

  private valorSalarioMin: number = 1200;

  selecaoProcesso(processo) {
    this.processo = processo;

    this.definirTipo('uniao', processo.valor);

    this.builderForm();
  }

  // regra para definir se é precatório ou rpv
  definirTipo(ente, valor) {
    let parametroCalculo: number;
    let valorCalculado: number;
    if (ente == 'uniao') {
      parametroCalculo = 60;
    } else if (ente == 'estado') {
      parametroCalculo = 40;
    } else if (ente == 'municipio') {
      parametroCalculo = 20;
    }
    valorCalculado = parametroCalculo * this.valorSalarioMin;

    this.tipoPrecatorio = 'RPV';

    if (valorCalculado >= valor) {
      this.tipoPrecatorio = 'OR';
    }
  }


  limparProcesso() { this.processo = null; }



  builderForm() {
    this.precatorioForm = this.fb.group({
      magistrado_id: ['', Validators.required],
      tipo_titulo: ['', Validators.required],
      natureza_credito: ['', Validators.required],
      tipo_previdencia: ['', Validators.required],
      tipo_orbigacao: ['', Validators.required],
      desc_tipo_obrigacao: ['', Validators.required],

      dt_ajuizamento: ['', Validators.required],
      dt_decurso_prazo: ['', Validators.required],
      dt_transito_julgado_conhecimento: ['', Validators.required],
      dt_transito_julgado_embargos: ['', Validators.required],

      credor_id: ['', Validators.required],
      credor_adv_id: ['', Validators.required],
      credor_natureza_qualificacao: ['', Validators.required],

      credito_valor_bruto: ['', Validators.required],
      credito_dt_base: ['', Validators.required],
      credito_dt_juros: ['', Validators.required],
      credito_indice: ['', Validators.required],
      credito_juros: ['', Validators.required],
      credito_debito_compensado: ['', Validators.required],
      credito_dt_base_comp: ['', Validators.required],
      credito_indice_comp: ['', Validators.required],
      credito_juros_comp: ['', Validators.required],
      credito_valor_precatorio: [''],
      credito_natureza_debito_compensado_id: [''],
      credito_espec_debito_compensado: [''],

      avp_pagamento_valor_pago: [''],
      avp_pagamento_dt_pago: [''],
      avp_pagamento_observacao: [''],

      dp_valor_pagar: [''],
      dp_status: [''],
      dp_valor_pago: [''],
      dp_dt_pagamento: [''],
      dp_credor_id: [''],
      dp_credito_pref_id: [''],
      dp_observacao: [''],

      anexo: [''],

      atualiza_natureza_credito_id: ['']

    });
  }

  resetForm(e: MouseEvent): void {
    e.preventDefault();
    this.precatorioForm.reset();
    for (const key in this.precatorioForm.controls) {
      if (this.precatorioForm.controls.hasOwnProperty(key)) {
        this.precatorioForm.controls[key].markAsPristine();
        this.precatorioForm.controls[key].updateValueAndValidity();
      }
    }
  }

  submitForm(): void {
    console.log('submit', this.precatorioForm.value);
  }

  ngOnInit(): void {
    this.limparProcesso();
  }

  tipos = {
    cod_tipo_titulo: [
      { valor: 'J', descricao: 'Judicial' },
      { valor: 'E', descricao: 'Extrajudicial' }
    ],
    cod_natureza_credito: [
      { valor: 'A', descricao: 'Alimentar' },
      { valor: 'C', descricao: 'Comum' }
    ],
    cod_prioridades: [
      { valor: 0, descricao: 'Sem prioridade' },
      { valor: 1, descricao: 'IDADE-60 ANOS' },
      { valor: 2, descricao: 'DOENÇA GRAVE}' },
      { valor: 4, descricao: 'DEFICIÊNCIA' },
      { valor: 8, descricao: 'IDADE-80 ANOS' }
    ],
    cod_tipo_previdencia: [
      { valor: 0, descricao: 'Não se aplica' },
      { valor: 1, descricao: 'INSS' },
      { valor: 2, descricao: 'AMPREV' },
      { valor: 3, descricao: 'MACAPAPREV' },
      { valor: 4, descricao: 'SANPREV' }
    ],
    cod_tabela_obrigacao: [
      { valor: 1, descricao: 'Administrativa' },
      { valor: 2, descricao: 'Constitucional' },
      { valor: 3, descricao: 'Tributária' },
      { valor: 4, descricao: 'Civil' },
      { valor: 5, descricao: 'Trabalhista' },
      { valor: 6, descricao: 'Acidente de Trabalho' },
      { valor: 7, descricao: 'Indenização por desapropriação de imóvel residencial' },
      { valor: 8, descricao: 'Indenização por desapropriação de imóvel residencial - A desapropriação se enquadra no art. 78, do ADCT' },
    ],


    cod_tabela_qualificacao: [
      { valor: 1, descricao: 'Credor Principal' },
      { valor: 2, descricao: 'Credor Principal - Destacado de Litisconsórcio' },
      { valor: 3, descricao: 'Advogado - Honor. Contratuais' },
      { valor: 4, descricao: 'Perito - Honorários' },
      { valor: 5, descricao: 'Espólio' },
      { valor: 6, descricao: 'Menor' },
      { valor: 7, descricao: 'Advogado - Honor. Sucumbenciais' },
      { valor: 8, descricao: 'Incapaz' },
      { valor: 9, descricao: 'Massa Falida' },
    ],
    cod_natureza_deb_compensado: [
      { valor: 0, descricao: 'Não possui' },
      { valor: 1, descricao: 'Tributário' },
      { valor: 0, descricao: 'Não tributário' },
    ],
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
    ]

  }

}
