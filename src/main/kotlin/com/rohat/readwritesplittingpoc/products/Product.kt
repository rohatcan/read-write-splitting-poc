package com.rohat.readwritesplittingpoc.products

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Product(
   @Id
   val id: Long,
   val name: String,
   val description: String,
)
