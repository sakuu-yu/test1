package cn.edu.sicnu.cs.why.test1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import cn.edu.sicnu.cs.why.test1.model.Book
import java.util.Locale

/**
 * 书籍列表适配器，为 ListView 提供每一行书籍的展示数据。
 */
class BookAdapter(
    private val context: Context,
    private val books: List<Book>
) : BaseAdapter() {

    override fun getCount(): Int = books.size

    override fun getItem(position: Int): Book = books[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_book, parent, false)
        val holder = view.tag as? ViewHolder ?: ViewHolder(view).also { view.tag = it }
        val book = getItem(position)

        holder.index.text = String.format(Locale.CHINA, "%02d", position + 1)
        holder.title.text = book.title
        holder.author.text = context.getString(R.string.book_author_format, book.author)
        holder.publisher.text = context.getString(R.string.book_publisher_format, book.publisher)
        holder.price.text = context.getString(R.string.book_price_format, book.price)
        return view
    }

    private class ViewHolder(view: View) {
        val index: TextView = view.findViewById(R.id.tv_index)
        val title: TextView = view.findViewById(R.id.tv_title)
        val author: TextView = view.findViewById(R.id.tv_author)
        val publisher: TextView = view.findViewById(R.id.tv_publisher)
        val price: TextView = view.findViewById(R.id.tv_price)
    }
}
