package com.rohat.readwritesplittingpoc.orders

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Order(
   @Id
   val id: Long,
   val name: String,
   val description: String,
)
