
export class Processo {
  id: string;
  numero: string;
  comarca: string;
  orgao: string;
  classe: string;
  assunto: string;
  arquivado: boolean;
  rito: string;
  segredo: boolean;
  valor: string;
  dt_distribuicao: string;
  partes: any[];
  polo_ativo: any;
  polo_passivo: any;

  descricao_detalhe: string;
}
