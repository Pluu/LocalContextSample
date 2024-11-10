package com.pluu.sample.compose.context

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentActivity
import com.pluu.sample.compose.context.databinding.ActivityFragmentContainerBinding
import com.pluu.sample.compose.context.ui.theme.SampleTheme

class FragmentContainerActivity : FragmentActivity() {
    private lateinit var binding: ActivityFragmentContainerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFragmentContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.composeView.setContent {
            SampleTheme {
                ContextText("Activity")
            }
        }
    }

    companion object {
        fun start(context: Context)  {
            context.startActivity(Intent(context, FragmentContainerActivity::class.java))
        }
    }
}