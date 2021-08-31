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
import com.example.pokemonbreeding.databinding.FragmentWaterBinding

class WaterFragment : Fragment() {
    private var _binding: FragmentWaterBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val TAG : String = "로그"

        // GrassFragment.newInstance()
        fun newInstance() : WaterFragment {
            return WaterFragment()
        }
    }

    var increment_number = 1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "WaterFragment - onCreateView() called")
        _binding = FragmentWaterBinding.inflate(inflater, container, false)

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
                binding.pokemonName.setText("어니부기")
                binding.waterPokemon.setImageResource(R.drawable.img_water_wartortle)
            }
            if (increment_number >= 36) {
                binding.pokemonName.setText("거북왕")
                binding.waterPokemon.setImageResource(R.drawable.img_water_blastoise)
            }
        }

        // 포켓몬 도감
        binding.pokemonName.setOnClickListener {
            val current_pokemon = binding.pokemonName.text.toString()
            if (current_pokemon == "꼬부기")
            {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.pokemonkorea.co.kr/pokedex/view/12"))
                startActivity(intent)
            }
            if (current_pokemon == "어니부기")
            {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.pokemonkorea.co.kr/pokedex/view/13"))
                startActivity(intent)
            }
            if (current_pokemon == "거북왕")
            {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.pokemonkorea.co.kr/pokedex/view/14"))
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