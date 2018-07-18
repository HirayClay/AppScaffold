package com.hiray.tsl

import android.app.Application
import android.content.Context
import com.hiray.R
import okhttp3.OkHttpClient
import java.security.KeyStore
import java.security.cert.CertificateFactory
import java.util.*
import javax.inject.Inject
import javax.net.ssl.*


class TslProvider constructor(var appContext: Context) {

    var trustManager: X509TrustManager
    var sslSocketFactory: SSLSocketFactory

    init {
        trustManager = createTrustManager()
        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, arrayOf(trustManager), null)
        sslSocketFactory = sslContext.socketFactory
    }

    private fun createTrustManager(): X509TrustManager {
        val cerIns = appContext.resources.openRawResource(R.raw.revor)
        val certificateFactory = CertificateFactory.getInstance("X.509")
        val certificates = certificateFactory.generateCertificates(cerIns)
        if (certificates.isEmpty())
            throw IllegalStateException("expect no-empty trusted certificates")

        val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
        val password = "123456".toCharArray()
        keyStore.load(null, password)

        for ((alias, cer) in certificates.withIndex()) {
            keyStore.setCertificateEntry(alias.toString(), cer)
        }

        val keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm())
        val trustManFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
        keyManagerFactory.init(keyStore, password)
        trustManFactory.init(keyStore)
        val trustManagers = trustManFactory.trustManagers
        if (trustManagers.size != 1 || trustManagers[0] !is X509TrustManager)
            throw IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers))
        return trustManagers[0] as X509TrustManager
    }


}