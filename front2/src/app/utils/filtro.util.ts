
export class Filtro {
  tipo: TipoFiltro = TipoFiltro.numeroProcesso;
  valor:string = '0029043-23.2020.8.03.0001';
}

export enum TipoFiltro {

  numeroProcesso = 'numero_unico',
  nomeParte = 'nome_parte'
}
