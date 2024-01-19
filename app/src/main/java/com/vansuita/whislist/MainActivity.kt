package com.vansuita.whislist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.vansuita.whislist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

	private val binding by lazy {
		ActivityMainBinding.inflate(layoutInflater)
	}

	private val adapter by lazy {
		ListAdapter()
	}

	private val editTextSet by lazy {
		binding.run {
			setOf(name, url, price)
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
		setSupportActionBar(binding.toolbar)

		editTextSet.forEach { eachOne ->
			eachOne.doAfterTextChanged {
				binding.submit.isEnabled = editTextSet.all { it.text.isNotBlank() }
			}
		}

		binding.list.adapter = adapter
		binding.submit.setOnClickListener {
			doSubmit()
		}
	}

	private fun doSubmit() {
		val (name, url, price) = editTextSet.map { it.text.toString() }
		editTextSet.forEach { it.text.clear() }

		adapter.addItem(
			Item(
				name = name,
				url = url,
				price = price.toFloat()
			)
		)
	}
}