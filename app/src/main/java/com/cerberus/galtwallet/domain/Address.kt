package com.cerberus.galtwallet.domain

import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

class Address(val plainAddress: String) {
    private var barcodeEncoder = BarcodeEncoder()

    val qrcodeBitmap = barcodeEncoder.encodeBitmap(plainAddress, BarcodeFormat.QR_CODE, 800, 800)
}