import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SpinnerService {

  visible = new BehaviorSubject<boolean>(false);

  constructor() { }

  show() {
    console.log('show');
    this.visible.next(true);
  }

  hide() {
    console.log('hidden');
    this.visible.next(false);
  }

}
