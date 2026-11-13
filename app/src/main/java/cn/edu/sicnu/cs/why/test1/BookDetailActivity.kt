package cn.edu.sicnu.cs.why.test1

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cn.edu.sicnu.cs.why.test1.model.Book
import com.google.android.material.appbar.MaterialToolbar

/**
 * 第二个 Activity：展示从书单中选中的书籍的完整信息。
 */
class BookDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
        )
        setContentView(R.layout.activity_book_detail)

        applyWindowInsets()

        findViewById<MaterialToolbar>(R.id.toolbar).apply {
            title = getString(R.string.book_detail_title)
            setNavigationOnClickListener { finish() }
        }

        val book = getBookFromIntent()
        if (book == null) {
            finish()
            return
        }
        title = book.title
        showBook(book)
    }

    private fun applyWindowInsets() {
        val root = findViewById<View>(R.id.detail_root)
        ViewCompat.setOnApplyWindowInsetsListener(root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }
    }

    private fun getBookFromIntent(): Book? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(EXTRA_BOOK, Book::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra(EXTRA_BOOK) as? Book
        }
    }

    private fun showBook(book: Book) {
        findViewById<TextView>(R.id.tv_detail_title).text =
            getString(R.string.book_title_format, book.title)
        findViewById<TextView>(R.id.tv_detail_author).text =
            getString(R.string.book_author_format, book.author)
        findViewById<TextView>(R.id.tv_detail_price).text =
            getString(R.string.book_price_format, book.price)
        findViewById<TextView>(R.id.tv_detail_publisher).text = book.publisher
        findViewById<TextView>(R.id.tv_detail_year).text =
            getString(R.string.detail_year_format, book.publishYear)
        findViewById<TextView>(R.id.tv_detail_isbn).text = book.isbn
        findViewById<TextView>(R.id.tv_detail_description).text = book.description
    }

    companion object {
        private const val EXTRA_BOOK = "extra_book"

        fun createIntent(context: Context, book: Book): Intent {
            return Intent(context, BookDetailActivity::class.java)
                .putExtra(EXTRA_BOOK, book)
        }
    }
}
