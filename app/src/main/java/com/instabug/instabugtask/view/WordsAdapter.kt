package com.instabug.instabugtask.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.instabug.instabugtask.data_layer.dto.Word
import com.instabug.instabugtask.databinding.WordItemBinding


class WordsAdapter(private var words: List<Word>) :
    RecyclerView.Adapter<WordsAdapter.WordsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsViewHolder {
        var binding = WordItemBinding.inflate(LayoutInflater.from(parent.context))
        return WordsViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return words.size
    }

    override fun onBindViewHolder(holder: WordsViewHolder, position: Int) {
        holder.bind(words[position])
    }

    class WordsViewHolder(private var binding: WordItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(word: Word) {
            binding.apply {
                tvWord.text = word.word
                tvWordCont.text = word.wordCount.toString()
            }
        }

    }

}