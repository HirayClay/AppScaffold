package com.hiray.mvvm.model.entity

import android.arch.persistence.room.*
import com.google.gson.annotations.SerializedName

/*    title: "有哪些体育解说员「打脸」事件？",
    url: "http://news-at.zhihu.com/api/2/news/9689524",
    image: "https://pic2.zhimg.com/v2-c8411589388d6da0a5fa410a0b72a4d1.jpg",
    share_url: "http://daily.zhihu.com/story/9689524",
    thumbnail: "https://pic4.zhimg.com/v2-03ac9cfbed193a2724cf66013f5e6e37.jpg",
    ga_prefix: "071317",
    id: 9689524*/
@Entity(tableName = "News")
data class News(var title: String,
                var url: String,
                var image: String?,
                @SerializedName("share_url") var shareUrl: String,
                var thumbnail: String,
                var id: Int) {

    override fun toString(): String {
        return super.toString()
    }
}

@Dao
interface NewsDao {

    @Query("SELECT * FROM NEWS")
    fun getNews(): List<News>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveNews(news: List<News>)
}