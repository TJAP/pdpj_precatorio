import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SelecaoProcessoComponent } from './selecao-processo.component';

describe('SelecaoProcessoComponent', () => {
  let component: SelecaoProcessoComponent;
  let fixture: ComponentFixture<SelecaoProcessoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SelecaoProcessoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SelecaoProcessoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
