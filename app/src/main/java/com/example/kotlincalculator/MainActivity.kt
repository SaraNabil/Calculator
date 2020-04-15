package com.example.kotlincalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        zeroBtn.setOnClickListener { appendExpression("0") }
        oneBtn.setOnClickListener { appendExpression("1") }
        twoBtn.setOnClickListener { appendExpression("2") }
        threeBtn.setOnClickListener { appendExpression("3") }
        fourBtn.setOnClickListener { appendExpression("4") }
        fiveBtn.setOnClickListener { appendExpression("5") }
        sixBtn.setOnClickListener { appendExpression("6") }
        sevenBtn.setOnClickListener { appendExpression("7") }
        eightBtn.setOnClickListener { appendExpression("8") }
        nineBtn.setOnClickListener { appendExpression("9") }
        dotBtn.setOnClickListener { appendExpression(".") }

        divBtn.setOnClickListener { appendExpression("/") }
        mulBtn.setOnClickListener { appendExpression("*") }
        addBtn.setOnClickListener { appendExpression("+") }
        subBtn.setOnClickListener { appendExpression("-") }
        openBracBtn.setOnClickListener { appendExpression("(") }
        closeBracBtn.setOnClickListener { appendExpression(")") }

        clearBtn.setOnClickListener {
            expressionTv.text = ""
        }
        equalBtn.setOnClickListener {
            try {
                val expression = ExpressionBuilder(expressionTv.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble())
                    resultTv.text = longResult.toString()
                else
                    resultTv.text = result.toString()

            } catch (e: Exception) {
                Log.d("ExceptionTag", " message : " + e.message)
                Toast.makeText(this, "not valid input", Toast.LENGTH_LONG).show()
            }
        }
        deleteBtn.setOnClickListener {
            val string = expressionTv.text.toString()
            if (string.isNotEmpty()) {
                expressionTv.text = string.substring(0, string.length - 1)
            }
            resultTv.text = ""
        }

    }

    private fun appendExpression(expr: String) {
        if (resultTv.text.isNotEmpty()) {
            expressionTv.text = ""
        }

        if (expr == "/" || expr == "*" || expr == "+" || expr == "-" || expr == "." || expr == ")") {
            if (expressionTv.text.isEmpty()) {
                Toast.makeText(this, "not valid input", Toast.LENGTH_LONG).show()
            } else {
                val operation: String = expressionTv.text.toString()
                val lastOper: Char = operation[operation.length - 1]
                Log.d("MyApp", lastOper.toString())
                if (lastOper == '/' || lastOper == '*' || lastOper == '+' || lastOper == '-' || lastOper == '.')
                    Toast.makeText(this, "not valid input", Toast.LENGTH_LONG).show()
                else {
                    resultTv.text = ""
                    expressionTv.append(expr)
                }

            }
        } else {
            resultTv.text = ""
            expressionTv.append(expr)
        }


    }
}
