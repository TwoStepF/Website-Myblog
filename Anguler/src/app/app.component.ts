import {AfterViewInit, Component, DoCheck, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {AuthService} from './Service/auth-service.service';
import {LocalStorageService} from 'ngx-webstorage';
import {Observable} from "rxjs";
import {Tag} from "./Form/Tag";
import {PostService} from "./Service/post.service";
import {PostDetail} from "./Form/PostDetail";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  constructor(public authService: AuthService, public localStorage: LocalStorageService, private postService: PostService) {
  }

  tags: Observable<Array<Tag>>;
  posts: Observable<Array<PostDetail>>;
  title = 'MyWebsite';
  username!: String
  Key: String;

  OnLogOut() {
    this.authService.LogOut();
    window.location.reload();
  }

  ngOnInit(): void {
    this.username = this.localStorage.retrieve('username');
    this.tags = this.postService.getAllTag();
    this.posts = this.postService.getTopPost();
  }
}
