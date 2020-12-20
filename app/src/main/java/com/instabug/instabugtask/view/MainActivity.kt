package com.instabug.instabugtask.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.instabug.instabugtask.data_layer.local.DatabaseHandler
import com.instabug.instabugtask.databinding.ActivityMainBinding
import com.instabug.instabugtask.view_model.WordsViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var wordVM: WordsViewModel
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialization()
        listeners()
        getWords()

    }

    private fun initialization() {
        wordVM = ViewModelProvider(this).get(WordsViewModel::class.java)
    }



    private fun listeners() {
        binding.includeLayoutError.buttonTryAgain.setOnClickListener {
            binding.includeLayoutError.root.visibility = View.GONE
            binding.layoutProgressBar.visibility = View.VISIBLE

            handler.postDelayed({
                getWords()
            }, 500)
        }
    }


    private fun getWords() {
        wordVM.getWords()
        wordVM.response.observe(this, Observer { wordsResponse ->
            binding.layoutProgressBar.visibility = View.GONE
            if (wordsResponse.status == 200) {
                binding.rvWords.adapter = WordsAdapter(wordsResponse.data!!.words)
            }
            else{
                binding.includeLayoutError.tvError.text = wordsResponse.message
                binding.includeLayoutError.root.visibility = View.VISIBLE
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }


}

