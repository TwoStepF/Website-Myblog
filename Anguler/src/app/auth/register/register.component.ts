import {Component, OnInit} from '@angular/core';
import {SignIn} from '../../Form/SignIn';
import {AuthService} from '../../Service/auth-service.service';
import {Router} from '@angular/router';


@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

    constructor(private authservice: AuthService, private router: Router) {
    }

    ngOnInit(): void {
        if(this.authservice.isAuth()){
            this.router.navigateByUrl('');
        }
    }

    public model = new SignIn('', '', '');

    onSubmit(inFor: SignIn): void {
        this.authservice.register(inFor).subscribe(
            data => {
                alert('đăng ký thành công');
                this.router.navigateByUrl("/auth/login");
            },
            error => {
                alert('đăng ký thất bại');
            }
        );
    }
}
