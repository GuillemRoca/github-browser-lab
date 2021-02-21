package dev.guillem.githubbrowserlab.presentation.tools.dialog

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import dev.guillem.githubbrowserlab.R
import javax.inject.Inject

class AlertDialogFactory @Inject constructor() {
    fun showError(context: Context) = AlertDialog.Builder(context)
        .setTitle(context.getString(R.string.dialog_error_title))
        .setMessage(context.getString(R.string.dialog_error_description))
        .setPositiveButton(context.getString(android.R.string.ok)) { _, _ -> }
        .create()
        .show()

    fun showMoreInfoRepository(
        context: Context,
        learnMoreRepositoryClickListener: DialogInterface.OnClickListener,
        learnMoreOwnerClickListener: DialogInterface.OnClickListener,
    ) =
        AlertDialog.Builder(context)
            .setTitle(context.getString(R.string.dialog_repository_title))
            .setMessage(context.getString(R.string.dialog_repository_description))
            .setPositiveButton(
                context.getString(R.string.learn_more_repository),
                learnMoreRepositoryClickListener
            )
            .setNegativeButton(
                context.getText(R.string.learn_more_owner),
                learnMoreOwnerClickListener
            )
            .create()
            .show()
}