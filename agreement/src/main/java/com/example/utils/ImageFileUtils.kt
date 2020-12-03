package com.example.utils

import android.content.Context
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import com.example.listener.OnSavedPhotoListener
import rx.Single
import rx.schedulers.Schedulers

import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

fun Bitmap.saveBitmapInPictures(context: Context, onSavedPhotoListener: OnSavedPhotoListener) {
    val calendar = Calendar.getInstance()
    val todayDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)

    val directory = ImageViewFileUtil.getPublicAlbumDirectoryAtPictures( "Stant")

    val imageFile = File.createTempFile(
        todayDate + "-" + ImageViewFileUtil.JPG_FILE_PREFIX
                + calendar.timeInMillis, ImageViewFileUtil.JPG_FILE_SUFFIX, directory
    )

    val fileOutputStream = FileOutputStream(imageFile)

    val compressThread = Single.fromCallable {
        this.compress(
            Bitmap.CompressFormat.PNG,
            100,
            fileOutputStream
        )
    }
        .subscribeOn(Schedulers.newThread()).subscribe { compressed ->
            if (compressed) {
                MediaScannerConnection.scanFile(
                    context,
                    arrayOf(imageFile.toString()),
                    null
                ) { path, _ ->
                    onSavedPhotoListener.onSaved(path)
                }
            }
        }


}