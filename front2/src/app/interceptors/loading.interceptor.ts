import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SpinnerService } from '@services/spinner.service';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

@Injectable()
export class LoadingInterceptor implements HttpInterceptor {

    private totalRequests = 0;

    constructor(private spinner: SpinnerService) {}

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        this.totalRequests++;
        this.spinner.show();

        return next.handle(request).pipe(
            finalize(() => {
                this.totalRequests--;
                if (this.totalRequests <= 0) {
                    this.spinner.hide();
                }
            })
        );
    }
}
