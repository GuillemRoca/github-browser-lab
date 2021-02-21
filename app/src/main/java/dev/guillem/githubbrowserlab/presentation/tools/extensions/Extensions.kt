package dev.guillem.githubbrowserlab.presentation.tools.extensions

import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun Fragment.getColor(@ColorRes colorResource: Int) =
    ContextCompat.getColor(requireContext(), colorResource)