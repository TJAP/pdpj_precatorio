import { Injectable } from '@angular/core';
import { NzNotificationPlacement, NzNotificationService } from 'ng-zorro-antd/notification';

@Injectable({
  providedIn: 'root'
})
export class AvisoService {

  constructor(public aviso: NzNotificationService) {
  }


  putSuccess(mensagem: string) {

    this.aviso.success('Sucesso', mensagem, {
      nzStyle: {
      },
      nzClass: 'sucesso-aviso',
      nzPlacement: 'bottomRight',
      nzDuration: 10000
    });
  }

  putError(mensagem: string) {

    this.aviso.error('Erro', mensagem, {
      nzStyle: {
      },
      nzClass: 'erro-aviso',
      nzPlacement: 'bottomRight',
      nzDuration: 10000
    });
  }
}
