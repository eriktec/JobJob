package com.res.jobjob.common.base

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.res.jobjob.ControlActivity
import com.res.jobjob.model.data.User

open class BaseFragment : Fragment() {

    lateinit var alertDialog: AlertDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        alertDialog = BaseDialogs.loadingDialog(requireContext(), "Iniciando")
    }

    protected fun String.checkString(): Boolean {
        if (this.isNotEmpty()) return true
        return false
    }

    protected fun success(user: User) {
        alertDialog.dismiss()
        val intent = Intent(requireContext(), ControlActivity::class.java)
        intent.putExtra("user", user)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        requireActivity().finish()
    }

    protected fun error(errorMsg: String) {
        alertDialog.dismiss()
        Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_SHORT).show()
    }
}