package com.example.wildberriesweek71

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wildberriesweek71.databinding.FragmentInfoBinding


class InfoFragment : Fragment() {
    lateinit var binding: FragmentInfoBinding

    val uri: Uri = Uri.parse("https://github.com/golevArtemOhta")
    val intent = Intent(Intent.ACTION_VIEW, uri)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.textViewGitHub.setOnClickListener {
            startActivity(intent)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = InfoFragment()
    }
}