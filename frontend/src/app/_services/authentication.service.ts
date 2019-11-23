/*
 * Developed by Petka <petar.georgiev@gmail.com>
 * Last modified 10/29/19 6:26 PM.
 * Copyright (c) 2019.
 */

import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from '@environments/environment';
import {BehaviorSubject, Observable} from 'rxjs';
import {map} from 'rxjs/operators';

import {User} from '@/_models';

@Injectable({providedIn: 'root'})
export class AuthenticationService {
  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  login(username, password) {
    const httpOptions = {
      headers: new HttpHeaders({
                                 'Authorization': 'Basic cGV0a2FjbGllbnQ6cGV0a2E=',
                                 'Content-Type': 'application/x-www-form-urlencoded'
                               }),
      withCredentials: true
    };

    let body = new URLSearchParams();
    body.set('username', username);
    body.set('password', password);
    body.set('grant_type', 'password');
    return this.http.post<any>(`${environment.apiUrl}/oauth/token`, body.toString(), httpOptions)
      .pipe(map(response => {
        // store user details and jwt token in local storage to keep user logged in between page refreshes
        localStorage.setItem('access_token', JSON.stringify(response.access_token));
        localStorage.setItem('refresh_token', JSON.stringify(response.refresh_token));
        this.currentUserSubject.next(response);
        return response;
      }));
  }

  logout() {
    // remove user from local storage and set current user to null
    localStorage.removeItem('access_token');
    localStorage.removeItem('refresh_token');
    this.currentUserSubject.next(null);
  }
}
