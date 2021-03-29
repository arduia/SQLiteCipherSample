package com.demo.sqliteciphersample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.TtsSpan
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.demo.sqliteciphersample.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.StringBuilder
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.noteList.observe(this) { noteList ->
            Log.d("Main", "noteList $noteList")

            val stringBuilder = StringBuilder()
            noteList.forEach {
                stringBuilder.append("Note ${it.note} Date: ${it.date} ")
                stringBuilder.appendLine()
            }
            binding.tvHello.text = stringBuilder.toString()
        }
    }

}