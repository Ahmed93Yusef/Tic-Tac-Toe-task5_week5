package com.example.tic_tac_task5
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.tic_tac_task5.databinding.WinActivityBinding

class WinScreen: AppCompatActivity() {
    private lateinit var binding: WinActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WinActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val player = intent.getStringExtra("player")
        if(player == "Tie") binding.messageBoxHeader.text = "TIE"
        else binding.messageBoxHeader.text = "$player Win"

        Handler().postDelayed({
          startActivity(Intent(this@WinScreen , MainActivity ::class.java))
                              },2000)
    }
    override fun onPause() {
        super.onPause()
        finish()
    }

}
