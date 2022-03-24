import { Component, OnInit } from '@angular/core';
import {Observable} from "rxjs";
import {PostDetail} from "../Form/PostDetail";
import {PostService} from "../Service/post.service";
import {ActivatedRoute} from "@angular/router";
import {AuthService} from "../Service/auth-service.service";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  posts: Observable<Array<PostDetail>>;
  public key: String;

  constructor(private postService: PostService, private router: ActivatedRoute, private authService: AuthService) { }

  ngOnInit(): void {
    this.router.params.subscribe(param => {
      this.key = param['key'];
      this.posts = this.postService.searchPost(this.key)
    });
  }

  deletePost(id: number) {
    if (window.confirm('are you sure')) {
      this.postService.deletePost(id).subscribe(
        data => {window.location.reload();},
        error => {alert(error.message)}
      );
    }
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
}
