package cipher

import java.security.Key
import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.DESKeySpec

/**
 * @author: 陆仁东
 * @date: 2020/11/25 10:54
 * @description:
 */
//创建 cipher 对象
//初始化cipher
//加解密
fun main() {
    //
    val input = "des加密demo"
    //DES 密钥 8位 密钥des长度8位  DES前7位不参与加密计算，最后一位作为校验码 
    val password = "125489451"

    println(String(DESCrypt.encrypt(input, password)))
    println("des 解密 ："+ String(DESCrypt.desDeCrypt(DESCrypt.encrypt(input, password),password)))

}
object DESCrypt {
    fun encrypt(input:String,password:String):ByteArray{
        val desCipher = Cipher.getInstance("DES")
        /**
         * @param int 加密模式
         * @param key 密钥
         */
        val des_skf = SecretKeyFactory.getInstance("DES")

        val deskeySpec = DESKeySpec(password.toByteArray())

        val key:Key?=des_skf.generateSecret(deskeySpec);
        desCipher.init(Cipher.ENCRYPT_MODE,key)
        val desEncrypt = desCipher.doFinal(input.toByteArray())

        println(Base64.getEncoder().encode(desEncrypt))
        return Base64.getEncoder().encode(desEncrypt);


    }
    fun desDeCrypt(input:ByteArray,password:String):ByteArray{
        val desCipher = Cipher.getInstance("DES")
        /**
         * @param int 加密模式
         * @param key 密钥
         */
        val des_skf = SecretKeyFactory.getInstance("DES")

        val deskeySpec = DESKeySpec(password.toByteArray())

        val key:Key?=des_skf.generateSecret(deskeySpec);
        desCipher.init(Cipher.DECRYPT_MODE,key)
        val desDecrypt = desCipher.doFinal(Base64.getDecoder().decode(input))

        return desDecrypt
    }
}