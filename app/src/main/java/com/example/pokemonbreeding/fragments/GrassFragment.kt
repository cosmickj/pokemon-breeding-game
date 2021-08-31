package com.example.pokemonbreeding.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.pokemonbreeding.R
import com.example.pokemonbreeding.databinding.FragmentGrassBinding

class GrassFragment : Fragment() {
    private var _binding: FragmentGrassBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val TAG : String = "로그"

        // GrassFragment.newInstance()
        fun newInstance() : GrassFragment {
            return GrassFragment()
        }
    }

    // 메모리에 올라가는 것
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "GrassFragment - onCreate() called")
    }
    // 메인 액티비티에 프래그먼트가 붙는 것
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "GrassFragment - onAttach() called")
    }

    var increment_number = 1
    // 메인 액티비티의 onCreate와 동일한 것
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "GrassFragment - onCreateView() called")
        _binding = FragmentGrassBinding.inflate(inflater, container, false)

        // 이상한 사탕 클릭
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
                binding.pokemonName.setText("이상해풀")
                binding.grassPokemon.setImageResource(R.drawable.img_grass_ivysaur)
            }
            if (increment_number >= 32) {
                binding.pokemonName.setText("이상해꽃")
                binding.grassPokemon.setImageResource(R.drawable.img_grass_venusaur)
            }
        }

        // 포켓몬 도감
        binding.pokemonName.setOnClickListener {
            val current_pokemon = binding.pokemonName.text.toString()
            if (current_pokemon == "이상해씨")
            {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.pokemonkorea.co.kr/pokedex/view/1"))
                startActivity(intent)
            }
            if (current_pokemon == "이상해풀")
            {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.pokemonkorea.co.kr/pokedex/view/2"))
                startActivity(intent)
            }
            if (current_pokemon == "이상해꽃")
            {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.pokemonkorea.co.kr/pokedex/view/3"))
                startActivity(intent)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
        Log.d(TAG, "GrassFragment - onDestroyView() called")
    }
}