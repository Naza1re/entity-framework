package com.kotlin.aop.controller

import com.kotlin.aop.model.User
import com.kotlin.aop.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class Controller(
    private val userService: UserService
) {

    @GetMapping
    fun getUsers(): List<User> {
        return userService.getUsers()
    }

}