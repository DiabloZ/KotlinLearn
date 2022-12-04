package main.kotlin.utils

import org.apache.logging.log4j.LogManager

object Logger {
    private val logger = LogManager.getLogger()
    fun printText(any: Any, text: Any){
        //println("Function - $any, text - $text")
        logger.info("Function - $any, text - $text")
    }
}