package com.dns.wordsapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

//class WordAdapter untuk RecyclerView pada DetailActivity
class WordAdapter(private val letterId: String, context: Context) :
    RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    private val filteredWords: List<String>

    init {
        //mengambil data word dari res/values/arrays.xml
        val words = context.resources.getStringArray(R.array.words).toList()

        filteredWords = words
            //
            .filter { it.startsWith(letterId, ignoreCase = true) }
            //
            .shuffled()
            //mengembalikan 5 nilai n pertama sebagai [List]
            .take(5)
            //menyortir urutan words dari [List]
            .sorted()
    }

    class WordViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val button = view.findViewById<Button>(R.id.button_item)
    }

    override fun getItemCount(): Int = filteredWords.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false)

        layout.accessibilityDelegate = Accessibility

        return WordViewHolder(layout)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val item = filteredWords[position]
        val context = holder.view.context
        holder.button.text = item

        holder.button.setOnClickListener {

            /* deklarasi variable queryUrl untuk search query dengan memanggil variabel
            SEARCH_PREFIX sebagai fungsi google search untuk setiap word
             */
            val queryUrl: Uri = Uri.parse("${DetailActivity.SEARCH_PREFIX}${item}")

            val intent = Intent(Intent.ACTION_VIEW, queryUrl)

            context.startActivity(intent)
        }
    }

    companion object Accessibility : View.AccessibilityDelegate() {
        override fun onInitializeAccessibilityNodeInfo(host: View, info: AccessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(host, info)

            val customString = host.context?.getString(R.string.look_up_word)
            val customClick = AccessibilityNodeInfo.AccessibilityAction(
                AccessibilityNodeInfo.ACTION_CLICK, customString
            )
        info.addAction(customClick)
        }
    }
}