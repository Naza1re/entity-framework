package com.kotlin.aop.service

import com.kotlin.aop.annotation.MyTransactional
import com.kotlin.aop.model.User
import com.kotlin.aop.repository.UserRepository
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