package com.rohat.readwritesplittingpoc.users

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
data class User(
   @Id
   val id: Long,
   val name: String,
   val email: String,
   val password: String,
   var loginCount: Int,
)
