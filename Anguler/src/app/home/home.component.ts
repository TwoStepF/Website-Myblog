import {Component, OnInit} from '@angular/core';
import {PostService} from '../Service/post.service';
import {PostDetail} from '../Form/PostDetail';
import {Observable} from 'rxjs';
import {AuthService} from "../Service/auth-service.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  posts: Observable<Array<PostDetail>>;
  constructor(private postSevice: PostService, private authService: AuthService) {
  }

  ngOnInit(): void {
    this.getAllPost();

  }

  getAllPost() {
    this.posts = this.postSevice.getAllPost();
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
      this.postSevice.deletePost(id).subscribe(
        data => {window.location.reload();},
        error => {alert(error.message)}
      );
    }
  }
}
