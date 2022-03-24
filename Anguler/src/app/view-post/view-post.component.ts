import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {PostService} from '../Service/post.service';
import {PostDetail} from '../Form/PostDetail';
import {AuthService} from '../Service/auth-service.service';
import {LocalStorageService} from 'ngx-webstorage';


@Component({
  selector: 'app-view-post',
  templateUrl: './view-post.component.html',
  styleUrls: ['./view-post.component.css']
})
export class ViewPostComponent implements OnInit {
  IdPost: number;
  post: PostDetail;
  postV: PostDetail;
  bool: boolean = false;

  constructor(private router: ActivatedRoute, private postService: PostService, public jauthService: AuthService, private localStorage: LocalStorageService) {
  }

  ngOnInit(): void {
    this.router.params.subscribe(param => {
      this.IdPost = param['id'];
      this.getPost(this.IdPost);
    });

  }

  getPost(id: number) {
    this.postService.getPost(id).subscribe(
      data => {
        this.post = data;
        this.post.view++;
        this.postService.updateViewPost(this.post).subscribe()
        if (this.jauthService.isUser(data.username)) {
          this.bool = true;
        }
      },
      error => {
        alert(error.message);
      }
    );
  }

  getUpdatePost() {
    this.localStorage.store('idPost', this.IdPost);
  }

}
