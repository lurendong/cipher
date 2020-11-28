package cipher

import java.security.Key
import java.security.spec.KeySpec
import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.SecretKeySpec

/**
 * @author: 陆仁东
 * @date: 2020/11/25 15:18
 * @description:
 */
object AESCrypt{

    //AES 密钥字节长度 16位 16个字节 16*8 128 所有位数参与


    fun encipher(input:String,password:String):ByteArray{
        val cipher = Cipher.getInstance("AES")

        val aesKeyspec:SecretKeySpec = SecretKeySpec(password.toByteArray(),"AES");

        cipher.init(Cipher.ENCRYPT_MODE,aesKeyspec)
        val encrypt = cipher.doFinal(input.toByteArray())

        val result = Base64.getEncoder().encode(encrypt);
        println("AES加密 = $result");
        return result;
    }
    fun decipher(input:ByteArray,password:String):ByteArray{
        val cipher = Cipher.getInstance("AES")

        val aesKeyspec:SecretKeySpec = SecretKeySpec(password.toByteArray(),"AES");

        cipher.init(Cipher.DECRYPT_MODE,aesKeyspec)
        val decrypt = cipher.doFinal(Base64.getDecoder().decode(input))

        val result = decrypt
        println("AES解密 = $result");
        return result;
    }
}

fun main() {
    val input = "AES加密测试"
    //AES加密必须16位
    val password = "i_love_you??????"
    println(String(AESCrypt.encipher(input,password)))
    println(String(AESCrypt.decipher(AESCrypt.encipher(input,password),password)))
}