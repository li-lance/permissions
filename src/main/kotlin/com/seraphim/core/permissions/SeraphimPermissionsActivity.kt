package com.seraphim.core.permissions

import android.os.Bundle
import com.seraphim.core.permissions.databinding.ActivitySeraphimPermissionsBinding

class SeraphimPermissionsActivity : PermissionsActivity(R.layout.activity_seraphim_permissions) {
    private lateinit var binding: ActivitySeraphimPermissionsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeraphimPermissionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            tvPermissionTitle.text = intent.getStringExtra(EXTRA_PERMISSIONS_TITLE) ?: "Permissions Required"
            tvPermissionDesc.text = intent.getStringExtra(EXTRA_PERMISSIONS_DESCRIPTION) ?: "Please grant the required permissions to continue."
            btnRequestPermission.setOnClickListener {
               verifyPermissions()
            }
            btnDeny.setOnClickListener {
                finish()
            }
        }
    }

    override fun onPermissionsGranted() {
    }

    override fun onPermissionsDenied() {
   }
}