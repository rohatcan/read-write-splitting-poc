package com.rohat.readwritesplittingpoc.products

import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class ProductController(
   private val productRepository: ProductRepository
) {

   fun incrementPrice() {
      val product = productRepository.findByIdOrNull(1L) ?: run {
         val newProduct = Product(1L, "Product 1", "Description 1", 0)
         productRepository.save(newProduct)
         newProduct
      }
      product.price += 1
      productRepository.save(product)
   }

   @Transactional(readOnly = true)
   fun getProduct(id: Long): Product {
      return productRepository.findByIdOrNull(id) ?: throw Exception("Product not found")
   }

}
