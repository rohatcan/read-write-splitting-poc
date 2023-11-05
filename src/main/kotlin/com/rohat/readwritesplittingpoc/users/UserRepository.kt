package com.rohat.readwritesplittingpoc.users

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long>
