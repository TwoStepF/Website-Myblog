import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {InputTextModule} from 'primeng/inputtext';
import { FormsModule } from '@angular/forms';
import {RegisterComponent} from './auth/register/register.component';
import {LoginComponent} from './auth/login/login.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {Ng2Webstorage} from 'ngx-webstorage';
import { HomeComponent } from './home/home.component';
import { AddpostComponent } from './addpost/addpost.component';
import {HttpClientInterceptor} from './htttp-client';
import { ViewPostComponent } from './view-post/view-post.component';
import { NotfoundComponent } from './notfound/notfound.component';
import { UserPostComponent } from './user/user-post/user-post.component';
import { ManageComponent } from './manage/manage.component';
import {DropdownModule} from 'primeng/dropdown';
import {EditorModule} from 'primeng/editor';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {ButtonModule} from 'primeng/button';
import {CheckboxModule} from 'primeng/checkbox';
import { PostTagComponent } from './post-tag/post-tag.component';
import { SearchComponent } from './search/search.component';
@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    HomeComponent,
    AddpostComponent,
    ViewPostComponent,
    NotfoundComponent,
    UserPostComponent,
    ManageComponent,
    PostTagComponent,
    SearchComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    InputTextModule,
    FormsModule,
    HttpClientModule,
    Ng2Webstorage.forRoot(),
    DropdownModule,
    EditorModule,
    BrowserAnimationsModule,
    ButtonModule,
    CheckboxModule
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: HttpClientInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
