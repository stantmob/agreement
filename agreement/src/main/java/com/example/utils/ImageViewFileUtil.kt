package com.example.utils

import android.content.Context
import android.os.Environment
import android.util.Log
import java.io.File


object ImageViewFileUtil {

    private const val LOG_TAG = "NOT_CREATED"
    const val JPG_FILE_SUFFIX = ".jpg"
    const val JPG_FILE_PREFIX = "IMG-"

    fun getPrivateTempDirectory(context: Context): File {
        return context.filesDir
    }

    fun getAppSpecificAlbumStorageDir(context: Context, albumName: String): File? {
        val file = File(
            context.getExternalFilesDir(
                Environment.DIRECTORY_PICTURES
            ), albumName
        )
        if (!file.mkdirs()) {
            Log.e(LOG_TAG, "Directory not created")
        }
        return file
    }


}