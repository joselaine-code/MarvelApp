package com.joselaine.marvelapp.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*
import kotlin.time.Duration.Companion.seconds

class AuthorizationInterceptor(
    private val publicKey: String,
    private val privateKey: String,
    private val calendar: Calendar
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestUrl = request.url
        val timestamp = (calendar.timeInMillis.seconds).toString()
        val hash = "$timestamp$privateKey$publicKey".md5()
        val newUrl = requestUrl.newBuilder()
            .addQueryParameter(QUERY_PARAMETER_TS, timestamp)
            .addQueryParameter(QUERY_PARAMETER_API_KEY, publicKey)
            .addQueryParameter(QUERY_PARAMETER_HASH, hash)
            .build()

        return chain.proceed(
            request.newBuilder()
                .url(newUrl)
                .build()
        )
    }

    private fun String.md5(): String {
        val md = MessageDigest.getInstance(ALGORITHM)
        return BigInteger(SIGNUM, md.digest(toByteArray())).toString(RADIX)
            .padStart(LENGTH, PAD_CHAR)
    }

    companion object {
        private const val ALGORITHM = "MD5"
        private const val SIGNUM = 1
        private const val RADIX = 16
        private const val LENGTH = 32
        private const val PAD_CHAR = '0'

        private const val QUERY_PARAMETER_TS = "ts"
        private const val QUERY_PARAMETER_API_KEY = "apikey"
        private const val QUERY_PARAMETER_HASH = "hash"
    }
}