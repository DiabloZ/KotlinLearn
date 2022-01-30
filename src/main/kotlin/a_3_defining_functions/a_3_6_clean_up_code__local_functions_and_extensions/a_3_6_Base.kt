package main.kotlin.a_3_defining_functions.a_3_6_clean_up_code__local_functions_and_extensions

import main.kotlin.a_2_basics_kotlin.a_2_5_exeption_in_kotlin.catchException
import java.lang.IllegalArgumentException

fun a_3_6_Base() {
    runCatching { saveUserFinal(User(2, null, null)) }
        .onFailure { System.err.println(it) }

    catchException {
        saveUser(User(1, "null", "null"))
    }
    catchException {
        saveUser(User(1, null, null))
    }
    catchException {
        saveUserAdvanced(User(1, "null", "null"))
    }
    catchException {
        saveUserAdvanced(User(1, null, null))
    }
    catchException {
        saveUserFinal(User(1, "null", "null"))
    }
    catchException {
        saveUserFinal(User(2, "null", "null"))
    }
    catchException {
        saveUserFinal(User(1, null, null))
    }
}

data class User(val id: Int, val name: String?, val address: String?)

fun saveUser(user: User){
    if (user.name.isNullOrEmpty()){
        throw IllegalArgumentException(
            "Can't save user ${user.id}: empty Name"
        )
    }
    if (user.address.isNullOrEmpty()){
        throw IllegalArgumentException(
            "Can't save user ${user.id}: empty Name"
        )
    }

    println("user saved - $user")
    //SAVE DATA IN DB
}

fun saveUserAdvanced(user: User){
    fun validate(/*user: User,*/
    value: String?,
    fieldName:String){
        if (value.isNullOrEmpty()){
            throw IllegalArgumentException(
                "Can't save user ${user.id}: empty $fieldName"
            )
        }
    }

    validate(/*user,*/ user.name, "Name")
    validate(/*user,*/ user.address, "Address")
    println("user saved - $user")
}

fun User.validateBeforeSave(){
    fun validate(value: String?, fieldName:String){
        if (value.isNullOrEmpty()){
            throw IllegalArgumentException(
                "Can't save user ${id}: empty $fieldName"
            )
        }
    }

    validate(name, "Name")
    validate(address, "Address")

    println("user saved - $this")
}

fun saveUserFinal(user: User){
    user.validateBeforeSave()
}