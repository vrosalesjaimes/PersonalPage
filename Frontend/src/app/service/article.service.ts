import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Article } from '../interface/article.interface';
import { Author } from '../interface/author.interface';
import { Image } from '../interface/image.interface';
import { Reference } from '../interface/reference.interface';
import { Tag } from '../interface/tag.interface';
import { ARTICLE_URL } from '../routes.constants';


@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  constructor(private http: HttpClient) { }

  createArticle(article: Article): Observable<any> {
    return this.http.post(`${ARTICLE_URL}/create`, article);
  }

  updateArticle(id: number, article: Article): Observable<any> {
    return this.http.put(`${ARTICLE_URL}/update/${id}`, article);
  }

  deleteArticle(id: number): Observable<any> {
    return this.http.delete(`${ARTICLE_URL}/delete/${id}`);
  }

  addImages(id: number, images: Image[]): Observable<any> {
    return this.http.put(`${ARTICLE_URL}/${id}/add-images`, images);
  }

  addAuthors(id: number, authors: Author[]): Observable<any> {
    return this.http.put(`${ARTICLE_URL}/${id}/add-authors`, authors);
  }

  addReferences(id: number, references: Reference[]): Observable<any> {
    return this.http.put(`${ARTICLE_URL}/${id}/add-references`, references);
  }

  addTags(id: number, tags: Tag[]): Observable<any> {
    return this.http.put(`${ARTICLE_URL}/${id}/add-tags`, tags);
  }

  getAllForCards(): Observable<Article[]> {
    return this.http.get<Article[]>(`${ARTICLE_URL}/card-view`);
  }

  getArticle(id: number): Observable<any> {
    return this.http.get(`${ARTICLE_URL}/${id}`);
  }

  searchByTitle(title: string): Observable<Article[]> {
    return this.http.get<Article[]>(`${ARTICLE_URL}/search/title?title=${title}`);
  }

  searchByAuthorName(authorName: string): Observable<Article[]> {
    return this.http.get<Article[]>(`${ARTICLE_URL}/search/authorName?authorName=${authorName}`);
  }

  searchByTagName(tagName: string): Observable<Article[]> {
    return this.http.get<Article[]>(`${ARTICLE_URL}/search/tagName?tagName=${tagName}`);
  }

}
