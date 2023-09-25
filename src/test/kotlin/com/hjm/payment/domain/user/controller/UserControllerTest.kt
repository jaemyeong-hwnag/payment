package com.hjm.payment.domain.user.controller

import com.hjm.payment.domain.user.dto.LoginUserDto
import io.kotest.core.spec.style.FunSpec
import org.json.JSONObject
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@AutoConfigureMockMvc
@SpringBootTest
@ContextConfiguration(classes = [UserController::class])
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // BeforeAll
class UserControllerTest : FunSpec() {
    @Autowired
    lateinit var mockMvc: MockMvc

    @BeforeEach
    fun beforeTest() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun loginUserTest() {
        val loginUserDto: LoginUserDto = LoginUserDto("test", "test")
        val loginDtoJson = JSONObject(loginUserDto).toString()

        mockMvc.post("/user/login")
        {
            contentType = MediaType.APPLICATION_JSON
            content = loginDtoJson
        }
            .andExpect {
                status { isOk() }
            }
            .andDo {
                print()
            }
    }
}