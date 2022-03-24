import {Component, OnInit} from '@angular/core';
import {SignIn} from '../../Form/SignIn';
import {Login} from '../../Form/Login';
import {AuthService} from '../../Service/auth-service.service';
import {Router} from '@angular/router';


@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    constructor(private authService: AuthService, private router: Router) {
    }

    ngOnInit(): void {
        if(this.authService.isAuth()){
            this.router.navigateByUrl('');
        }
    }

    public model = new SignIn('', '', '');

    onSubmit(value: Login) {
        this.authService.login(value).subscribe(
            data => {
                alert('Đăng nhập thành công');
                window.location.reload();
            },
            error => {
                alert('đăng nhập thất bại');
            }
        );
    }
}
