package com.example.zh.bean

import android.os.Parcel
import android.os.Parcelable

data class NavBean(
    val articles: List<NavArticle>,
    val cid: Int,
    val name: String
)

data class NavArticle(
        val apkLink: String,
        val author: String,
        val chapterId: Int,
        val chapterName: String,
        val collect: Boolean,
        val courseId: Int,
        val desc: String,
        val envelopePic: String,
        val fresh: Boolean,
        val id: Int,
        val link: String,
        val niceDate: String,
        val origin: String,
        val projectLink: String,
        val publishTime: Long,
        val superChapterId: Int,
        val superChapterName: String,
        val tags: List<Any>,
        val title: String,
        val type: Int,
        val userId: Int,
        val visible: Int,
        val zan: Int
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readString(),
            1 == source.readInt(),
            source.readInt(),
            source.readString(),
            source.readString(),
            1 == source.readInt(),
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readLong(),
            source.readInt(),
            source.readString(),
            ArrayList<Any>().apply { source.readList(this, Any::class.java.classLoader) },
            source.readString(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(apkLink)
        writeString(author)
        writeInt(chapterId)
        writeString(chapterName)
        writeInt((if (collect) 1 else 0))
        writeInt(courseId)
        writeString(desc)
        writeString(envelopePic)
        writeInt((if (fresh) 1 else 0))
        writeInt(id)
        writeString(link)
        writeString(niceDate)
        writeString(origin)
        writeString(projectLink)
        writeLong(publishTime)
        writeInt(superChapterId)
        writeString(superChapterName)
        writeList(tags)
        writeString(title)
        writeInt(type)
        writeInt(userId)
        writeInt(visible)
        writeInt(zan)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<NavArticle> = object : Parcelable.Creator<NavArticle> {
            override fun createFromParcel(source: Parcel): NavArticle = NavArticle(source)
            override fun newArray(size: Int): Array<NavArticle?> = arrayOfNulls(size)
        }
    }
}