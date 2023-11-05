package com.rohat.readwritesplittingpoc.orders

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "orders")
data class Order(
   @Id
   val id: Long,
   val name: String,
   val description: String,
   var basketSize: Int,
)
