import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {Post} from '../Form/post';
import {PostDetail} from '../Form/PostDetail';
import {Tag} from "../Form/Tag";

@Injectable({
  providedIn: 'root'
})
export class PostService {
  private url = 'http://localhost:8082/api/post';

  constructor(private http: HttpClient) {
  }

  AddPost(Infor: Post): Observable<Post> {
    return this.http.post<Post>(this.url, Infor);
  }

  getAllPost(): Observable<Array<PostDetail>> {
    return this.http.get<Array<PostDetail>>(this.url + '/all');
  }

  getPost(IdPost: number): Observable<PostDetail> {
    return this.http.get<PostDetail>(this.url + '/get/' + IdPost);
  }

  updatePost(Infor: PostDetail): Observable<any> {
    return this.http.put(this.url + "/update", Infor);
  }

  getUserPost(): Observable<Array<PostDetail>> {
    return this.http.get<Array<PostDetail>>(this.url + '/me');
  }

  getAllTag(): Observable<Array<Tag>> {
    return this.http.get<Array<Tag>>(this.url + '/tag');
  }

  getPostByTag(IdTag: number): Observable<Array<PostDetail>> {
    return this.http.get<Array<PostDetail>>(this.url + '/tag/' + IdTag);
  }

  searchPost(key: String): Observable<Array<PostDetail>> {
    return this.http.get<Array<PostDetail>>(this.url + '/search/' + key);
  }

  deletePost(id: number): Observable<any> {
    return this.http.delete(this.url + '/delete/' + id);
  }

  updateViewPost(Infor: PostDetail): Observable<any> {
    return this.http.put(this.url + "/view", Infor);
  }

  getTopPost(): Observable<any> {
    return this.http.get(this.url + "/top")
  }
}
