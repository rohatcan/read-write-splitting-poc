package com.rohat.readwritesplittingpoc.orders

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService(
   private val orderRepository: OrderRepository
) {

   @Transactional(readOnly = true)
   fun getOrder(id: Long): Order {
      return orderRepository.findById(id).orElseThrow()
   }

}
