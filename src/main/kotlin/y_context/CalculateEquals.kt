package main.kotlin.y_context

object CalculateEquals {
    fun calculateEquals(input: Array<String>){
        val space = " "
        val baskSpacesChar = '.'
        //val input = BufferedReader(FileReader("input.txt"))
        //val out = PrintStream("output.txt")
        //val string = input.readLine().split(space)
        val first = input.first()
        val second = input.last()
        val countBackSpacesFirst = Integer.min((first.count { it == baskSpacesChar } * 2), first.length)
        val countBackSpacesSecond = Integer.min(second.count { it == baskSpacesChar } * 2, second.length)
        val result = first.substring(0, first.length - countBackSpacesFirst) == second.substring( 0, second.length - countBackSpacesSecond)
        println(result)
    }
}