import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultaProcessoComponent } from './consulta-processo.component';

describe('ConsultaProcessoComponent', () => {
  let component: ConsultaProcessoComponent;
  let fixture: ComponentFixture<ConsultaProcessoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultaProcessoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultaProcessoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
