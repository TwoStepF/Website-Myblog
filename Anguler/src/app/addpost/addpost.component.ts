import {Component, Input, OnInit} from '@angular/core';
import {PostService} from '../Service/post.service';
import {Router} from '@angular/router';
import {AuthService} from '../Service/auth-service.service';
import {LocalStorageService} from 'ngx-webstorage';
import {PostDetail} from '../Form/PostDetail';
import {Tag} from "../Form/Tag";
import {Observable} from "rxjs";


@Component({
  selector: 'app-addpost',
  templateUrl: './addpost.component.html',
  styleUrls: ['./addpost.component.css']
})
export class AddpostComponent implements OnInit {
  tags: Observable<Array<Tag>>;
  idPost: number;
  bool: boolean = false;
  Infor = new PostDetail();

  constructor(private postService: PostService, private router: Router, private authService: AuthService, private localStorage: LocalStorageService) {
  }

  ngOnInit(): void {
    if (!this.authService.isAuth() || this.authService.isRole('ROLE_USER')) {
      this.router.navigateByUrl('**');
    }
    this.idPost = this.localStorage.retrieve('idPost');
    this.localStorage.clear('idPost');
    if (this.idPost != null) {
      this.bool = true;
      this.postService.getPost(this.idPost).subscribe(
        data => {
          this.Infor = data;
        },
        error => {
          alert(error.message)
        }
      )
    }
    this.tags = this.postService.getAllTag();
  }

  OnSubmit() {
    this.postService.AddPost(this.Infor).subscribe(
      data => {
        this.router.navigateByUrl('');
      },
      error => {
        alert(error.message);
      }
    );
  }

  OnUpdate() {
    this.postService.updatePost(this.Infor).subscribe(
      data => {
        this.router.navigateByUrl('');
      },
      error => {
        alert(error.message);
      }
    )
  }

}

