package com.souvik.cc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    private var resultTV : TextView? = null

    var lastDot : Boolean = false
    var lastNumber : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTV = findViewById(R.id.resultTV)
    }
    fun clickDigit(view: View){                                 //click on numbers
        resultTV?.append((view as Button).text)
        lastNumber = true
        lastDot = false
    }

    fun allClear(view: View){                                   //click on AC
        resultTV?.text= ""
    }

    fun onDecimalPoint(view: View){                             //click on Decimal
        if (!lastDot && lastNumber){
            resultTV?.append(".")
            lastNumber = false
            lastDot = true
        }
    }

    fun onOperators(view: View){
        resultTV?.text?.let {
            if (lastNumber && !isOperatorAdded(it.toString())){
                resultTV?.append((view as Button).text)
            }
        }
    }

    fun onEqual(view: View){
        if (lastNumber){
            var tvValue = resultTV?.text.toString()
            var prefix = ""
            try {
                if (tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                if (tvValue.contains("-")){
                    val tvValueSplit = tvValue.split("-")
                    var one = tvValueSplit[0]
                    var two = tvValueSplit[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    // var result=
                    resultTV?.text = removeDot((one.toDouble() - two.toDouble()).toString())

                }else if (tvValue.contains("x")){
                    val tvValueSplit = tvValue.split("x")
                    var one = tvValueSplit[0]
                    var two = tvValueSplit[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    // var result=
                    resultTV?.text = removeDot((one.toDouble() * two.toDouble()).toString())

                }else if (tvValue.contains("/")){
                    val tvValueSplit = tvValue.split("/")
                    var one = tvValueSplit[0]
                    var two = tvValueSplit[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    // var result=
                    resultTV?.text = removeDot((one.toDouble() / two.toDouble()).toString())

                }else if (tvValue.contains("+")){
                    val tvValueSplit = tvValue.split("+")
                    var one = tvValueSplit[0]
                    var two = tvValueSplit[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    // var result=
                    resultTV?.text = removeDot((one.toDouble() + two.toDouble()).toString())
                }

            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeDot(result: String): String{
        var value = result
        if (result.contains(".0")) {
            value = result.substring(0, result.length - 2)
        }
            return value

    }

    private fun isOperatorAdded(value : String):Boolean{        //Is add the operators are added or not
        return if(value.startsWith("-")){
            false
        }else {
            value.contains("/")
                    || value.contains("x")
                    || value.contains("+")
                    || value.contains("-")
        }

    }
}