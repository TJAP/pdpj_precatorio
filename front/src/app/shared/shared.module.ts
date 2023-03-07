import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatBadgeModule } from '@angular/material/badge';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatNativeDateModule, MatRippleModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatDialogModule } from '@angular/material/dialog';
import { MatDividerModule } from '@angular/material/divider';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatMenuModule } from '@angular/material/menu';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatSortModule } from '@angular/material/sort';
import { MatStepperModule } from '@angular/material/stepper';
import { MatTableModule } from '@angular/material/table';
import { MatTabsModule } from '@angular/material/tabs';
import { RouterModule } from '@angular/router';

import { ConsultaProcessoComponent } from './components/consulta-processo/consulta-processo.component';
import { SnackMessengerComponent } from './components/snack-messenger/snack-messenger.component';
import { SpinnerComponent } from './components/spinner/spinner.component';

@NgModule({
    declarations: [
        SpinnerComponent,
        SnackMessengerComponent,
        ConsultaProcessoComponent
    ],
    imports: [
        CommonModule,
        RouterModule,
        FormsModule,
        ReactiveFormsModule,
        MatCardModule,
        MatButtonModule,
        MatMenuModule,
        MatIconModule,
        MatListModule,
        MatFormFieldModule,
        MatInputModule,
        MatDatepickerModule,
        MatSelectModule,
        MatCheckboxModule,
        MatSnackBarModule,
        MatTabsModule,
        MatExpansionModule,
        MatBadgeModule,
        MatRippleModule,
        MatDialogModule,
        MatRadioModule,
        MatNativeDateModule,
        MatTableModule,
        MatPaginatorModule,
        MatSortModule,
        MatStepperModule,
        MatDividerModule
    ],
    exports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        MatCardModule,
        MatButtonModule,
        MatMenuModule,
        MatIconModule,
        MatListModule,
        MatFormFieldModule,
        MatInputModule,
        MatDatepickerModule,
        MatSelectModule,
        MatCheckboxModule,
        MatSnackBarModule,
        MatTabsModule,
        MatExpansionModule,
        MatBadgeModule,
        MatRippleModule,
        MatDialogModule,
        MatRadioModule,
        MatNativeDateModule,
        MatTableModule,
        MatPaginatorModule,
        MatSortModule,
        MatStepperModule,
        MatDividerModule,
        //Componentes que serão usados como tags em templates precisam ser exportados
        SpinnerComponent,
        SnackMessengerComponent,
        ConsultaProcessoComponent
    ],
    entryComponents: [SnackMessengerComponent]
})
export class SharedModule {}
