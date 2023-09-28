package tn.esprit.colormixer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import tn.esprit.colormixer.databinding.ActivityQuestionBinding


//TODO 2 Add string constant val here for RED / BLUE / YELLOW / PURPLE / GREEN / ORANGE
const val RED = "Red"
const val BLUE = "Blue"
const val YELLOW = "Yellow"
const val PURPLE = "Purple"
const val GREEN = "Green"
const val ORANGE = "Orange"

//TODO 3 Add string constant val here for NAME / MIXED_COLOR / COLOR1 / COLOR2 / RESULT / SUCCESS / FAILED

const val NAME = "Name"
const val MIXED_COLOR = "MixedColor"
const val COLOR1 = "Color1"
const val COLOR2 = "Color2"
const val RESULT = "Result"
const val SUCCESS = "Success"
const val FAILED = "Failed"

class QuestionActivity : AppCompatActivity() {

    //TODO 4 Add lateint var for binding
    lateinit var binding: ActivityQuestionBinding

    //TODO 5 Add var for colorMixed / color1 / color2 / name
    private var colorMixed: String = ""
    private var color1: String = ""
    private var color2: String = ""
    private var name: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO 6 Bind the view and implement setContentView()
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //TODO 7 Implement setOnClickListener on the button Mix and call mixColor()
        binding.btnMix.setOnClickListener {
            mixColor()
        }
    }

    private fun mixColor(){

        //TODO 8 Check if the input for FullName. IF it's empty show a snackbar with the message : You must enter your name !
        name = binding.tfFullName.text.toString()
        if (name.isEmpty()) {
            showSnackbar("You must enter your name!")
            return
        }

        //TODO 9 Check if Only 2 colors are selected then change the value of  colorMixed / color1 / color2
        if (binding.cbRed.isChecked && binding.cbBlue.isChecked) {
            color1 = RED
            color2 = BLUE
            colorMixed = mixColors(color1, color2)
        } else if (binding.cbRed.isChecked && binding.cbYellow.isChecked) {
            color1 = RED
            color2 = YELLOW
            colorMixed = mixColors(color1, color2)
        } else if (binding.cbBlue.isChecked && binding.cbYellow.isChecked) {
            color1 = BLUE
            color2 = YELLOW
            colorMixed = mixColors(color1, color2)
        } else {
            showSnackbar("Select exactly 2 colors for mixing.")
            return
        }

        //TODO 10 Change the value of name with the input
        name = binding.tfFullName.text.toString()


        //TODO 11 Create an Intent to AnswerActivity and add all of the values name / colorMixed / color1 / color2 Then start the Activity
        val intent = Intent(this, AnswerActivity::class.java)
        intent.putExtra(NAME, name)
        intent.putExtra(MIXED_COLOR, colorMixed)
        intent.putExtra(COLOR1, color1)
        intent.putExtra(COLOR2, color2)
        startActivity(intent)

    }
    private fun mixColors(color1: String, color2: String): String {
        if (color1 == "BLUE" && color2 == "YELLOW")  {
            return "GREEN"
        } else if (color1 == "RED" && color2 == "BLUE") {
            return "purple"
        } else if (color1 == "BLUE" && color2 == "YELLOW") {
            return "orange"
        }
        return "MixedColor"
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}