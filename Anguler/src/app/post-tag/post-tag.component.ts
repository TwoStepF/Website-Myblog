import {Component, OnInit, DoCheck} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {PostService} from "../Service/post.service";
import {PostDetail} from "../Form/PostDetail";
import {Observable} from "rxjs";
import {LocalStorageService} from "ngx-webstorage";
import {AuthService} from "../Service/auth-service.service";

@Component({
  selector: 'app-post-tag',
  templateUrl: './post-tag.component.html',
  styleUrls: ['./post-tag.component.css']
})
export class PostTagComponent implements OnInit, DoCheck {
  TagName: String;
  IdTag: number;
  posts: Observable<Array<PostDetail>>
  constructor(private router: ActivatedRoute, private postService: PostService, private localStorage: LocalStorageService, private authService: AuthService) { }

  ngOnInit(): void {
    this.router.params.subscribe(param => {
      this.IdTag = param['id'];
      this.getPostByTag(this.IdTag);
    });
  }

  private getPostByTag(IdTag: number) {
    this.posts = this.postService.getPostByTag(IdTag);
  }

  ngDoCheck(): void {
    this.TagName = this.localStorage.retrieve('tagname')
  }

  RightToDelete(Post1: PostDetail): boolean {
    if(this.authService.isRole("ROLE_SAD") || this.authService.isUser(Post1.username)){
      return true;
    }
    if(this.authService.isRole("ROLE_ADMIN") && Post1.roleUser == "ROLE_VUSER"){
      return true;
    }
    return false;
  }

  deletePost(id: number) {
    if (window.confirm('are you sure')) {
      this.postService.deletePost(id).subscribe(
        data => {window.location.reload();},
        error => {alert(error.message)}
      );
    }
  }
}
