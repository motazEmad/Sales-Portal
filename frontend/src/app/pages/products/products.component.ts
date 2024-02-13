import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Product } from 'src/app/models/product';
import { ProductsService } from '../products.service';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent {
  products: Product[] = [];

  constructor(private productsService: ProductsService) {
    this.getProducts();
  }

  getProducts(): void {
    this.productsService.getProducts().subscribe(products => this.products = products);
  }
}
