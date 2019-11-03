/*
 * Developed by Petka <petar.georgiev@gmail.com>
 * Last modified 11/3/19 5:10 PM.
 * Copyright (c) 2019.
 */

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { User } from '@/_models';

@Injectable({ providedIn: 'root' })
export class UserService {
  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get<User[]>(`${config.apiUrl}/users`);
  }

  register(user: User) {
    return this.http.post(`${config.apiUrl}/users/register`, user);
  }

  delete(id: number) {
    return this.http.delete(`${config.apiUrl}/users/${id}`);
  }
}
