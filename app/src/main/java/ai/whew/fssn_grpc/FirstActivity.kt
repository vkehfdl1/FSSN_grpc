package ai.whew.fssn_grpc

import ai.whew.fssn_grpc.databinding.ActivityFirstBinding
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit

class FirstActivity: AppCompatActivity(R.layout.activity_first) {
    private val binding: ActivityFirstBinding by viewBinding()
    private lateinit var task: GrpcTask

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        task = GrpcTask()

        binding.button.setOnClickListener {
            val future = task.myFunction(binding.editText.text.toString().toInt())
            future.addListener({
                try {
                    val reply = future.get(0, TimeUnit.SECONDS)
                    binding.resultTextView.post {
                        binding.resultTextView.text = reply.value.toString()
                    }
                } catch (e: ExecutionException) {
                    Log.e(TAG, "$e")
                }
            }, ContextCompat.getMainExecutor(this))
        }
    }

    companion object {
        const val TAG = "FirstActivity"
    }

}
