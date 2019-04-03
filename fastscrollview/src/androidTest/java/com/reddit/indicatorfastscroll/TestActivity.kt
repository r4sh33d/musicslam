package com.reddit.indicatorfastscroll

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.reddit.indicatorfastscroll.test.R

class TestActivity : AppCompatActivity() {

  lateinit var recyclerView: RecyclerView
  lateinit var fastScrollerView: FastScrollerView

  lateinit var testAdapter: TestAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.test)

    recyclerView = findViewById(R.id.test_recyclerview)
    fastScrollerView = findViewById(R.id.test_fastscroller)

    testAdapter = TestAdapter()

    recyclerView.apply {
      layoutManager = LinearLayoutManager(context)
      adapter = testAdapter
    }
    fastScrollerView.setupWithRecyclerView(
        recyclerView,
        { position ->
          val item = testAdapter.data[position]
          if (item.showInFastScroll) {
            item.iconRes?.let(FastScrollItemIndicator::Icon)
                ?: FastScrollItemIndicator.Text(item.text)
          } else {
            null
          }
        }
    )
  }

  fun presentData(data: List<ListItem>) {
    testAdapter.data = data
    testAdapter.notifyDataSetChanged()
  }

  inner class TestAdapter : RecyclerView.Adapter<TestAdapter.ViewHolder>() {

    var data: List<ListItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      return ViewHolder(this@TestActivity)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val item = data[position]
      holder.textView.text = item.text
    }

    override fun getItemCount(): Int = data.count()

    inner class ViewHolder(context: Context) : RecyclerView.ViewHolder(
        AppCompatTextView(context).apply {
          layoutParams = ViewGroup.LayoutParams(
              ViewGroup.LayoutParams.MATCH_PARENT,
              getDimenPixelSize(R.dimen.test_item_height)
          )
        }
    ) {
      val textView: TextView = itemView as TextView
    }

  }

  class ListItem(
      val text: String,
      val iconRes: Int? = null,
      val showInFastScroll: Boolean = true
  )

}
