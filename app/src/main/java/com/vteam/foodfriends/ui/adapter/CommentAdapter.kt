package com.vteam.foodfriends.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import com.vteam.foodfriends.R
import com.vteam.foodfriends.data.model.Comment
import com.vteam.foodfriends.ui.base.BaseAdapter
import com.vteam.foodfriends.ui.base.BaseViewHolder

/**
 * Created by H2PhySicS on 12/13/2017.
 */
class CommentAdapter(context: Context) : BaseAdapter<Comment>(context) {
    init {
        contentView = R.layout.item_comment
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Comment>?, position: Int) {
        val comment = mList!![position]
        (holder as CommentViewHolder).bind(comment)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<Comment> {
        val view : View = LayoutInflater.from(context).inflate(contentView!!, parent, false)
        return CommentViewHolder(view)
    }

    inner class CommentViewHolder(itemView : View) : BaseViewHolder<Comment>(itemView) {
        val title = itemView.findViewById<TextView>(R.id.title)
        val rating = itemView.findViewById<RatingBar>(R.id.rating)
        val author = itemView.findViewById<TextView>(R.id.author)
        val time = itemView.findViewById<TextView>(R.id.time)
        val content = itemView.findViewById<TextView>(R.id.content)

        override fun bind(t: Comment) {
            title.text = t.title
            rating.rating = t.rating.toFloat()
            author.text = t.author
            time.text = t.time
            content.text = t.content
        }

    }

}