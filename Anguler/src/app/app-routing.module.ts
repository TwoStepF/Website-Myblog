import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {RegisterComponent} from './auth/register/register.component';
import {LoginComponent} from './auth/login/login.component';
import {HomeComponent} from './home/home.component';
import {AddpostComponent} from './addpost/addpost.component';
import {ViewPostComponent} from './view-post/view-post.component';
import {NotfoundComponent} from './notfound/notfound.component';
import {UserPostComponent} from './user/user-post/user-post.component';
import {ManageComponent} from './manage/manage.component';
import {PostTagComponent} from "./post-tag/post-tag.component";
import {SearchComponent} from "./search/search.component";



const routes: Routes = [
  { path: '', pathMatch: 'full' ,component: HomeComponent},
  { path: 'auth/register', component: RegisterComponent },
  { path: 'auth/login', component: LoginComponent },
  { path: 'addpost', component: AddpostComponent },
  { path: 'post/:id', component: ViewPostComponent },
  { path: 'user/post', component: UserPostComponent },
  { path: 'manage', component: ManageComponent },
  { path: 'tag/:id', component: PostTagComponent },
  { path: 'search/:key', component: SearchComponent },
  { path: '**', component: NotfoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
