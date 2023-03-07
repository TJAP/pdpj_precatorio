import { CommonModule, registerLocaleData } from '@angular/common';
import pt from '@angular/common/locales/pt';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DashboardOutline, FormOutline, MenuFoldOutline, MenuUnfoldOutline } from '@ant-design/icons-angular/icons';
import { NzAvatarModule } from 'ng-zorro-antd/avatar';
import { NzBackTopModule } from 'ng-zorro-antd/back-top';
import { NzBreadCrumbModule } from 'ng-zorro-antd/breadcrumb';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzCardModule } from 'ng-zorro-antd/card';
import { NzCollapseModule } from 'ng-zorro-antd/collapse';
import { NzDescriptionsModule } from 'ng-zorro-antd/descriptions';
import { NzDividerModule } from 'ng-zorro-antd/divider';
import { NzGridModule } from 'ng-zorro-antd/grid';
import { NZ_I18N, pt_BR } from 'ng-zorro-antd/i18n';
import { NZ_ICONS, NzIconModule } from 'ng-zorro-antd/icon';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzListModule } from 'ng-zorro-antd/list';
import { NzMenuModule } from 'ng-zorro-antd/menu';
import { NzMessageModule } from 'ng-zorro-antd/message';
import { NzNotificationModule } from 'ng-zorro-antd/notification';
import { NzPageHeaderModule } from 'ng-zorro-antd/page-header';
import { NzPopoverModule } from 'ng-zorro-antd/popover';
import { NzRadioModule } from 'ng-zorro-antd/radio';
import { NzSpaceModule } from 'ng-zorro-antd/space';
import { NzSpinModule } from 'ng-zorro-antd/spin';
import { NzTableModule } from 'ng-zorro-antd/table';
import { NzTabsModule } from 'ng-zorro-antd/tabs';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzProgressModule } from 'ng-zorro-antd/progress';
import { NzSelectModule } from 'ng-zorro-antd/select';

import { SelecaoProcessoComponent } from './../pages/precatorio/selecao-processo/selecao-processo.component';
import { SpinnerComponent } from './spinner/spinner.component';

registerLocaleData(pt);

const icons = [MenuFoldOutline, MenuUnfoldOutline, DashboardOutline, FormOutline];

@NgModule({
  declarations: [
    SpinnerComponent,
    SelecaoProcessoComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    NzFormModule,
    NzProgressModule,
    NzIconModule,
    NzLayoutModule,
    NzMenuModule,
    NzMessageModule,
    NzNotificationModule,
    NzDividerModule,
    NzGridModule,
    NzSpaceModule,
    NzAvatarModule,
    NzPageHeaderModule,
    NzPopoverModule,
    NzDescriptionsModule,
    NzBreadCrumbModule,
    NzCardModule,
    NzRadioModule,
    NzInputModule,
    NzButtonModule,
    NzTableModule,
    NzListModule,
    NzSpinModule,
    NzCollapseModule,
    NzBackTopModule,
    NzTabsModule,
    NzSelectModule
  ],
  exports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    NzFormModule,
    NzProgressModule,
    NzIconModule,
    NzLayoutModule,
    NzMenuModule,
    NzMessageModule,
    NzNotificationModule,
    NzDividerModule,
    NzGridModule,
    NzSpaceModule,
    NzAvatarModule,
    NzPageHeaderModule,
    NzPopoverModule,
    NzDescriptionsModule,
    NzBreadCrumbModule,
    NzCardModule,
    NzRadioModule,
    NzInputModule,
    NzButtonModule,
    NzTableModule,
    NzListModule,
    NzSpinModule,
    NzCollapseModule,
    NzBackTopModule,
    SpinnerComponent,
    NzTabsModule,
    NzSelectModule,
    SelecaoProcessoComponent
  ],
  providers: [
    { provide: NZ_I18N, useValue: pt_BR },
    { provide: NZ_ICONS, useValue: icons }
  ]
})
export class SharedModule {
}
