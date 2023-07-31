import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Author } from '../interface/author.interface';
import { AUTHOR_URL } from '../routes.constants';

@Injectable({
  providedIn: 'root'
})
export class AuthorService {

  constructor(private http: HttpClient) { }

  createAuthor(author: Author): Observable<any> {
    return this.http.post(`${AUTHOR_URL}/create`, author);
  }

  updateAuthor(id: number, author: Author): Observable<any> {
    return this.http.put(`${AUTHOR_URL}/update/${id}`, author);
  }

  deleteAuthor(id: number): Observable<any> {
    return this.http.delete(`${AUTHOR_URL}/delete/${id}`);
  }
}
