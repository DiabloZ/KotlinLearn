package main.kotlin.phone_mask

fun main(){
    val oldText = "(903) 550-"
    val newText = "(903)b550-16-4"
    val phoneMask = PhoneMaskResponse(
        phoneMask = "(###) ###-##-##",
        digitsCount = 10,
    )

    PhoneMaskHandler.handle (
        oldText = oldText,
        newText = newText,
        maskModel = phoneMask
    )
}

data class PhoneMaskResponse(
    val phoneMask: String? = null,
    val digitsCount: Int? = null,
    val phoneCode: String? = null
)
object PhoneMaskHandler {
    val numberArray = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
    fun handle(
        /*oldText: String,*/
        newText: String,
        maskModel: PhoneMaskResponse
    ): String? {
        /*val rawOld = oldText.toCharArray().filter { it in numberArray }*/
        val rawNew = newText.toCharArray().filter { it in numberArray }
        val mask = maskModel.phoneMask ?: return null

        var cursor = 0
        var newString = ""
        mask.forEach { char ->
            if (char != '#') {
                newString += char
                return@forEach
            }
            if (rawNew.size < cursor) return@forEach
            val number = rawNew.getOrNull(cursor++) ?: return@forEach
            newString += number
        }

        return newString
    }
}

fun String.getSafety(index: Int): Char? {
    val result = kotlin.runCatching { return this[index] }
    result.exceptionOrNull().let { exception ->
        /*Timber.e(exception)*/
    }
    return null
}