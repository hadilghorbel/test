import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Hotel} from "../../components/uikit/models/hotel.model";

@Injectable({
  providedIn: 'root'
})
export class HotelService {

    environement: string = "http://localhost:8080/api/hotels";
  constructor(private httpClient: HttpClient) { }
    findAll(): Observable<Hotel[]>{
      return this.httpClient.get<Hotel[]>(this.environement);
    }
}
