package com.kotlin.entityframework.service

import com.kotlin.entityframework.annotation.MyTransactional
import com.kotlin.entityframework.model.User
import com.kotlin.entityframework.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    @MyTransactional
    fun getUsers(): List<User> {
        return userRepository.findAll()
    }

}