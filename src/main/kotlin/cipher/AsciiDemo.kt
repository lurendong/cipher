package cipher

import java.lang.StringBuilder

/**
 * @author: 陆仁东
 * @date: 2020/11/25 09:15
 * @description:
 */
fun main() {
    val loveStr = "i love you"
    val loveArray = loveStr.toCharArray();
    val stringBuilder = StringBuilder()
//    for (ch in loveArray){
//        val result = ch.toInt();
//        stringBuilder.append("$result ")
//        println(result.toChar())
//
//    }
    val result = with(stringBuilder){
        for (ch in loveArray) {
            val result = ch.toInt();
            append("$result ")
            println(result.toChar())
        }
        toString()
    }

    println(result)
}