package com.daboluo.myapplication

/**
 *作者：daboluo on 2025/2/20 18:54
 *Email:daboluo719@gmail.com
 */
import android.util.Log
import okhttp3.*
import java.util.concurrent.TimeUnit

class WebSocketManager(
    private val serverUrl: String,
    private val androidId: String,
    private val inputProvider: () -> String
) {
    private val client = OkHttpClient.Builder()
        .readTimeout(0, TimeUnit.MILLISECONDS)
        .build()

    private var webSocket: WebSocket? = null

    fun connect() {
        val request = Request.Builder()
            .url("$serverUrl/ws/$androidId")
            .build()
        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                Log.d("WebSocket", "Connected to server")
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                Log.d("WebSocket", "Received message: $text")
                // 解析请求ID和指令（例如 "abcd1234:get_input"）
                val parts = text.split(":", limit = 2)
                if (parts.size == 2 && parts[1] == "get_input") {
                    val requestId = parts[0]
                    val content = inputProvider() // 获取输入框内容
                    webSocket.send("$requestId:$content") // 返回结果
                }
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                Log.d("WebSocket", "Connection closed")
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                Log.e("WebSocket", "Connection failed: ${t.message}")
                // 可在此实现重连逻辑
            }
        })
    }

    fun disconnect() {
        webSocket?.close(1000, "User initiated disconnect")
        client.dispatcher.executorService.shutdown()
    }
}