package com.example.shepcardpaymentminimalrepo

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import com.example.shepcardpaymentminimalrepo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val paymentResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        handlePaymentActivityResult(it.resultCode, it.data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        binding.submit.setOnClickListener {
            val paymentUrl = binding.paymentUrlInput.text.toString().trim()

            if (paymentUrl.isNotEmpty()) {
                val intent = Intent(this, PaymentActivity::class.java).apply {
                    putExtra(PaymentActivity.PAYMENT_URL_KEY, paymentUrl)
                }
                paymentResultLauncher.launch(intent)
            }
        }
    }

    private fun handlePaymentActivityResult(resultCode: Int, intent: Intent?) {
        val data = intent?.extras?.getSerializable(PaymentActivity.PAYMENT_RESULT_DATA) as PaymentResult?

        binding.resultTitle.isVisible = true
        binding.resultContent.apply {
            text = "Result code: $resultCode\nData: $data"
            isVisible = true
        }
    }
}