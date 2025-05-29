package com.seraphim.core.permissions

import android.app.Activity
import android.content.Intent

object SeraphimPermissionsUtils {
    fun checkPermissions(
        activity: Activity,
        permissions: Array<String>,
        title: String? = null,
        description: String? = null
    ) {
        val intent = Intent(activity, SeraphimPermissionsActivity::class.java).apply {
            putExtra(PermissionsActivity.EXTRA_PERMISSIONS, permissions)
            putExtra(PermissionsActivity.EXTRA_PERMISSIONS_TITLE, title ?: "Permissions Required")
            putExtra(
                PermissionsActivity.EXTRA_PERMISSIONS_DESCRIPTION,
                description ?: "Please grant the required permissions to continue."
            )
        }
        activity.startActivity(intent)
    }
}