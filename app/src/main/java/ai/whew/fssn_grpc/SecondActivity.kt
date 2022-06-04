package ai.whew.fssn_grpc

import ai.whew.fssn_grpc.databinding.ActivitySecondBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding

class SecondActivity : AppCompatActivity(R.layout.activity_second) {
    private val binding: ActivitySecondBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val task = GrpcTask()
        task.bidirectionalStreamMessage(listOf(
            "message #1",
            "message #2",
            "message #3",
            "message #4",
            "message #5",
        ))
    }
}
