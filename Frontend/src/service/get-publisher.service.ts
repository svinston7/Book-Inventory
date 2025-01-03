import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Publisher } from '../model/Publisher';

@Injectable({
  providedIn: 'root'
})
export class GetPublisherService {
  PUBLISHERURL: string = "http://localhost:9090/api/publisher";

  constructor(private httpClient: HttpClient) {}

  showPublishers(): Observable<any> {
    return this.httpClient.get(this.PUBLISHERURL);
  }

  getPublisherById(id: number): Observable<Publisher> {
    return this.httpClient.get<Publisher>(`${this.PUBLISHERURL}/${id}`);
  }

  addPublisher(publisher: Publisher): Observable<any> {
    return this.httpClient.post(`${this.PUBLISHERURL}/post`, publisher);
  }

  updatePublisherName(publisherId: number, name: string): Observable<any> {
    return this.httpClient.put(`${this.PUBLISHERURL}/update/name/${publisherId}`, name);
  }

  updatePublisherCity(publisherId: number, city: string): Observable<any> {
    return this.httpClient.put(`${this.PUBLISHERURL}/update/city/${publisherId}`, city);
  }

  updatePublisherState(publisherId: number, state: string): Observable<any> {
    return this.httpClient.put(`${this.PUBLISHERURL}/update/state/${publisherId}`, state);
  }
}
