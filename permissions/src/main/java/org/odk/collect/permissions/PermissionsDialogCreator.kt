package org.odk.collect.permissions

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.google.android.material.dialog.MaterialAlertDialogBuilder

internal interface PermissionsDialogCreator {
    fun showEnableGPSDialog(
        activity: Activity,
        action: PermissionListener
    )

    fun showAdditionalExplanation(
        activity: Activity,
        title: Int,
        message: Int,
        drawable: Int,
        action: PermissionListener
    )
}

internal object PermissionsDialogCreatorImpl : PermissionsDialogCreator {
    override fun showEnableGPSDialog(
        activity: Activity,
        action: PermissionListener
    ) {
        MaterialAlertDialogBuilder(activity)
            .setMessage(activity.getString(org.odk.collect.strings.R.string.gps_enable_message))
            .setCancelable(false)
            .setPositiveButton(
                activity.getString(org.odk.collect.strings.R.string.enable_gps)
            ) { _: DialogInterface?, _: Int ->
                activity.startActivityForResult(
                    Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS),
                    0
                )
            }
            .setNegativeButton(
                activity.getString(org.odk.collect.strings.R.string.cancel)
            ) { dialog: DialogInterface, _: Int ->
                action.denied()
                dialog.cancel()
            }
            .create()
            .show()
    }

    override fun showAdditionalExplanation(
        activity: Activity,
        title: Int,
        message: Int,
        drawable: Int,
        action: PermissionListener
    ) {
        MaterialAlertDialogBuilder(activity)
            .setIcon(drawable)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(org.odk.collect.strings.R.string.ok) { _, _ ->
                action.additionalExplanationClosed()
            }
            .setNeutralButton(org.odk.collect.strings.R.string.open_settings) { _, _ ->
                Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", activity.packageName, null)
                    activity.startActivity(this)
                }
                action.additionalExplanationClosed()
            }
            .create()
            .show()
    }
}
