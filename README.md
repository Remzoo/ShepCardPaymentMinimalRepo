# ShepCardPaymentMinimalRepo

This is a minimal repo that shows the problem with rendering an SZEP Card payment screen after trying to open a payment URL in a WebView.

![szep-card-error-gif](https://private-user-images.githubusercontent.com/10036526/381529665-881177ad-8c2c-472e-ab43-d2096f9c0706.gif?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MzAyODkwMTcsIm5iZiI6MTczMDI4ODcxNywicGF0aCI6Ii8xMDAzNjUyNi8zODE1Mjk2NjUtODgxMTc3YWQtOGMyYy00NzJlLWFiNDMtZDIwOTZmOWMwNzA2LmdpZj9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDEwMzAlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQxMDMwVDExNDUxN1omWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPWVjNGQwMWI4ZWQyYTExODRhNWNiMjMzMjJlMzk5M2M1NzJjNjNlYTFiZTg5OGFkMDNmMWFiNDBlMDE2OTEyMDImWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0In0.QHj0ohixaZJUmaCi7ziv2Dnk-P-q8Ue7q_1NJ_ZUkuI)

After loading a payment URL e.g ``https://system-test.paymentgateway.hu/Start?TransactionId=8219d46f5cc9a240f6f5f5c4c9e070df`` to the WebView the web engine Chromium shows an error:

``[INFO:CONSOLE(154)] "Uncaught ReferenceError: loaded is not defined", source: https://ebank.khb.hu/PaymentGatewayTest/PGPayment?mid=3005&txid=6493252613&type=PU&amount=2550&ccy=HUF&szep_zseb=2&lang=EN&sign=4dab0e8385f706a97fc8c5ca95d70f3ccf0c5b350abe51934b4392940764ea245dad5dd9b0ff9d20e2246fe00d169588ff7619e774d02886a9a5949c59a91ca8b487ea89b023d34fbbe1f3dc51aa930d0c7bf936b7a00240c6a7249837a41181dbe99de6db31506d0f284963a8afcf5a0a3978509a8e0be7af7cb2f379ca0757 (154)
``


## Reproduction steps

1. Install attached ``app-debug.apk`` file on the Android emulator Pixel 6a API 29
2. Generate transaction URL in the format ``https://system-test.paymentgateway.hu/Start?TransactionId=8219d46f5cc9a240f6f5f5c4c9e070df``
3. Paste the URL in the input field and click the SUBMIT button
4. The app will open a new activity and then immediately go back showing an error
