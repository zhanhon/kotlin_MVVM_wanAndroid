package com.example.zh.bean

class ArticleBean {
        var size: Int = 0
        var total: Int = 0
        var offset: Int = 0
        var isOver: Boolean = false
        var curPage: Int = 0
        var pageCount: Int = 0
        var datas: List<DatasBean>? = null

        class DatasBean {
            var userId: Int = 0
            var link: String? = null
            var chapterName: String? = null
            var desc: String? = null
            var visible: Int = 0
            var origin: String? = null
            var zan: Int = 0
            var apkLink: String? = null
            var isCollect: Boolean = false
            var projectLink: String? = null
            var chapterId: Int = 0
            var niceDate: String? = null
            var superChapterName: String? = null
            var envelopePic: String? = null
            var title: String? = null
            var id: Int = 0
            var originId: Int = -1
            var publishTime: Long = 0
            var isFresh: Boolean = false
            var courseId: Int = 0
            var type: Int = 0
            var superChapterId: Int = 0
            var author: String? = null
            var tags: List<*>? = null
            override fun toString(): String {
                return "DatasBean(userId=$userId, link=$link, chapterName=$chapterName, desc=$desc, visible=$visible, origin=$origin, zan=$zan, apkLink=$apkLink, isCollect=$isCollect, projectLink=$projectLink, chapterId=$chapterId, niceDate=$niceDate, superChapterName=$superChapterName, envelopePic=$envelopePic, title=$title, id=$id, publishTime=$publishTime, isFresh=$isFresh, courseId=$courseId, type=$type, superChapterId=$superChapterId, author=$author, tags=$tags)"
            }

        }

        override fun toString(): String {
            return "DataBean(size=$size, total=$total, offset=$offset, isOver=$isOver, curPage=$curPage, pageCount=$pageCount, datas=$datas)"
        }

}
