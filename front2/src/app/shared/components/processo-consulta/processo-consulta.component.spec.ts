import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProcessoConsultaComponent } from './processo-consulta.component';

describe('ProcessoConsultaComponent', () => {
  let component: ProcessoConsultaComponent;
  let fixture: ComponentFixture<ProcessoConsultaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProcessoConsultaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProcessoConsultaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
