package com.daboluo.myapplication

import android.util.Log
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI

/**
 *作者：daboluo on 2025/2/20 18:04
 *Email:daboluo719@gmail.com
 */
class MyWebSocketClient(serverUri: URI) : WebSocketClient(serverUri) {

   // #selectedCode


override fun onOpen(handshakedata: ServerHandshake?) {
    Log.d("MyWebSocketClient", "Connected to server")
}

override fun onMessage(message: String?) {
    // 这里处理从服务器返回的数据
    Log.d("MyWebSocketClient", "Message from server: $message")
}

override fun onClose(code: Int, reason: String?, remote: Boolean) {
    Log.d("MyWebSocketClient", "Connection closed: $reason")
}

override fun onError(ex: Exception?) {
    Log.d("MyWebSocketClient", "Error occurred: ${ex?.message}")
}

}