package com.vansuita.whislist

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.content.ContextCompat

fun Context.openUrl(item: Item) = try {
	ContextCompat.startActivity(
		this,
		Intent(Intent.ACTION_VIEW, Uri.parse(item.url)),
		null
	)
} catch (e: ActivityNotFoundException) {
	Toast.makeText(this, "Invalid URL for " + item.name, Toast.LENGTH_LONG)
		.show()
}