package ai.whew.fssn_grpc

import ai.whew.fssn_grpc.databinding.ActivityMainBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding: ActivityMainBinding by viewBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            startActivity(Intent(this, FirstActivity::class.java))
        }

        binding.button2.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
}
