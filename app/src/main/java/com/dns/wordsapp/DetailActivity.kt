package com.dns.wordsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dns.wordsapp.databinding.ActivityDetailBinding

//class Detail Activity
class DetailActivity : AppCompatActivity() {

    companion object {

        //deklarasi constant variable LETTER
        const val LETTER = "letter"

        //deklarasi constant variable SEARCH_PREFIX untuk google search dari setiap word
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*deklarasi constant variable letterId dengan memanggil variable
        LETTER untuk menampilkan list words dari setiap masing-masih huruf
         */
        val letterId = intent?.extras?.getString(LETTER).toString()

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = WordAdapter(letterId, this)

        recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        title = getString(R.string.detail_prefix) + " " + letterId
    }
}