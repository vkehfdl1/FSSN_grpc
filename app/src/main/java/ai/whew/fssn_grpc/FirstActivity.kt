package ai.whew.fssn_grpc

import ai.whew.fssn_grpc.databinding.ActivityFirstBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding

class FirstActivity: AppCompatActivity(R.layout.activity_first) {
    private val binding: ActivityFirstBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }

}
