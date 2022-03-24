import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SignIn} from '../Form/SignIn';
import {Observable} from 'rxjs';
import {Login} from '../Form/Login';
import {jwtAuth} from '../Form/JwtAuth';
import {LocalStorageService} from 'ngx-webstorage';
import {map} from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private url = 'http://localhost:8082/api/auth/';

    constructor(private http: HttpClient, private localStorageService: LocalStorageService) {
    }

    register(Infor: SignIn): Observable<any> {
        return this.http.post(this.url + 'signup', Infor);
    }

    login(Infor: Login): Observable<boolean> {
        return this.http.post<jwtAuth>(this.url + 'login', Infor)
            .pipe(map(data => {
                this.localStorageService.store('authenticationToken', data.authenticationToken);
                this.localStorageService.store('username', data.username);
                this.localStorageService.store('role', data.role);
                return true;
            }));
    }

    isAuth(): boolean {
            return this.localStorageService.retrieve('username') != null;
    }
    isUser(username: String): boolean {
        return this.localStorageService.retrieve('username') == username;
    }
    isRole(role: String): boolean {
        return this.localStorageService.retrieve('role') == role;
    }
    LogOut(){
        this.localStorageService.clear();
    }
}
