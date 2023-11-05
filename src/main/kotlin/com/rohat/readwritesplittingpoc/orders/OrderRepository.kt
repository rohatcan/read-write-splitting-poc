package com.rohat.readwritesplittingpoc.orders

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface OrderRepository : JpaRepository<Order, Long>{

   @Query(nativeQuery = true, value = "SELECT * FROM orders WHERE id = ?1")
   fun findByIdNative(id: Long): Order?
}
