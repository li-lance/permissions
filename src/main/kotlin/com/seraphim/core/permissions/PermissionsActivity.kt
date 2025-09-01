package com.seraphim.core.permissions

import android.content.pm.PackageManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

abstract class PermissionsActivity(@LayoutRes layoutId: Int) : AppCompatActivity(layoutId) {
    companion object {
        const val EXTRA_PERMISSIONS = "extra_permissions"
        const val EXTRA_PERMISSIONS_TITLE = "extra_permissions_title"
        const val EXTRA_PERMISSIONS_DESCRIPTION = "extra_permissions_description"
        const val REQUEST_CODE = 1001
    }

    protected fun verifyPermissions() {
        val permissions = intent.getStringArrayExtra(EXTRA_PERMISSIONS) ?: emptyArray()
        if (permissions.isEmpty()) {
            finish()
            return
        }
        if (hasPermissions(permissions)) {
            onPermissionsGranted()
            finish()
        } else {
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE)
        }
    }

    private fun hasPermissions(permissions: Array<String>): Boolean {
        return permissions.all {
            ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                onPermissionsGranted()
            } else {
                onPermissionsDenied()
            }
            finish()
        }
    }

    abstract fun onPermissionsGranted()

    abstract fun onPermissionsDenied()
}