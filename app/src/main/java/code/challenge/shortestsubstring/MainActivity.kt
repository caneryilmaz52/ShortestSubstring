package code.challenge.shortestsubstring

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.pow


class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        find_btn.setOnClickListener(this)
    }

    private val characterLimit = 256
    private fun charCount(string: String, length: Int): Int {
        val count = IntArray(characterLimit)

        for (i in 0 until length) {
            count[string[i].toInt()]++
        }

        var charCount = 0
        for (i in 0 until characterLimit) {
            if (count[i] != 0) {
                charCount++
            }
        }
        return charCount
    }

    private fun shortestSubstring(parameterString: String): Int {
        val stringLength: Int = parameterString.length
        val stringCharCount: Int = charCount(parameterString, stringLength)
        var smallestLength = stringLength

        for (i in 0..stringLength) {
            for (j in 0..stringLength) {
                val substring: String = if (i < j) parameterString.substring(i, j) else parameterString.substring(j, i)

                val substringLength = substring.length
                val substringCharCount: Int = charCount(substring, substringLength)

                if (substringLength < smallestLength && stringCharCount == substringCharCount) {
                    smallestLength = substringLength
                }
            }
        }
        return smallestLength
    }

    private fun showAlertDialog(title:String, message: String){
        val alert = AlertDialog.Builder(this)
        alert.setTitle(title)
        alert.setMessage(message)
        alert.show()
    }

    override fun onClick(v: View?) {
        var hasError = false
        var errorMessage=""
        if(parameter_et.text != null && parameter_et.text.toString() !=""){
            val parameterString:String = parameter_et.text.toString()

            parameterString.forEach { char ->
                if(char.isDigit()){
                    hasError = true
                    errorMessage = "String has digit."
                }
            }

            if(parameterString.contains(" ")){
                hasError = true
                errorMessage = "String has spaces."
            }

            if(parameterString.length > 10.0.pow(5.0)){
                hasError = true
                errorMessage = "String length more than 10⁵"
            }

            if (hasError){
                showAlertDialog("Error", errorMessage)
            }else{
                val shortestLength = shortestSubstring(parameterString)
                showAlertDialog("Success","Shortest substring length is $shortestLength")
            }
        }else{
            showAlertDialog("Warning","Enter parameter string")
        }
    }
}
