package cn.edu.sicnu.cs.why.test1.model

import java.io.Serializable

/**
 * 书籍实体类。
 *
 * 实现 Serializable，方便通过 Intent 在两页 Activity 之间传递完整对象。
 */
data class Book(
    val title: String,
    val author: String,
    val publisher: String,
    val price: Double,
    val publishYear: Int,
    val isbn: String,
    val description: String
) : Serializable
