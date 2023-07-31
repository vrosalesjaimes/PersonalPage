import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Reference } from '../interface/reference.interface';
import { HttpClient } from '@angular/common/http';
import { REFERENCE_URL } from '../routes.constants';
import { Author } from '../interface/author.interface';

@Injectable({
  providedIn: 'root'
})
export class ReferenceService {

  constructor(private http:HttpClient) { }

  createReference(reference: Reference): Observable<any> {
    return this.http.post(`${REFERENCE_URL}/create`, reference);
  }

  updateReference(id: number, reference: Reference): Observable<any> {
    return this.http.put(`${REFERENCE_URL}/update/${id}`, reference);
  }

  deleteReference(id: number): Observable<any> {
    return this.http.delete(`${REFERENCE_URL}/delete/${id}`);
  }

  addAuthors(id: number, authors: Author[]): Observable<any> {
    return this.http.put(`${REFERENCE_URL}/${id}/add-authors`, authors);
  }

}
