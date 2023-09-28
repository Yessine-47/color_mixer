package tn.esprit.colormixer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tn.esprit.colormixer.databinding.ActivityResultBinding // Import your binding class

class ResultActivity : AppCompatActivity() {

    //TODO 18 Add lateinit var for binding
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO 19 Bind the view and implement setContentView()
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO 20 Check the RESULT from the intent and update UI elements based on the result
        val result = intent.getStringExtra(QuestionActivity.RESULT)
        val name = intent.getStringExtra(QuestionActivity.NAME)

        // Example: Update UI based on the result
        when (result) {
            QuestionActivity.SUCCESS -> {
                // Set background color, text, and image for a successful result
                binding.rlBackground.setBackgroundColor(resources.getColor(R.color.successColor))
                binding.btnQuit.setBackgroundColor(resources.getColor(R.color.successColor))
                binding.imgResult.setImageResource(R.drawable.ic_success)
                binding.txtResult.text = "Congratulations, $name!"
                binding.txtName.text = name
                binding.txtAnswer.text = "You got it right!"
            }
            QuestionActivity.FAILED -> {
                // Set background color, text, and image for a failed result
                binding.rlBackground.setBackgroundColor(resources.getColor(R.color.failedColor))
                binding.btnQuit.setBackgroundColor(resources.getColor(R.color.failedColor))
                binding.imgResult.setImageResource(R.drawable.ic_failed)
                binding.txtResult.text = "Sorry, $name!"
                binding.txtName.text = name
                binding.txtAnswer.text = "You missed it!"
            }
            else -> {
                // Handle other result cases or default behavior
            }
        }

        //TODO 21 Implement setOnClickListener for btnQuit to finish the activity
        binding.btnQuit.setOnClickListener {
            finish()
        }
    }
}
