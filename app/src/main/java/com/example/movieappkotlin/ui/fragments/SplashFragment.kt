package com.example.movieappkotlin.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.movieappkotlin.R


class SplashFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        withHandlerRunnable(3500)
    }

    private fun withHandlerRunnable(delay: Long) {
        val runnable = Runnable {
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        }
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(runnable, delay)
    }

}