import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class ServerConfig {
    baseUrl = "http://localhost:8082/"
    getProductsUrl = this.baseUrl + "product/v1/products"

}