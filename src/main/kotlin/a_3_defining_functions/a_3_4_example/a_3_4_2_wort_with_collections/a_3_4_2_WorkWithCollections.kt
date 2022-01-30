package a_3_defining_functions.a_3_4_example.a_3_4_2_wort_with_collections

fun a_3_4_2_UnpackCollectionsFromArray() {
    val string = arrayOf("one", "two", "three")
    listOf("test: ", *string, *string ).apply {
        println(this) // '*' - распаковала содержимое массива
    }
}