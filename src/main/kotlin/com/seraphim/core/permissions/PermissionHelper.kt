package com.seraphim.core.permissions

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

@SuppressLint("ObsoleteSdkInt")
object PermissionHelper {
    fun getImagePermissions(): Array<String> {
        return when {
            Build.VERSION.SDK_INT >= 33 -> arrayOf(Manifest.permission.READ_MEDIA_IMAGES)
            Build.VERSION.SDK_INT >= 29 -> arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            Build.VERSION.SDK_INT >= 23 -> arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )

            else -> emptyArray()
        }
    }

    fun getVideoPermissions(): Array<String> {
        return when {
            Build.VERSION.SDK_INT >= 33 -> arrayOf(Manifest.permission.READ_MEDIA_VIDEO)
            Build.VERSION.SDK_INT >= 29 -> arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            Build.VERSION.SDK_INT >= 23 -> arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )

            else -> emptyArray()
        }
    }

    fun getAudioPermissions(): Array<String> {
        return when {
            Build.VERSION.SDK_INT >= 33 -> arrayOf(Manifest.permission.READ_MEDIA_AUDIO)
            Build.VERSION.SDK_INT >= 29 -> arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            Build.VERSION.SDK_INT >= 23 -> arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )

            else -> emptyArray()
        }
    }

    fun getCameraPermission(): Array<String> {
        return arrayOf(Manifest.permission.CAMERA)
    }

    fun getLocationPermissions(): Array<String> {
        return when {
            Build.VERSION.SDK_INT >= 31 -> arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )

            Build.VERSION.SDK_INT >= 29 -> arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
//                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            )

            else -> arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    fun getBackLocationPermissions(): Array<String> {
        
        return when {
            Build.VERSION.SDK_INT >= 29 -> arrayOf(
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            )

            else -> emptyArray()
        }
    }

    fun getStoragePermissions(): Array<String> {
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> {
                arrayOf(Manifest.permission.MANAGE_EXTERNAL_STORAGE)
            }

            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            }

            else -> emptyArray()
        }
    }

    fun isStoragePermissionGranted(activity: AppCompatActivity): Boolean {
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> {
                android.os.Environment.isExternalStorageManager()
            }

            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                val read = ContextCompat.checkSelfPermission(
                    activity, Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
                val write = ContextCompat.checkSelfPermission(
                    activity, Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
                read && write
            }

            else -> true
        }
    }
}