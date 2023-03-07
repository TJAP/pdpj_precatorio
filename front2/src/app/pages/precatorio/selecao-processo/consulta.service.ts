import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { shareReplay } from "rxjs/operators";
import { Filtro } from "@util/filtro.util";
import { EnvService } from "@services/env.service";

@Injectable({
    providedIn: 'root'
})
export class ConsultaService {

    constructor (private http: HttpClient, private env: EnvService) {}

    AUTH_TOKLEN = '778683a6-f260-132f-eac2-109537b0e7e3';

    listar(filtro: string): Observable<any[]>  {
        return this.http.get<any[]>(`${this.env.apiUrl}/consulta?filtro=${filtro}`).pipe(shareReplay());
    }

    buscarProcesso(filtro: Filtro): Observable<any> {

      if (!filtro) {
        throw new Error(
          'Você precisa informar um valor para busca do Auto/Processo'
        );
      }

      let httpParams = {
        "Auth-Token": this.AUTH_TOKLEN
      };

      httpParams[`${filtro.tipo}`] = filtro.valor;


      return this.http.get<any>(this.env.apiTucujuris+'/publico/buscar-autos-consulta-publica', {params: httpParams}).pipe(shareReplay());
    //return this.http.get<any>(this.env.apiTucujuris+'/tramite/buscar-autos', {params: httpParams}).pipe(shareReplay());
    }

}
