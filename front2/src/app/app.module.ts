import { LocationStrategy, PathLocationStrategy, registerLocaleData } from '@angular/common';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import pt from '@angular/common/locales/pt';
import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpErrorInterceptor } from '@interceptors/http-error.interceptor';
import { TokenInterceptor } from '@interceptors/token.interceptor';
import { KeycloakService } from '@services/auth/keycloak.service';
import { SharedModule } from '@shared/shared.module';
import { AppRoutingModule } from './app-routing.module';
import { PrecatorioModule } from './pages/precatorio/precatorio.module';
import { AppComponent } from './app.component';
import { AppConfigService } from './app-config.service';
import { AppConfig } from './app-config.model';
import { LoadingInterceptor } from '@interceptors/loading.interceptor';

registerLocaleData(pt);

export function inicializarAuth(kcService: KeycloakService) {
  return () => kcService.init();
}


export function initializeApp(appConfigService: AppConfigService) {
  return (): Promise<AppConfig> => appConfigService.load();
}

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    SharedModule,
    PrecatorioModule
  ],
  exports: [SharedModule],
  providers: [
    AppConfigService,
    { provide: APP_INITIALIZER, useFactory: initializeApp, deps: [AppConfigService], multi: true },
    { provide: APP_INITIALIZER, useFactory: inicializarAuth, deps: [KeycloakService], multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: HttpErrorInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: LoadingInterceptor, multi: true },
    { provide: LocationStrategy, useClass: PathLocationStrategy }
  ],
  bootstrap: [AppComponent],
  entryComponents: []
})
export class AppModule { }
