import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {User} from '../Form/user';
import {HttpClient} from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class ManageService {
  private url = 'http://localhost:8082/api/manage';

  constructor(private http: HttpClient) {
  }

  getAllUser(): Observable<Array<User>> {
    return this.http.get<Array<User>>(this.url);
  }

  updateRole(data: User): Observable<any> {
    return this.http.put(this.url + "/update", data);
  }

  getUserInfor(id: number): Observable<any>{
    return this.http.get(this.url + "/get/" + id);
  }
}
