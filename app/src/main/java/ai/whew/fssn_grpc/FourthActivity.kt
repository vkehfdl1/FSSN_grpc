package ai.whew.fssn_grpc

import ai.whew.fssn_grpc.databinding.ActivityFourthBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding

class FourthActivity: AppCompatActivity(R.layout.activity_fourth) {
    private val binding: ActivityFourthBinding by viewBinding()
    lateinit var task: GrpcTask

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        task = GrpcTask()

        binding.button.setOnClickListener {
            task.startServerStreaming(binding.editText.text.toString().toInt())
        }
    }
}
