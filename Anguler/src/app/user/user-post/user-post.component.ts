import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../Service/auth-service.service';
import {Observable} from 'rxjs';
import {PostDetail} from '../../Form/PostDetail';
import {PostService} from '../../Service/post.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ManageService} from "../../Service/manage.service";
import {User} from "../../Form/user";

@Component({
  selector: 'app-user-post',
  templateUrl: './user-post.component.html',
  styleUrls: ['./user-post.component.css']
})
export class UserPostComponent implements OnInit {
  posts: Observable<Array<PostDetail>>;
  user: User;
  IdPost: number;

  constructor(private postService: PostService, private authService: AuthService, private router: Router, private manageService: ManageService, private router1: ActivatedRoute) {
  }

  ngOnInit(): void {
    if (this.authService.isAuth()) {
      this.posts = this.postService.getUserPost();
      // this.router1.params.subscribe(param => {
      //   this.IdPost = param['id'];
      //   this.manageService.getUserInfor(this.IdPost).subscribe(
      //     data => {this.user = data},
      //     error => {alert(error.message)}
      //   )
      // });
    } else {
      this.router.navigateByUrl('');
    }
  }

}
