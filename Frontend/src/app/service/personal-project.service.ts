import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PersonalProject } from '../interface/personal-project.interface';
import { Image } from '../interface/image.interface';
import { Reference } from '../interface/reference.interface';
import { Tag } from '../interface/tag.interface';
import { Author } from '../interface/author.interface';
import { PERSONAL_PROJECT_URL } from '../routes.constants';

@Injectable({
  providedIn: 'root'
})
export class PersonalProjectService {

  constructor(private http: HttpClient) { }

  createPersonalProject(personalProject: PersonalProject): Observable<PersonalProject> {
    return this.http.post<PersonalProject>(`${PERSONAL_PROJECT_URL}/create`, personalProject);
  }

  updatePersonalProject(id: number, personalProject: PersonalProject): Observable<string> {
    return this.http.put<string>(`${PERSONAL_PROJECT_URL}/update/${id}`, personalProject);
  }

  deletePersonalProject(id: number): Observable<string> {
    return this.http.delete<string>(`${PERSONAL_PROJECT_URL}/delete/${id}`);
  }

  getPersonalProjectById(id: number): Observable<PersonalProject> {
    return this.http.get<PersonalProject>(`${PERSONAL_PROJECT_URL}/${id}`);
  }

  getAllPersonalProjects(): Observable<PersonalProject[]> {
    return this.http.get<PersonalProject[]>(`${PERSONAL_PROJECT_URL}/`);
  }

  searchPersonalProjectsByTitle(title: string): Observable<PersonalProject[]> {
    return this.http.get<PersonalProject[]>(`${PERSONAL_PROJECT_URL}/search/title?title=${title}`);
  }

  searchPersonalProjectsByTag(tagName: string): Observable<PersonalProject[]> {
    return this.http.get<PersonalProject[]>(`${PERSONAL_PROJECT_URL}/search/tag?tagName=${tagName}`);
  }

  searchPersonalProjectsByAuthor(authorName: string): Observable<PersonalProject[]> {
    return this.http.get<PersonalProject[]>(`${PERSONAL_PROJECT_URL}/search/author?authorName=${authorName}`);
  }

  addImagesToPersonalProject(id: number, images: Image[]): Observable<string> {
    return this.http.put<string>(`${PERSONAL_PROJECT_URL}/${id}/add-images`, images);
  }

  addAuthorsToPersonalProject(id: number, authors: Author[]): Observable<string> {
    return this.http.put<string>(`${PERSONAL_PROJECT_URL}/${id}/add-authors`, authors);
  }

  addReferencesToPersonalProject(id: number, references: Reference[]): Observable<string> {
    return this.http.put<string>(`${PERSONAL_PROJECT_URL}/${id}/add-references`, references);
  }

  addTagsToPersonalProject(id: number, tags: Tag[]): Observable<string> {
    return this.http.put<string>(`${PERSONAL_PROJECT_URL}/${id}/add-tags`, tags);
  }
}
