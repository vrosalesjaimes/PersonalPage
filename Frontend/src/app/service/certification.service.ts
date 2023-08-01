import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Certification } from '../interface/certification.interface';
import { Tag } from '../interface/tag.interface';
import { CERTIFICATION_URL } from '../routes.constants';



@Injectable({
  providedIn: 'root'
})
export class CertificationService {

  constructor(private http: HttpClient) { }

  createCertification(certification: Certification): Observable<any> {
    return this.http.post(`${CERTIFICATION_URL}/create`, certification);
  }

  updateCertification(id: number, certification: Certification): Observable<any> {
    return this.http.put(`${CERTIFICATION_URL}/update/${id}`, certification);
  }

  deleteCertification(id: number): Observable<any> {
    return this.http.delete(`${CERTIFICATION_URL}/delete/${id}`);
  }

  getAll(): Observable<Certification[]> {
    return this.http.get<Certification[]>(`${CERTIFICATION_URL}/all-by-idiom`);
  }

  searchByTitle(title: string): Observable<Certification[]> {
    return this.http.get<Certification[]>(`${CERTIFICATION_URL}/search/title?title=${title}`);
  }

  searchByTagName(tagName: string): Observable<Certification[]> {
    return this.http.get<Certification[]>(`${CERTIFICATION_URL}/search/tagName?tagName=${tagName}`);
  }

  addTags(id: number, tags: Tag[]): Observable<any> {
    return this.http.put(`${CERTIFICATION_URL}/${id}/add-tags`, tags);
  }
}
