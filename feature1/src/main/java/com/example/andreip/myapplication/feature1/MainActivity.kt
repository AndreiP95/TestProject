package com.example.andreip.myapplication.feature1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var preffered: String = "aaa";

    fun firstFunction(string: String) : Boolean {
        if (string.equals("aaa"))
            return true;
        return false;
    }

    fun secondFunction(string: String): Boolean {
        val b: Boolean? = null
        if (b ?: false) {
            println(b)
        }
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var a = 7
        var b = 8
        var calculator: Calculator = Calculator();

        Log.d("Testing", calculator.multiply(a, b).toString())

    }
}
