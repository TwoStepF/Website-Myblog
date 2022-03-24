import {Component, OnInit} from '@angular/core';
import {ManageService} from '../Service/manage.service';
import {User} from '../Form/user';
import {Observable, Subject} from 'rxjs';
import {AuthService} from '../Service/auth-service.service';
import {Router} from '@angular/router';
import {LocalStorageService} from "ngx-webstorage";


@Component({
  selector: 'app-manage',
  templateUrl: './manage.component.html',
  styleUrls: ['./manage.component.css']
})
export class ManageComponent implements OnInit {
  public users: Observable<User[]>;
  public tran: string = 'All member';
  public find: boolean = false;
  public id: number;
  public Fid: number;
  public lt: String[] = [];
  public InUser: String;
  public InUserRole: String;
  option: String[] = [
    'ROLE_USER',
    'ROLE_VUSER',
    'ROLE_ADMIN'
  ];
  constructor(private manageService: ManageService, private authService: AuthService, private router: Router, private localStorage: LocalStorageService) {
  }

  ngOnInit(): void {
    this.InUser = this.localStorage.retrieve('username');
    this.InUserRole = this.localStorage.retrieve('role');
    (this.authService.isRole('ROLE_SAD') || this.authService.isRole('ROLE_ADMIN'))? this.users = this.manageService.getAllUser() : this.router.navigateByUrl('**');
    for (let i = 0; i < 1000; i++) {
      this.lt[i] = 'choise';
    }
    if(this.InUserRole == 'ROLE_ADMIN') {
      this.option = [
        'ROLE_USER',
        'ROLE_VUSER'
      ];
    }
  }

  findUser() {
    this.tran = 'ROLE_USER';
    this.find = false;
  }

  findVUser() {
    this.tran = 'ROLE_VUSER';
    this.find = false;
  }

  findAdmin() {
    this.tran = 'ROLE_ADMIN';
    this.find = false;
  }

  findSAdmin() {
    this.tran = 'ROLE_SAD';
    this.find = false;
  }

  findAll() {
    this.tran = 'All member';
    this.find = false;
  }

  search(value: number) {
    this.id = value;
    this.find = true;
  }

  onSave(data: User, i: number) {
    if (this.lt[i] != 'choise') {
      data.role = this.lt[i];
      this.manageService.updateRole(data).subscribe(
        data => {
          alert("đã lưu");
          this.lt[i] = 'choise';
        },
        error => {
          alert(error.message)
        }
      );
    }else{
      alert("Nhập lựa chọn");
    }
  }
}
