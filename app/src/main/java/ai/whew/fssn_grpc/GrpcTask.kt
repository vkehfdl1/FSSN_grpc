package ai.whew.fssn_grpc

import HelloGrpc
import MyServiceGrpc
import com.google.common.util.concurrent.ListenableFuture
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor

class GrpcTask {
    private val channel: ManagedChannel = ManagedChannelBuilder.forAddress("mkseoul.iptime.org", 6666)
        .usePlaintext()
        .executor(Dispatchers.IO.asExecutor())
        .build()

    private val stub = MyServiceGrpc.newFutureStub(channel)

    fun myFunction(
        number: Int
    ): ListenableFuture<HelloGrpc.MyNumber> {
        val request = HelloGrpc.MyNumber.newBuilder()
            .setValue(number)
            .build()
        return stub.myFunction(request)
    }

    fun shutdown() {
        channel.shutdownNow()
    }
}
