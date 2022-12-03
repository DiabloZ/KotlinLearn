package leetcode.beginer

object AddBinary67 {
    fun addBinary(a: String, b: String): String {
        var stringBuilder = ""
        var isCarryingChar = false
        var count = 1
        val zero = '0'
        val one = '1'
        fun getCharOrNull(): Char? {
            return if (isCarryingChar){
                isCarryingChar = false
                one
            } else {
                null
            }
        }
        while (count >= 0){
            val firstValue = a.getOrNull(a.length - count) ?: getCharOrNull()
            val secondValue = b.getOrNull(b.length - count) ?: getCharOrNull()
            when{
                firstValue == zero && secondValue == zero -> {
                    if (!isCarryingChar){
                        stringBuilder = "$zero$stringBuilder"
                    }
                    if (isCarryingChar){
                        stringBuilder = "$one$stringBuilder"
                        isCarryingChar = false
                    }
                }
                firstValue == one && secondValue == zero || firstValue == zero && secondValue == one -> {
                    if (isCarryingChar){
                        stringBuilder = "$zero$stringBuilder"
                    }
                    if(!isCarryingChar) {
                        stringBuilder = "$one$stringBuilder"
                    }
                }
                firstValue == one && secondValue == one -> {
                    if (isCarryingChar){
                        stringBuilder = "$one$stringBuilder"
                    }
                    if (!isCarryingChar){
                        isCarryingChar = true
                        stringBuilder = "$zero$stringBuilder"
                    }
                }
                firstValue == null && secondValue == one || firstValue == one && secondValue == null -> {
                    stringBuilder = "$one$stringBuilder"
                }
                firstValue == null && secondValue == zero || firstValue == zero && secondValue == null -> {
                    stringBuilder = "$zero$stringBuilder"
                }
                firstValue == null && secondValue == null -> {
                    if (isCarryingChar){
                        stringBuilder = "$one$stringBuilder"
                    }
                    break
                }
            }
            ++count
        }
        return stringBuilder
    }
}