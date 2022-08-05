import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './user';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private apiServiceURL = environment.apiBaseUrl;

  //   injecting the HttpClient built in angular service dependency in order to make HttpRequests
  constructor(private http: HttpClient) {}

  public getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.apiServiceURL);
  }

  public addUser(user: User): Observable<User> {
    return this.http.post<User>(this.apiServiceURL, user);
  }

  public deleteUser(userId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServiceURL}/${userId}`);
  }
}
