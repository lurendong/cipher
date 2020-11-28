package cipher

import java.lang.StringBuilder
import java.security.MessageDigest

/**
 * @author: 陆仁东
 * @date: 2020/11/28 00:30
 * @description:
 */
object MessageDigetUtil {
    fun md5(input:String):String{
        val digest = MessageDigest.getInstance("MD5")
        //MD5 摘要 返回byte数组 不要打印  需腰转换为16进制
        //16个字节
        //转变16进制后 变为32位
        val result = digest.digest(input.toByteArray())

        //市场上MD5摘要通常需要转换为16进制
        println(toHex(result))
        return toHex(result)
    }
    fun toHex(byteArray: ByteArray):String{
        //市场上MD5摘要通常需要转换为16进制
        val stringBuilder = StringBuilder()
        byteArray.forEach {
            val value = it;

            val hex = value.toInt() and 0xFF
            val hexStr = Integer.toHexString(hex)

            if (hexStr.length == 1){
                stringBuilder.append("0").append(hexStr)
            }else{
                stringBuilder.append(hexStr)
            }

        }
        return stringBuilder.toString()
    }
    fun sha1(input: String):String{

        val digest = MessageDigest.getInstance("SHA-1")
        //加密后20个字节
        val result = digest.digest(input.toByteArray())
        //转换为16进制后为40个字节
        return toHex(result)
    }
    fun sha256(input: String):String{

        val digest = MessageDigest.getInstance("SHA-256")
        //加密后32个字节
        val result = digest.digest(input.toByteArray())
        //转换为16进制后为64个字节
        return toHex(result)
    }
}

fun main() {
    val input = "hello digest"
    println(MessageDigetUtil.sha1(input))
    println(MessageDigetUtil.sha256(input))
}