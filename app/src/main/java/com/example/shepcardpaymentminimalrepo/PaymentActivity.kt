package com.example.shepcardpaymentminimalrepo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.shepcardpaymentminimalrepo.databinding.ActivityPaymentBinding

class PaymentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupWebView()
        loadUrl()
    }

    private fun setupToolbar() {
        binding.back.setOnClickListener {
            finish()
        }
    }

    private fun setupWebView() {
        val customWebViewClient = object : WebViewClient() {
            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                Log.d(TAG, "onReceivedError: request: $request | error: $error")
                super.onReceivedError(view, request, error)
                finishActivityWithError(error)
            }
        }

        binding.webView.apply {
            settings.apply {
                @SuppressLint("SetJavaScriptEnabled")
                javaScriptEnabled = true
                domStorageEnabled = true
                useWideViewPort = true
            }

            setInitialScale(1)
            webViewClient = customWebViewClient
        }
    }

    private fun loadUrl() {
        intent.getStringExtra(PAYMENT_URL_KEY)?.let { paymentUrl ->
            binding.webView.loadUrl(paymentUrl)
        }
    }

    private fun finishActivityWithError(error: WebResourceError?) {
        val result = PaymentResult(isSuccess = false, errorCode = error?.errorCode, errorDescription = error?.description)
        val resultIntent = Intent().apply {
            putExtra(PAYMENT_RESULT_DATA, result)
        }

        setResult(RESULT_OK, resultIntent)
        finish()
    }

    companion object {
        const val TAG = "PaymentActivity"

        const val PAYMENT_URL_KEY = "paymentUrlKey"
        const val PAYMENT_RESULT_DATA = "paymentResultData"
    }
}