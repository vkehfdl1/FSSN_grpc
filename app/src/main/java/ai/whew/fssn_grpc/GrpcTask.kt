package ai.whew.fssn_grpc

import HelloGrpc
import MyServiceGrpc
import android.util.Log
import bidirectional.BidirectionalGrpc
import bidirectional.BidirectionalOuterClass
import clientstreaming.ClientStreamingGrpc
import clientstreaming.Clientstreaming
import com.google.common.util.concurrent.ListenableFuture
import io.grpc.ClientCall
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.stub.StreamObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import serverstreaming.ServerStreamingGrpc
import serverstreaming.Serverstreaming
import java.lang.Exception
import java.util.*

class GrpcTask {
    private val channel: ManagedChannel = ManagedChannelBuilder.forAddress("mkseoul.iptime.org", 6666)
        .usePlaintext()
        .executor(Dispatchers.IO.asExecutor())
        .build()

    private val stub = MyServiceGrpc.newFutureStub(channel)
    private val bidirectionalStub = BidirectionalGrpc.newStub(channel)
    private val clientStub = ClientStreamingGrpc.newStub(channel)
    private val serverStub = ServerStreamingGrpc.newBlockingStub(channel)

    fun myFunction(
        number: Int
    ): ListenableFuture<HelloGrpc.MyNumber> {
        val request = HelloGrpc.MyNumber.newBuilder()
            .setValue(number)
            .build()
        return stub.myFunction(request)
    }

    fun bidirectionalStreamMessage(messages: List<String>) {
        val requestObserver = bidirectionalStub.getServerResponse(BidirectionalResponseStreamObserver())
        for (message in messages) {
            requestObserver.onNext(generateBidirectionalMessage(message))
        }
        requestObserver.onCompleted()
    }

    private fun generateBidirectionalMessage(message: String): BidirectionalOuterClass.Message {
        return BidirectionalOuterClass.Message.newBuilder().setMessage(message).build()
    }

    fun sendClientStreamingMessage(messages: List<String>) {
        val requestObserver = clientStub.getServerResponse(ClientResponseStreamObserver())
        for (message in messages) {
            requestObserver.onNext(Clientstreaming.ClientMessage.newBuilder().setMessage(message).build())
        }
        requestObserver.onCompleted()
    }

    fun startServerStreaming(num: Int) {
        val response = serverStub.getServerResponse(Serverstreaming.ServerNumber.newBuilder().setValue(num).build())
        response.forEach {
            Log.d(TAG, "server streaming response : ${it.message}")
        }
    }


    fun shutdown() {
        channel.shutdownNow()
    }

    class BidirectionalResponseStreamObserver: StreamObserver<BidirectionalOuterClass.Message> {
        override fun onNext(value: BidirectionalOuterClass.Message?) {
            Log.d(TAG, "${value?.message}")
        }

        override fun onError(t: Throwable?) {
            Log.e(TAG, "error at bidirectional stream observer", t)
        }

        override fun onCompleted() {
            Log.d(TAG, "bidirectional stream completed")
        }

    }

    class ClientResponseStreamObserver: StreamObserver<Clientstreaming.Number> {
        override fun onNext(value: Clientstreaming.Number?) {
            value?.value?.let {
                Log.d(TAG, "sum is $it")
            }
        }

        override fun onError(t: Throwable?) {
            Log.e(TAG, "error at client-side stream observer", t)
        }

        override fun onCompleted() {
            Log.d(TAG, "client-side stream completed")
        }

    }

    class ServerResponseStreamObserver: StreamObserver<Serverstreaming.ServerMessage> {
        override fun onNext(value: Serverstreaming.ServerMessage?) {
            value?.message?.let {
                Log.d(TAG, "received message : $it")
            }
        }

        override fun onError(t: Throwable?) {
            Log.e(TAG, "error at server-side stream observer", t)
        }

        override fun onCompleted() {
            Log.d(TAG, "server-side stream completed")
        }

    }

    companion object {
        const val TAG = "GrpcTask"
    }
}
