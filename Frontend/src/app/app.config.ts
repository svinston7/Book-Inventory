import { ApplicationConfig, importProvidersFrom, provideZoneChangeDetection } from '@angular/core';
import { provideRouter, RouterModule } from '@angular/router';
import { provideHttpClient,withInterceptorsFromDi } from '@angular/common/http';
import { routes } from './app.routes';
import { provideClientHydration, withEventReplay } from '@angular/platform-browser';
import { JwtModule } from '@auth0/angular-jwt';
import { FormsModule } from '@angular/forms';
export function tokenGetter() {
  return localStorage.getItem("token");
}
export const appConfig: ApplicationConfig = {
  providers: [
    FormsModule,
    provideZoneChangeDetection({ eventCoalescing: true }), 
    provideRouter(routes), 
    provideClientHydration(withEventReplay()),
    provideHttpClient(withInterceptorsFromDi()), importProvidersFrom(
      RouterModule,
      JwtModule.forRoot({
          config: {
              tokenGetter: tokenGetter,
              allowedDomains: ["localhost:9090"],
              disallowedRoutes: [""],
          },
      }),
  ),
  provideHttpClient(
      withInterceptorsFromDi()
  ),

  ]
};
