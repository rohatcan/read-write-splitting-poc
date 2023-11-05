package com.rohat.readwritesplittingpoc.orders

import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/orders")
class OrderController(
   private val orderRepository: OrderRepository
) {

   fun incrementBasketSize() {
      val order = orderRepository.findByIdOrNull(1L) ?: run {
         val newOrder = Order(1L, "Order 1", "Description 1", 0)
         orderRepository.save(newOrder)
         newOrder
      }
      order.basketSize += 1
      orderRepository.save(order)
   }

   @Transactional(readOnly = true)
   fun getOrder(id: Long): Order {
      return orderRepository.findByIdNative(id) ?: throw Exception("Order not found")
   }

}
