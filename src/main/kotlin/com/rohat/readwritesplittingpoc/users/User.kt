package com.rohat.readwritesplittingpoc.users

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class User(
   @Id
   val id: Long,
   val name: String,
   val email: String,
   val password: String

)
