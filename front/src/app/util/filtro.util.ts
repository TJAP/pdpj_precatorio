
export class Filtro {
  tipo: TipoFiltro = TipoFiltro.numeroProcesso;
  valor:string = '';
}

export enum TipoFiltro {

  numeroProcesso = 'numero_unico',
  nomeParte = 'nome_parte'
}
