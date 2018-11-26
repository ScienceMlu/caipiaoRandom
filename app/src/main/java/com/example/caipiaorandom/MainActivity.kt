package com.example.caipiaorandom

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.NumberFormatException
import java.sql.Time
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val startButton = findViewById<Button>(R.id.button_start)
        val clearButton = findViewById<Button>(R.id.button_clear)
        val TimeCount = findViewById<EditText>(R.id.editText_time).text
        val TextViewNow = findViewById<TextView>(R.id.textView_now)
        val listview = findViewById<RecyclerView>(R.id.listview)
        listview.layoutManager = LinearLayoutManager(this)


//        Element
        startButton.setOnClickListener {
            listview.adapter = MainAdapter(RollMainFun(TimeCount))}
        clearButton.setOnClickListener {
            ClearAll(listview) }

    }

    fun ClearAll(DefaultView2:RecyclerView) {
//        DefaultView1.append(DefaultView2.text)
//        DefaultView2.on = null

    }


    fun RollMainFun(TimeUse: Editable?): List<String> {
        var FirstNum: Int
        var EndNum: Int
        val ResultString = emptyList<String>().toMutableList()
        var CircleBaseNumber = 0
        try {
            val countUse = TimeUse.toString().toInt()
            while (CircleBaseNumber < countUse) {
                val FirstSet = emptySet<Int>().toMutableSet()
                val EndSet = emptySet<Int>().toMutableSet()
                do {
                    do {
                        FirstNum = Random().nextInt(36)
                    } while (FirstNum == 0)
                    FirstSet.add(FirstNum)
                } while (FirstSet.size < 5)
                do {
                    do {
                        EndNum = Random().nextInt(13)
                    } while (EndNum == 0)
                    EndSet.add(EndNum)
                } while (EndSet.size < 2)
                CircleBaseNumber += 1
                val StringDeal = ((FirstSet.toList()+EndSet.toList()).toString() + "\n").filterNot { it.toString() == "[" || it.toString() == "]"}
                ResultString.add(StringDeal)
            }
            return ResultString
        } catch (e: NumberFormatException) {
            return listOf(e.toString())
        }
    }
}


class MainAdapter(val items : List<String>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TextView(parent.context))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = items[position]
    }

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
}

