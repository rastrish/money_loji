package com.zerone.moneyloji.utill

import org.hamcrest.core.Is.`is`
import org.junit.Assert.*
import org.junit.Test

class ValidatorTest {


    private val emailRegex = "\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$"

    private val passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$"

    private val usernameRegex = "^[a-zA-Z\\s]*$"


    //Email Validator Test Cases

    @Test
    fun emailValidator_CorrectEmailSimple_ReturnsTrue() {
        assertThat(
            Validator.isValid( emailRegex,"rishabhrastogi@gmail.com"),
            `is`(true)
        )
    }

    @Test
    fun emailValidator_InvalidEmailNoUsername_ReturnsFalse() {
        assertFalse(Validator.isValid(emailRegex,"@email.com"))
    }



    @Test
    fun emailValidator_InvalidEmailDoubleDot_ReturnsFalse() {
        assertFalse(Validator.isValid(emailRegex,"name@email..com"))
    }

    @Test
    fun emailValidator_EmptyString_ReturnsFalse() {
        assertFalse(Validator.isValid(emailRegex,"" ))
    }

    @Test
    fun emailValidator_InvalidEmailNoTld_ReturnsFalse() {
        assertFalse(Validator.isValid(emailRegex,"name@email" ))
    }


    //Password Validator TestCases

    @Test
    fun passwordValidator_InvalidPassword_ReturnFalse() {
        assertFalse(Validator.isValid(passwordRegex,"rishabh"))
    }

    @Test
    fun passwordValidator_validPassword_ReturnTrue() {
        assertThat(Validator.isValid(passwordRegex,"Rishabh123"), `is`(true))
    }

    @Test
    fun passwordValidator_NumericPassword_ReturnFalse() {
        assertFalse(Validator.isValid(passwordRegex,"123"))
    }

    @Test
    fun passwordValidator_LessThan8Character_ReturnFalse() {
        assertFalse(Validator.isValid(passwordRegex,"abc123"))
    }

    @Test
    fun passwordValidator_SpecialCharacter_ReturnFalse() {
        assertFalse(Validator.isValid(passwordRegex,"Rish@@@123"))
    }

    @Test
    fun nameValidator() {
        assertThat(Validator.isValid(usernameRegex,"Rishbh Rastogi"),`is`(true))
    }


}