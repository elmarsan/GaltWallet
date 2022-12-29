package com.cerberus.galtwallet.presentation.send

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.CompoundBarcodeView

@Composable
fun QRScanner() {
    var scanFlag by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }

    Column {
        Text(text = text)

        AndroidView(
            factory = { context ->
                CompoundBarcodeView(context).apply {
                    val capture = CaptureManager(context as Activity, this)
                    capture.initializeFromIntent(context.intent, null)
                    this.setStatusText("")
                    capture.decode()
                    this.decodeContinuous { result ->
                        if (scanFlag) {
                            return@decodeContinuous
                        }
                        println("scanFlag true")
                        scanFlag = true
                        result.text?.let { barCodeOrQr ->
                            text = barCodeOrQr
                            //Do something and when you finish this something
                            //put scanFlag = false to scan another item
                            scanFlag = false
                        }
                        //If you don't put this scanFlag = false, it will never work again.
                        //you can put a delay over 2 seconds and then scanFlag = false to prevent multiple scanning
                    }
                    this.resume()
                }
            },
            modifier = Modifier
        )
    }
}