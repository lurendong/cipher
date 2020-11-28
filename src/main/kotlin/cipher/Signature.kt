package cipher

import sun.misc.BASE64Encoder
import java.security.PrivateKey
import java.security.PublicKey
import java.security.Signature
import java.util.*

/**
 * @author: 陆仁东
 * @date: 2020/11/28 10:11
 * @description:
 */
object SignatureUtils {
    fun sign(input:String,privateKey: PrivateKey): String {

        val signature = Signature.getInstance("SHA256withRSA")
        //init signature

        signature.initSign(privateKey)

        signature.update(input.toByteArray())
        //签名
        val sign = signature.sign()
        println(String(Base64.getEncoder().encode(sign)))
        return String(Base64.getEncoder().encode(sign))
    }
    fun verification(input:String,publicKey: PublicKey,sign:String):Boolean{
        val signature = Signature.getInstance("SHA256withRSA")

        //舒适化签名认证
        signature.initVerify(publicKey)

        signature.update(input.toByteArray())

        //校验签名
        val verifyStatus = signature.verify(Base64.getDecoder().decode(sign))
        //把签名和数据源返回


        return verifyStatus
    }
}

fun main() {
    val input = "goods=ipad&price=2888"
    val privateKey = RSACryptDemo.getPrivateKey()
    val publicKey:PublicKey = RSACryptDemo.getPublicKey()
    val sign = SignatureUtils.sign(input,privateKey)
    val reslutstatus = SignatureUtils.verification(input,publicKey,sign)
    println(reslutstatus)
}