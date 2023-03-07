import { Injectable } from "@angular/core";

@Injectable({
  providedIn: "root"
})
export class StorageService {
  private _PREFIXO = "__tjap__";
  private _SISTEMA = "precatorio";

  constructor() {}

  chave = chave => {
    return this._PREFIXO + this._SISTEMA + "_" + chave;
  };

  get = chave => {
    const chaveCompleta = this.chave(chave);

    let valor = sessionStorage.getItem(chaveCompleta);
    if (valor) {
      return JSON.parse(valor);
    }
    valor = localStorage.getItem(chaveCompleta);
    if (!valor) {
      return null;
    }
    return JSON.parse(valor);
  };

  ePermanente(chave) {
    if (!chave) {
      return false;
    }

    const permanentes = this.getPermanentes();

    return permanentes.some(item => chave.toLowerCase() === item.toLowerCase());
  }

  set = (chave, valor) => {
    if (!chave) {
      return null;
    }

    const e_permanente = this.ePermanente(chave);

    const chaveCompleta = this.chave(chave);

    const storageObj = e_permanente ? localStorage : sessionStorage;

    storageObj.setItem(chaveCompleta, JSON.stringify(valor));
  };

  clear = (chave: string = null) => {
    if (!chave) {
      sessionStorage.clear();
    }

    const e_permanente = this.ePermanente(chave);

    const chaveCompleta = this.chave(chave);

    const storageObj = e_permanente ? localStorage : sessionStorage;

    storageObj.removeItem(chaveCompleta);
  };

  getPermanentes = () => {
    return this.get("permanentes") || [];
  };
}
