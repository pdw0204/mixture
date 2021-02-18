package com.pdw.assets

import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media.DATA

/**
 * 类描述
 *
 * @author Corgi
 * @date 2021/2/18
 */
object MediaUtils {

    @JvmStatic
    fun parseContentData(ctx: Context, data: Intent): String {
        var imagePath: String? = null
        val uri = data.data
        if (DocumentsContract.isDocumentUri(ctx, uri)) {
            //如果是document类型的uri，则通过document id处理
            val docId = DocumentsContract.getDocumentId(uri)
            if ("com.android.providers.media.documents" == uri!!.authority) {
                val id =
                    docId.split(":".toRegex()).toTypedArray()[1] //解析出数字格式的id
                val selection = MediaStore.Images.Media._ID + "=" + id
                imagePath =
                    getImagePath(ctx, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection)
            } else if ("com.android,providers.downloads.documents" == uri.authority) {
                val contentUri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/public_downloads"),
                    docId.toLong()
                )
                imagePath = getImagePath(ctx, contentUri, null)
            }
        } else if ("content".equals(uri!!.scheme, ignoreCase = true)) {
            imagePath = getImagePath(ctx, uri, null)
        }
        return imagePath!!
    }

    /**
     * 获得图片路径
     */
    private fun getImagePath(ctx: Context, uri: Uri, selection: String?): String? {
        var path: String? = null
        val cursor: Cursor? = ctx.contentResolver.query(uri, null, selection, null, null) //内容提供器
        cursor?.let {
            if (it.moveToFirst()) {
                path = it.getString(it.getColumnIndex(DATA)) //获取路径
            }
        }
        cursor?.close()
        return path
    }
}