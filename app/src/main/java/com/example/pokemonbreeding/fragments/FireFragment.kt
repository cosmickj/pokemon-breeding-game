package com.example.pokemonbreeding.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pokemonbreeding.R
import com.example.pokemonbreeding.databinding.FragmentFireBinding

class FireFragment : Fragment() {
    private var _binding: FragmentFireBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val TAG : String = "로그"

        // GrassFragment.newInstance()
        fun newInstance() : FireFragment {
            return FireFragment()
        }
    }

    var increment_number = 1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "FireFragment - onCreateView() called")
        _binding = FragmentFireBinding.inflate(inflater, container, false)

        binding.candyBtn.setOnClickListener {
            binding.candyBtn.animate().apply {
                duration = 100
                translationY(-20f)
            }.withEndAction{
                binding.candyBtn.animate().apply {
                    duration = 100
                    translationY(20f)
                }.start()
            }

            increment_number++
            binding.pokemonLevel.text = increment_number.toString()

            if (increment_number >= 16) {
                binding.pokemonName.setText("리자드")
                binding.firePokemon.setImageResource(R.drawable.img_fire_charmeleon)
            }
            if (increment_number >= 36) {
                binding.pokemonName.setText("리자몽")
                binding.firePokemon.setImageResource(R.drawable.img_fire_charizard)
            }
        }

        // 포켓몬 도감
        binding.pokemonName.setOnClickListener {
            val current_pokemon = binding.pokemonName.text.toString()
            if (current_pokemon == "파이리")
            {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.pokemonkorea.co.kr/pokedex/view/6"))
                startActivity(intent)
            }
            if (current_pokemon == "리자드")
            {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.pokemonkorea.co.kr/pokedex/view/7"))
                startActivity(intent)
            }
            if (current_pokemon == "리자몽")
            {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.pokemonkorea.co.kr/pokedex/view/8"))
                startActivity(intent)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}