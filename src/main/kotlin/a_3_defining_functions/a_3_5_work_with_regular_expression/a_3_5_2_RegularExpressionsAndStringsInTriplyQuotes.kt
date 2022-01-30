package a_3_defining_functions.a_3_5_work_with_regular_expression

fun a_3_5_2_RegularExpressionsAndStringsInTriplyQuotes() {
    parsePath("/Users/yole/kotlin-book/chapter.adoc")
    parsePathRegular("/Users/yole/kotlin-book/chapter.adoc")
}

fun parsePath(path: String){
    val directory = path.substringBeforeLast("/")
    val fullName = path.substringAfterLast("/")
    val user = path.substringAfter("/Users/").substringBefore("/")

    val fileName = fullName.substringBeforeLast(".")
    val extension = fullName.substringAfterLast(".")
    println("Dir: $directory, name: $fileName, ext: $extension, user: $user" )
}
fun parsePathRegular(path: String){
    val regex = """(.+)/(.+)\.(.+)""".toRegex()
    val mathResult = regex.matchEntire(path)
    if (mathResult != null){
        val (directory, fileName, extension) = mathResult.destructured
        println("Dir: $directory, name: $fileName, ext: $extension" )
    }

}