package tn.esprit.colormixer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tn.esprit.colormixer.databinding.ActivityAnswerBinding
import tn.esprit.colormixer.QuestionActivity


class AnswerActivity : AppCompatActivity() {

    //TODO 12 Add lateint var for binding
     lateinit var binding: ActivityAnswerBinding

    private var correctColor = "NONE"
    private var name = "NONE"
    private var color1 = "NONE"
    private var color2 = "NONE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO 13 Bind the view and implement setContentView()
        binding = ActivityAnswerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO 14 Change the value of correctColor / name / color1 / color2 with the DATA from the INTENT

        val intent = intent
        name = intent.getStringExtra(QuestionActivity.NAME) ?: "NONE"
        correctColor = intent.getStringExtra(QuestionActivity.MIXED_COLOR) ?: "NONE"
        color1 = intent.getStringExtra(QuestionActivity.COLOR1) ?: "NONE"
        color2 = intent.getStringExtra(QuestionActivity.COLOR2) ?: "NONE"

        //TODO 15 Change the txtChoosed with : "You chose $color1 and $color2"
        val chosenText = "You chose $color1 and $color2"
        binding.txtChoosed.text = chosenText

        //TODO 16 Implement setOnClickListener on the btnSubmit and call checkAnswer()
        // You must check if only one radio button is selected the navigate to the ResultActivity with the data name and RESULT (FAILED/SUCCESS)
        binding.btnSubmit.setOnClickListener {
            checkAnswer()
        }
    }

    private fun checkAnswer(): Boolean {
        //TODO 17 Check if the answer of the chosen color is correct
        val selectedAnswer = binding.radioGroup.checkedRadioButtonId
        val isCorrect = when (selectedAnswer) {
            R.id.radioPurple -> correctColor == "purple"
            R.id.radioGreen -> correctColor == "green"
            R.id.radioOrange -> correctColor == "orange"
            else -> false
        }

        // Navigate to the ResultActivity with the data name and RESULT (FAILED/SUCCESS)
        val result = if (isCorrect) QuestionActivity.SUCCESS else QuestionActivity.FAILED
        val resultIntent = Intent(this, ResultActivity::class.java)
        resultIntent.putExtra(QuestionActivity.NAME, name)
        resultIntent.putExtra(QuestionActivity.RESULT, result)
        startActivity(resultIntent)

        return isCorrect
    }
}