package com.test.movieapplication.screens.fragment.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.test.movieapplication.databinding.FragmentAboutBinding


class AboutFragment : Fragment() {
    private val binding by lazy { FragmentAboutBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
//        val translateAPI = TranslateAPI(
//            Language.AUTO_DETECT,
//            Language.UKRAINIAN,
//            "Your Text"
//        )
//        translateAPI.setTranslateListener(object : TranslateListener {
//            override fun onSuccess(translatedText: String) {
//                Log.d("MyLog", "onSuccess: $translatedText")
//            }
//
//            override fun onFailure(ErrorText: String) {
//                Log.d("MyLog", "onFailure: $ErrorText")
//            }
//        })

        initTextViewScrollable()
        onBackButtonClickListener()
        buttonInstagramClickListener()
        buttonTwitterClickListener()
        buttonTelegramClickListener()
    }

    private fun initTextViewScrollable() {
        binding.textViewPoliticsSecurity.movementMethod = ScrollingMovementMethod()
    }

    private fun onBackButtonClickListener() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun buttonInstagramClickListener() {
        binding.btnGoToInstagram.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/sliderzxc/"))
            startActivity(browserIntent)
        }
    }

    private fun buttonTelegramClickListener() {
        binding.btnGoToTelegram.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/sliderzxc"))
            startActivity(browserIntent)
        }
    }

    private fun buttonTwitterClickListener() {
        binding.btnGoToTwitter.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/vadwwxz"))
            startActivity(browserIntent)
        }
    }

}