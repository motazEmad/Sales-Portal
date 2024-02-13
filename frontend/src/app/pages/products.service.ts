import { Injectable } from '@angular/core';
import { Product } from '../models/product';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ServerConfig } from '../ServerConfig';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {


  constructor(private http: HttpClient, private serverConfig: ServerConfig) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.serverConfig.getProductsUrl);
  }
}
