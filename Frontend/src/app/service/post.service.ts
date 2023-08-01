import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Post } from '../interface/post.interface';
import { Image } from '../interface/image.interface';
import { Tag } from '../interface/tag.interface';
import { POST_URL } from '../routes.constants';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient) { }

  createPost(post: Post): Observable<Post> {
    return this.http.post<Post>(`${POST_URL}/create`, post);
  }

  updatePost(id: number, post: Post): Observable<string> {
    return this.http.put<string>(`${POST_URL}/update/${id}`, post);
  }

  deletePost(id: number): Observable<string> {
    return this.http.delete<string>(`${POST_URL}/delete/${id}`);
  }

  getPostById(id: number): Observable<Post> {
    return this.http.get<Post>(`${POST_URL}/${id}`);
  }

  getAllPosts(): Observable<Post[]> {
    return this.http.get<Post[]>(`${POST_URL}/`);
  }

  searchPostsByTitle(title: string): Observable<Post[]> {
    return this.http.get<Post[]>(`${POST_URL}/search/title?title=${title}`);
  }

  searchPostsByTag(tagName: string): Observable<Post[]> {
    return this.http.get<Post[]>(`${POST_URL}/search/tag?tagName=${tagName}`);
  }

  addImagesToPost(id: number, images: Image[]): Observable<string> {
    return this.http.put<string>(`${POST_URL}/${id}/add-images`, images);
  }

  addTagsToPost(id: number, tags: Tag[]): Observable<string> {
    return this.http.put<string>(`${POST_URL}/${id}/add-tags`, tags);
  }

}
