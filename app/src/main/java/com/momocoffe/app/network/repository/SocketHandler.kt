package com.momocoffe.app.network.repository

import android.util.Log
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import java.net.URISyntaxException

object SocketHandler {
    lateinit var mSocket: Socket

    @Synchronized
    fun setSocket() {
        try {
            val options = IO.Options()
            options.transports = arrayOf("websocket")
            mSocket = IO.socket("http://momocoffe-lb-1774679013.us-east-1.elb.amazonaws.com/", options )
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }

        mSocket.on(Socket.EVENT_CONNECT, onConnect)
        mSocket.on(Socket.EVENT_DISCONNECT, onDisconnect)
        mSocket.on(Socket.EVENT_CONNECT_ERROR, onErrorConnect)

    }

    private val onErrorConnect = Emitter.Listener { args ->
        val exception = args[0] as Exception
        Log.e("com.socket.io", "Error al conectar el socket: ${exception.message}")
    }

    private val onConnect = Emitter.Listener {
        Log.d("com.socket.io","Socket connected")
    }

    private val onDisconnect = Emitter.Listener {
        Log.d("com.socket.io","Socket disconnected")
    }


    @Synchronized
    fun getSocket(): Socket {
        return mSocket
    }

    @Synchronized
    fun establishConnection() {
        mSocket.connect()
    }

    @Synchronized
    fun closeConnection() {
        mSocket.disconnect()
    }
}