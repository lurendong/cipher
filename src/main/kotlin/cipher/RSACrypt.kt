package cipher


import java.io.ByteArrayOutputStream
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
import java.util.*
import javax.crypto.Cipher


/**
 * @author: 陆仁东
 * @date: 2020/11/26 21:47
 * @description:
 */
object RSACrypt {
    private const val transformation = "RSA"


    private const val ENCRYPT_MAX_SIZE = 117;
    private const val DECRYPT_MAX_SIZE = 128;
    fun rsaEncryptByPrivate(input:String,privateKey: PrivateKey): String {
        val byteArray = input.toByteArray()
        val rsaCiper = Cipher.getInstance(transformation)
        rsaCiper.init(Cipher.ENCRYPT_MODE,privateKey);



        var temp: ByteArray? = null
        var current_offset = 0;


       val bos = ByteArrayOutputStream()

        while (byteArray.size - current_offset > 0){
            if((byteArray.size - current_offset) >= ENCRYPT_MAX_SIZE){
                temp = rsaCiper.doFinal(byteArray,current_offset, ENCRYPT_MAX_SIZE)
                current_offset += ENCRYPT_MAX_SIZE;
            }else{
                temp = rsaCiper.doFinal(byteArray, current_offset, byteArray.size - current_offset)
                current_offset = byteArray.size;
            }
            bos.write(temp)

        }
        bos.close()

        return String(Base64.getEncoder().encode(bos.toByteArray()))


    }
    fun rsaEncryptByPublicKey(input:String,publicKey: PublicKey): String {
//
        val byteArray = input.toByteArray()


        //1.创建cipher对象
        val cipher = Cipher.getInstance(RSACryptDemo.transformation)
        //2.初始化cipher
        cipher.init(Cipher.ENCRYPT_MODE, publicKey)
        //3.加密：分段加密
        //val encrypt = cipher.doFinal(input.toByteArray())

        var temp: ByteArray? = null
        var offset = 0 //当前偏移的位置

        val bos = ByteArrayOutputStream()

        while (byteArray.size - offset > 0) { //没加密完
            //每次最大加密117个字节
            if (byteArray.size - offset >= RSACryptDemo.ENCRYPT_MAX_SIZE) {
                //剩余部分大于117
                //加密完整117
                temp = cipher.doFinal(byteArray, offset, RSACryptDemo.ENCRYPT_MAX_SIZE)
                //重新计算偏移的位置
                offset += RSACryptDemo.ENCRYPT_MAX_SIZE
            } else {
                //加密最后一块
                temp = cipher.doFinal(byteArray, offset, byteArray.size - offset)
                //重新计算偏移的位置
                offset = byteArray.size
            }
            //存储到临时缓冲区
            bos.write(temp)
        }
        bos.close()


        return String(Base64.getEncoder().encode(bos.toByteArray()))
    }
    fun rsaDecryptByPrivate(input:String,privateKey:PrivateKey): String {
//        val rsaCiper = Cipher.getInstance(transformation)
//        val byteArray = Base64.getDecoder().decode(input)
//
//        var temp:ByteArray ?= null
//        var current_offset = 0;
//        rsaCiper.init(Cipher.DECRYPT_MODE,privateKey);
//        val byteArrayOutputStream = ByteArrayOutputStream()
//        while (byteArray.size - current_offset > 0){
//            if((byteArray.size - current_offset) >= DECRYPT_MAX_SIZE){
//                temp = rsaCiper.doFinal(byteArray,current_offset, DECRYPT_MAX_SIZE)
//                current_offset += DECRYPT_MAX_SIZE;
//            }else{
//                temp = rsaCiper.doFinal(byteArray, current_offset, byteArray.size - current_offset)
//                current_offset = byteArray.size;
//            }
//            byteArrayOutputStream.write(temp)
//        }
//        byteArrayOutputStream.close()
//
//        return String(byteArrayOutputStream.toByteArray())
        val byteArray = Base64.getDecoder().decode(input)


        //1.创建cipher对象
        val cipher = Cipher.getInstance(RSACryptDemo.transformation)
        //2.初始化cipher
        cipher.init(Cipher.DECRYPT_MODE, privateKey)
        //3.分段解密
        var temp: ByteArray? = null
        var offset = 0 //当前偏移的位置

        val bos = ByteArrayOutputStream()

        while (byteArray.size - offset > 0) { //没加密完
            //每次最大解密128个字节
            if (byteArray.size - offset >= RSACryptDemo.DECRYPT_MAX_SIZE) {
                temp = cipher.doFinal(byteArray, offset, RSACryptDemo.DECRYPT_MAX_SIZE)
                //重新计算偏移的位置
                offset += RSACryptDemo.DECRYPT_MAX_SIZE
            } else {
                //加密最后一块
                temp = cipher.doFinal(byteArray, offset, byteArray.size - offset)
                //重新计算偏移的位置
                offset = byteArray.size
            }
            //存储到临时缓冲区
            bos.write(temp)
        }
        bos.close()


        return String(bos.toByteArray())
    }

    fun rsaDecryptByPublicKey(input:String,publicKey:PublicKey): String {
        val byteArray = Base64.getDecoder().decode(input)


        val rsaCiper = Cipher.getInstance(transformation)
        rsaCiper.init(Cipher.DECRYPT_MODE,publicKey);

        var temp:ByteArray ?= null

        var current_offset = 0;

        val byteArrayOutputStream = ByteArrayOutputStream()
        while (byteArray.size - current_offset > 0){
            if((byteArray.size - current_offset) >= DECRYPT_MAX_SIZE){
                temp = rsaCiper.doFinal(byteArray,current_offset, DECRYPT_MAX_SIZE)
                current_offset += DECRYPT_MAX_SIZE;
            }else{
                temp = rsaCiper.doFinal(byteArray, current_offset, byteArray.size - current_offset)
                current_offset = byteArray.size;
            }
            byteArrayOutputStream.write(temp)

        }
        byteArrayOutputStream.close()



        return String(byteArrayOutputStream.toByteArray())

    }


}

fun main() {
    //生成密钥对
    val generator = KeyPairGenerator.getInstance("RSA")

    val keyPair = generator.genKeyPair()

    val privateKey = keyPair.private
    val publicKey = keyPair.public
    val input = "你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好程序员"
    var encryptedPrivateKeyInfo: String = RSACrypt.rsaEncryptByPublicKey(input,publicKey);
    println("加密密文$encryptedPrivateKeyInfo")
    println(RSACrypt.rsaDecryptByPrivate(encryptedPrivateKeyInfo,privateKey))
}