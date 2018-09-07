package com.example.andreip.myapplication.app

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.os.Handler
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.EditText

import SQLData.RecipeDb
import SQLData.WordRepository


class MainActivity : BaseActivity() {

    var recyclerView: RecyclerView? = null
        private set
    private var editText: EditText? = null
    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private var retroFitAdapter: RetroFitAdapter? = null
    private var wordRepository: WordRepository? = null
    private var mLiveData: LiveData<List<RecipeDb>>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.ETTitleRecipe)
        swipeRefreshLayout = findViewById(R.id.SRLRefresh)

        recyclerView = findViewById(R.id.RVRecyclerView)
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        recyclerView!!.adapter = MyAdapter()
        recyclerView!!.setOnTouchListener { _, _ ->
            hideSoftKeyboard()
            false
        }

        retroFitAdapter = RetroFitAdapter(this)

        swipeRefreshLayout!!.setOnRefreshListener { refreshData(editText!!.text.toString()) }

        wordRepository = WordRepository(application)

        mLiveData = wordRepository!!.allWords
        mLiveData!!.observeForever {
            if (mLiveData!!.value == null) {
                retroFitAdapter!!.getRecipes("", wordRepository!!)
            } else {
                (recyclerView!!.adapter as MyAdapter).setDbArrayList(mLiveData!!.value!!)
                Log.d("DEBUG_DB", mLiveData!!.value!!.size.toString() + " ")
            }
        }

        retroFitAdapter!!.getRecipes("", wordRepository!!)



        editText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.length > 2)
                    retroFitAdapter!!.getRecipes(s.toString(), wordRepository!!)
                else
                    retroFitAdapter!!.getRecipes("", wordRepository!!)
            }

            override fun afterTextChanged(s: Editable) {

            }
        })

    }

    fun refreshData(searchText: String) {
        Handler().post {
            if (searchText.length > 2)
                retroFitAdapter!!.getRecipes(searchText, wordRepository!!)
            else
                retroFitAdapter!!.getRecipes("", wordRepository!!)

            if (swipeRefreshLayout!!.isRefreshing)
                swipeRefreshLayout!!.isRefreshing = false
        }

    }

}


