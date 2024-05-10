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
            val options = IO.Options().apply {
                transports = arrayOf("websocket", "polling")
            }
            // "http://10.0.2.2:3000" is the network your Android emulator must use to join the localhost network on your computer
            mSocket = IO.socket("http://192.168.20.30:3000", options)
            mSocket.on(Socket.EVENT_CONNECT, Emitter.Listener {
                Log.d("Socket.IO", "Conectado")
                // Aquí puedes enviar mensajes al servidor o realizar otras acciones al conectarte
            }).on(Socket.EVENT_DISCONNECT, Emitter.Listener {
                Log.d("Socket.IO", "Desconectado")
            }).on(Socket.EVENT_CONNECT_ERROR, Emitter.Listener {
                Log.e("Socket.IO", "Error de conexión: ${it[0]}")
            })

        } catch (e: URISyntaxException) {
            e.printStackTrace();
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