package cipher

import java.lang.StringBuilder

/**
 * @author: 陆仁东
 * @date: 2020/11/25 09:35
 * @description:
 */
class Kaiser {
    fun encipher(input:String,key:Int):String {
        return with(StringBuilder()){
            val charArray = input.toCharArray();
            charArray.forEach {
                val c = it
                var ascii = c.toInt()
                ascii += key
                val result = ascii.toChar()

                append("$result")
            }
            toString()
        }

    }
    fun decryption(input: String,key: Int){
        return with(StringBuilder()){
            val charArray = input.toCharArray();
            charArray.forEach {
                val c = it
                var ascii = c.toInt()
                ascii -= key
                val result = ascii.toChar()

                append("$result")
            }
            toString()
        }

    }
}
fun main() {
    // move char to cipher

    val key = 3
    val command = "l love you"
    var result = Kaiser().encipher(command,key)
    print(result)
}