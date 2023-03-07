import { LocationStrategy, PathLocationStrategy } from '@angular/common';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { APP_INITIALIZER, LOCALE_ID, NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {
  AuthModule,
  LogModule,
  NotFoundModule,
  OIDC_CONFIG,
  OidcAuthModule,
  UikitModule,
  UnauthorizedModule,
  UpdateModule,
} from '@cnj/uikit';

import { AppConfig } from './app-config.model';
import { AppConfigService } from './app-config.service';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpErrorInterceptor } from '@interceptors/http-error.interceptor';
import { TokenInterceptor } from '@interceptors/token.interceptor';
import { CadastroModule } from '@modules/cadastro/cadastro.module';
import { KeycloakService } from '@services/keycloak.service';
import { SharedModule } from '@shared/shared.module';


export function inicializarAuth(kcService: KeycloakService) {
    return () => kcService.init();
}

const configServiceFactory = (): Oidc.UserManagerSettings => {
    const authenticationSettings = AppConfigService.settings.authentication;
    return authenticationSettings;
};

export function initializeApp(appConfigService: AppConfigService) {
    return (): Promise<AppConfig> => appConfigService.load();
}

@NgModule({
  declarations: [AppComponent],
  imports: [
    UikitModule,
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    SharedModule,
    AppRoutingModule,
    LogModule,
    NotFoundModule,
    UnauthorizedModule,
    AuthModule,
    OidcAuthModule,
    AppRoutingModule,
    UpdateModule,
    CadastroModule,
    ReactiveFormsModule
  ],
  providers: [

    // Uikit
    AppConfigService,
    { provide: APP_INITIALIZER, useFactory: initializeApp, deps: [AppConfigService], multi: true },
    { provide: OIDC_CONFIG, useFactory: configServiceFactory },
    // End uikit

    { provide: APP_INITIALIZER, useFactory: inicializarAuth, deps: [KeycloakService], multi: true },

    { provide: LOCALE_ID, useValue: 'pt-BR' },

    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true },

    { provide: HTTP_INTERCEPTORS, useClass: HttpErrorInterceptor, multi: true },

    /*
    Define o uso de paths sem o caracter de fragmento/hashtag ("#"). Por padrão, o Angular cria paths no
    formato "/#path/conforme/rota". Essa abordagem tem a facilidade de que, não importa a rota escolhida
    pelo usuário, o navegador estará sempre servindo o arquivo index.html (uma vez que qualquer coisa após
    uma hashtag na URL é interpretada como uma seção dentro do mesmo arquivo HTML - é responsabilidade do
    Angular modificar o conteúdo da página, via javascript, quando a rota se modifica). Contudo, caso se
    prefira a utilização "tradicional" em que, com a mudança de uma rota/link, a URL muda "de verdade",
    é necessário garantir que o servidor HTTP utilizado para servir a aplicação saiba como lidar com isso,
    redirecionando qualquer path inexistente de volta para o arquivo index.html. No caso do nginx, utilizado
    neste projeto, é necessário que no default.conf conste a seguinte configuração:
        location / {
            try_files $uri $uri/ /index.html;
        }
    */
    { provide: LocationStrategy, useClass: PathLocationStrategy }
  ],
  bootstrap: [AppComponent],
  entryComponents: []
})
export class AppModule { }
