package com.momocoffe.app.network.repository

import android.util.Log
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

object SocketHandler {
    lateinit var mSocket: Socket

    @Synchronized
    fun setSocket() {
        try {
            mSocket = IO.socket("http://momocoffe-lb-1774679013.us-east-1.elb.amazonaws.com")
            Log.d("com.socket.io", "Init Sockets")
        } catch (e: URISyntaxException) {
            Log.d("com.socket.io", e.toString())
        }
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