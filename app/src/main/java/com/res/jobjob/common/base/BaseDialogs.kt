package com.res.jobjob.common.base

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dmax.dialog.SpotsDialog

class BaseDialogs {

    companion object {
        fun alertDialogAccept(context: Context, title: String, message: String, dialogInterface: DialogInterface.OnClickListener): MaterialAlertDialogBuilder {
            return MaterialAlertDialogBuilder(context)
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton("Aceptar", dialogInterface)
        }

        fun loadingDialog(context: Context, message: String): AlertDialog {
            return SpotsDialog.Builder().setContext(context).setMessage(message).build()
        }
    }
}