package com.rohat.readwritesplittingpoc.products

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "products")
data class Product(
   @Id
   val id: Long,
   val name: String,
   val description: String,
   var price: Int,
)
