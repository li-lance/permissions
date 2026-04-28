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

    // ── NFC 相关权限 ───────────────────────────────

    /**
     * NFC 功能本身不需要运行时权限，但 Nearby 交互（如 Android Beam / 设备配对）
     * 在 Android 12+ 需要 BLUETOOTH_CONNECT / BLUETOOTH_SCAN。
     * 本模块仅返回空数组，由调用方按需补充。
     */
    fun getNfcPermissions(): Array<String> = emptyArray()

    /**
     * 检查设备是否具备 NFC 硬件
     */
    fun isNfcHardwareAvailable(context: android.content.Context): Boolean {
        val nfcAdapter = android.nfc.NfcAdapter.getDefaultAdapter(context)
        return nfcAdapter != null
    }

    /**
     * 检查 NFC 是否已开启
     */
    fun isNfcEnabled(context: android.content.Context): Boolean {
        val nfcAdapter = android.nfc.NfcAdapter.getDefaultAdapter(context)
        return nfcAdapter?.isEnabled == true
    }
}