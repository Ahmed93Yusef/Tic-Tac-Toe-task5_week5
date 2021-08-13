package com.example.tic_tac_task5
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.tic_tac_task5.databinding.ActivityMainBinding
import com.google.android.material.imageview.ShapeableImageView
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private var table = arrayListOf("", "", "", "", "", "", "", "", "")
    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonReset.setOnClickListener {
            startActivity(Intent(this@MainActivity, MainActivity::class.java))
        }


    }


    fun onclickLocation(view : View){
        when((view as ShapeableImageView)){
            binding.button1 -> selectLocation(view, 0)
            binding.button2-> selectLocation(view, 1)
            binding.button3 -> selectLocation(view, 2)
            binding.button4 -> selectLocation(view, 3)
            binding.button5 -> selectLocation(view, 4)
            binding.button6 -> selectLocation(view, 5)
            binding.button7 -> selectLocation(view, 6)
            binding.button8 -> selectLocation(view, 7)
            binding.button9 -> selectLocation(view, 8)
        }
    }

    private fun selectLocation(view : View, positionIndex : Int){
        if (table[positionIndex] == "") {
            (view as ShapeableImageView).setImageResource(R.drawable.ic_x)
            table[positionIndex] = "X"
            if(!isTableFull(table) && !result(table, "X")) {
                val position = getComputerMove(table)
                table[position] = "O"
                displayComputerSelect(position)
            }
        }
        resultOut(table)
    }

    private fun resultOut(board: ArrayList<String>){
        when {
            result(board, "X") -> {
                startActivity(Intent(this@MainActivity, WinScreen::class.java).putExtra("player", "YOU"))
            }
            result(board, "O") -> {
                startActivity(Intent(this@MainActivity, WinScreen::class.java).putExtra("player", "COMPUTER"))
            }
            isTableFull(board) -> {
                startActivity(Intent(this@MainActivity, WinScreen::class.java).putExtra("player", "Tie"))
            }
        }
    }

    private fun getComputerMove(table: ArrayList<String>): Int {
        for (i in 0 until table.count()){
            val set: ArrayList<String> = getTableSet(table)
            if(set[i] == "") set[i] = "O"
            if(result(set, "O")) return i
        }
        for (i in 0 until table.count()){
            val copy2 = getTableSet(table)
            if(copy2[i] == "") copy2[i] = "X"
            if(result(copy2, "X")) return i
        }
        val move = choseRandomMove(table, arrayListOf(0, 2, 6, 8))
        if(move != -1)
            return move
        if(table[4] == "") return 4
        return choseRandomMove(table, arrayListOf(1, 3, 5, 7))
    }


    private fun choseRandomMove(board: ArrayList<String>, list: ArrayList<Int>): Int {
        val possibleMoves = arrayListOf<Int>()
        for (i in list){
            if(board[i] == "") possibleMoves.add(i)
        }
        return if(possibleMoves.isEmpty()) -1
        else {
            val index = Random().nextInt(possibleMoves.count())
            possibleMoves[index]
        }
    }

    private fun getTableSet(table: ArrayList<String>): ArrayList<String> {
        val com = arrayListOf("", "", "", "", "", "", "", "", "")
        for (i in 0 until table.count()) {
            com[i] = table[i]
        }
        return com
    }

    private fun isTableFull(table: ArrayList<String>): Boolean {
        for (i in table)
            if(i != "X" && i != "O") return false
        return true
    }

    private fun displayComputerSelect(location: Int){
        when (location) {
            0 -> binding.button1.setImageResource(R.drawable.ic_o)
            1 -> binding.button2.setImageResource(R.drawable.ic_o)
            2 -> binding.button3.setImageResource(R.drawable.ic_o)
            3 -> binding.button4.setImageResource(R.drawable.ic_o)
            4 -> binding.button5.setImageResource(R.drawable.ic_o)
            5 -> binding.button6.setImageResource(R.drawable.ic_o)
            6 -> binding.button7.setImageResource(R.drawable.ic_o)
            7 -> binding.button8.setImageResource(R.drawable.ic_o)
            8 -> binding.button9.setImageResource(R.drawable.ic_o)
        }
    }


    private fun result(play: ArrayList<String>, string: String): Boolean =
        when {
            play[0] == string && play[1] == string && play[2] == string -> true
            play[3] == string && play[4] == string && play[5] == string -> true
            play[6] == string && play[7] == string && play[8] == string -> true
            play[0] == string && play[3] == string && play[6] == string -> true
            play[1] == string && play[4] == string && play[7] == string -> true
            play[2] == string && play[5] == string && play[8] == string -> true
            play[0] == string && play[4] == string && play[8] == string -> true
            else -> play[2] == string && play[4] == string && play[6] == string
        }


    override fun onPause() {
        super.onPause()
        finish()}

}