import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Tag } from '../interface/tag.interface';
import { TAG_URL } from '../routes.constants';

@Injectable({
  providedIn: 'root'
})
export class TagService {

  constructor(private http: HttpClient) { }

  createTag(tag: Tag): Observable<any> {
    return this.http.post(`${TAG_URL}/create`, tag);
  }

  updateTag(id: number, tag: Tag): Observable<any> {
    return this.http.put(`${TAG_URL}/update/${id}`, tag);
  }

  deleteTag(id: number): Observable<any> {
    return this.http.delete(`${TAG_URL}/delete/${id}`);
  }
}
