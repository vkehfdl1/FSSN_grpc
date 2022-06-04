package ai.whew.fssn_grpc

import ai.whew.fssn_grpc.databinding.ActivityThirdBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding

class ThirdActivity: AppCompatActivity(R.layout.activity_third) {
    private val binding: ActivityThirdBinding by viewBinding()
    private lateinit var task: GrpcTask
    private var stringList: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        task = GrpcTask()

        binding.button.setOnClickListener {
            stringList.add(binding.editText.text.toString())
            task.sendClientStreamingMessage(stringList)
        }
    }
}
