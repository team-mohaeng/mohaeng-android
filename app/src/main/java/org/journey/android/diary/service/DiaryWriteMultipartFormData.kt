package org.journey.android.diary.service

import android.content.ContentResolver
import android.net.Uri
import android.provider.OpenableColumns
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okio.BufferedSink
import okio.source

object DiaryWriteMultipartFormData {
    fun getBody(key:String, value:Any):MultipartBody.Part{
        return MultipartBody.Part.createFormData(key, value.toString())
    }

    fun Uri.asMultipart(name: String, contentResolver: ContentResolver): MultipartBody.Part? {
        return contentResolver.query(this, null, null, null, null)?.let {
            if (it.moveToNext()) {
                val displayName = it.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                val requestBody = object : RequestBody() {
                    override fun contentType(): MediaType? {
                        return contentResolver.getType(this@asMultipart)?.toMediaType()
                    }
                    override fun writeTo(sink: BufferedSink) {
                        sink.writeAll(contentResolver.openInputStream(this@asMultipart)?.source()!!)
                    }
                }
                it.close()
                MultipartBody.Part.createFormData(name, displayName, requestBody)
            } else {
                it.close()
                null
            }
        }
    }
}