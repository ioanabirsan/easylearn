import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/Observable/of';
import { environment } from '../../environments/environment';
import { CookieService } from 'ngx-cookie-service';

@Injectable()
export class AuthService {
  private api = `${environment.api}/user`;
  constructor(private http: HttpClient, private _cookie: CookieService) {
    let u = this._cookie.get('all_user');
    if (u) {
      u = JSON.parse(u);
      this.redirect = u['data'].UserPermission.permission;
    }
  }
  redirect = '';

  login(user): Observable<any> {
    return this.http.post(`${this.api}/login`, {
      email: user.username,
      password: user.password
    });
  }

  register(user): Observable<any> {
    return this.http.post(`${this.api}/create`, {
      username: user.username,
      email: user.email,
      password: user.password,
      firstname: user.firstname,
      lastname: user.lastname,
      serial: user.serial,
      access: user.access
    });
  }

  setSession(response) {
    this.redirect = response.data.UserPermission.permission;
    this._cookie.set('logged', 'true');
    this._cookie.set('session', response.token);
    this._cookie.set('user', response.username);
    this._cookie.set('all_user', JSON.stringify(response));
  }

  destroySession() {
    this._cookie.delete('logged');
    this._cookie.delete('session');
    this._cookie.delete('user');
    this._cookie.delete('all_user');
  }
}