import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import { environment } from './environments/environment';

import { enableAkitaProdMode, persistState, akitaConfig } from '@datorama/akita';

if (environment.production) {
  enableProdMode();
  enableAkitaProdMode();
}

akitaConfig({ resettable: true });
persistState();

platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.error(err));
