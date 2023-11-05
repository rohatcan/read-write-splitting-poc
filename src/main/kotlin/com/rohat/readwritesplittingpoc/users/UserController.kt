package com.rohat.readwritesplittingpoc.users

import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
   private val userRepository: UserRepository
){

   fun incrementLoginCount() {
      val firstUser = userRepository.findByIdOrNull(1L) ?: run {
         val newUser = User(1L, "User 1", "Description 1", "pwd-enc", 0)
         userRepository.save(newUser)
         newUser
      }

      firstUser.loginCount += 1
      userRepository.save(firstUser)
   }

   @Transactional(readOnly = true)
   fun getUser(id: Long): User {
      return userRepository.findByIdOrNull(id) ?: throw Exception("User not found")
   }

}
