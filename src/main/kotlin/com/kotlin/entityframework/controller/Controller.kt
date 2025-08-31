package com.kotlin.entityframework.controller

import com.kotlin.entityframework.model.User
import com.kotlin.entityframework.service.UserService
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