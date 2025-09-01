package com.seraphim.core.permissions

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class SeraphimPermissionRequestContract :
    ActivityResultContract<SeraphimPermissionRequestContract.Input, Boolean>() {
    data class Input(
        val permissions: Array<String>,
        val title: String? = null,
        val description: String? = null
    ) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Input

            if (!permissions.contentEquals(other.permissions)) return false
            if (title != other.title) return false
            if (description != other.description) return false

            return true
        }

        override fun hashCode(): Int {
            var result = permissions.contentHashCode()
            result = 31 * result + (title?.hashCode() ?: 0)
            result = 31 * result + (description?.hashCode() ?: 0)
            return result
        }
    }

    override fun createIntent(context: Context, input: Input): Intent {
        return Intent(context, SeraphimPermissionsActivity::class.java).apply {
            putExtra(PermissionsActivity.EXTRA_PERMISSIONS, input.permissions)
            putExtra(
                PermissionsActivity.EXTRA_PERMISSIONS_TITLE,
                input.title ?: "Permissions Required"
            )
            putExtra(
                PermissionsActivity.EXTRA_PERMISSIONS_DESCRIPTION,
                input.description ?: "Please grant the required permissions to continue."
            )
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Boolean {
        // 约定：SeraphimPermissionsActivity setResult(Activity.RESULT_OK) 表示全部授权
        return resultCode == Activity.RESULT_OK
    }
}

