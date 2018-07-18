package com.hiray.util

import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.support.annotation.RequiresApi
import android.util.Log
import com.hiray.BuildConfig
import java.security.KeyPairGenerator
import javax.crypto.KeyGenerator

@RequiresApi(Build.VERSION_CODES.M)
class CipherUtil {

    val alias = "KeyAlias"

    init {
        val ANDROID_KEY_STORE = "AndroidKeyStore"
        val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEY_STORE)

        val kpg = KeyPairGenerator.getInstance(
                KeyProperties.KEY_ALGORITHM_EC, "AndroidKeyStore")
        kpg.initialize(KeyGenParameterSpec.Builder(
                alias,
                KeyProperties.PURPOSE_SIGN or KeyProperties.PURPOSE_VERIFY)
                .setDigests(KeyProperties.DIGEST_SHA256,
                        KeyProperties.DIGEST_SHA512)
                .build())

        val kp = kpg.generateKeyPair()
        if (BuildConfig.DEBUG)
            Log.i("CipherUtil", "pubKey:${kp.public} \n priKey:${kp.private}")
    }

    companion object {
        fun put(key: String, value: String) {

        }
    }
}