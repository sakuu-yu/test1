package cn.edu.sicnu.cs.why.test1

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.TextView
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cn.edu.sicnu.cs.why.test1.model.Book
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
        )
        setContentView(R.layout.activity_main)

        applyWindowInsets()

        val studentTitle = getString(R.string.student_title)
        title = studentTitle
        findViewById<MaterialToolbar>(R.id.toolbar).title = studentTitle

        // 1. 在代码中创建多个书籍对象，放入数组。
        val books = createBooks()

        // 2. 对数组中的书籍按价格从低到高排序。
        books.sortWith(compareBy { it.price })

        // 3. 使用 ListView 展示排序后的书籍。
        findViewById<ListView>(R.id.lv_books).apply {
            adapter = BookAdapter(this@MainActivity, books.toList())
            setOnItemClickListener { _, _, position, _ ->
                // 4. 单击书籍，启动第二个 Activity 并传递完整 Book 对象。
                startActivity(BookDetailActivity.createIntent(this@MainActivity, books[position]))
            }
        }

        findViewById<TextView>(R.id.tv_summary).text =
            getString(R.string.book_count_summary, books.size)
    }

    private fun applyWindowInsets() {
        val root = findViewById<View>(R.id.main_root)
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

    private fun createBooks(): Array<Book> = arrayOf(
        Book(
            title = "红楼梦",
            author = "曹雪芹",
            publisher = "人民文学出版社",
            price = 59.70,
            publishYear = 1996,
            isbn = "9787020002207",
            description = "中国古典小说的巅峰之作，以贾、史、王、薛四大家族的兴衰为背景，" +
                "描绘了封建社会广阔的生活画卷，被誉为“中国封建社会的百科全书”。"
        ),
        Book(
            title = "活着",
            author = "余华",
            publisher = "作家出版社",
            price = 45.00,
            publishYear = 2012,
            isbn = "9787506365437",
            description = "讲述了农村人福贵悲惨的人生遭遇。福贵一生经历了家庭变故与时代动荡，" +
                "在苦难中依然顽强地活着，展现了生命的韧性与力量。"
        ),
        Book(
            title = "三体",
            author = "刘慈欣",
            publisher = "重庆出版社",
            price = 23.00,
            publishYear = 2008,
            isbn = "9787536692930",
            description = "地球文明向宇宙发出的一声啼鸣，以光速向宇宙深处飞驰。" +
                "四光年外，三体文明正苦苦挣扎，并决定向地球进发，人类的末日悄然来临。"
        ),
        Book(
            title = "平凡的世界",
            author = "路遥",
            publisher = "北京十月文艺出版社",
            price = 68.00,
            publishYear = 2017,
            isbn = "9787530216781",
            description = "全景式地表现中国当代城乡社会生活的长篇小说。以孙少安、孙少平兄弟为中心，" +
                "刻画了普通人在大时代历史进程中所走过的艰难曲折的道路。"
        ),
        Book(
            title = "围城",
            author = "钱钟书",
            publisher = "人民文学出版社",
            price = 39.00,
            publishYear = 1991,
            isbn = "9787020090006",
            description = "一部风格独特的讽刺小说，被誉为“新儒林外史”。" +
                "作品以方鸿渐的生活道路为主线，展现了抗战初期知识分子的群像与人生困境。"
        ),
        Book(
            title = "百年孤独",
            author = "加西亚·马尔克斯",
            publisher = "南海出版公司",
            price = 55.00,
            publishYear = 2011,
            isbn = "9787544253994",
            description = "魔幻现实主义文学的代表作，描写了布恩迪亚家族七代人的传奇故事，" +
                "以及加勒比海沿岸小镇马孔多的百年兴衰，反映了拉丁美洲一个世纪以来的风云变幻。"
        )
    )
}
