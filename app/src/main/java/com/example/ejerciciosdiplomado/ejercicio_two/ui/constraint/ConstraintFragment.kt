package com.example.ejerciciosdiplomado.ejercicio_two.ui.constraint

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ejerciciosdiplomado.R
import com.example.ejerciciosdiplomado.databinding.FragmentConstraintBinding

class ConstraintFragment : Fragment(){
    private var _binding: FragmentConstraintBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(ConstraintViewModel::class.java)

        _binding = FragmentConstraintBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textConstraint
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        setupRecyclerView()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            setHasFixedSize(true)
            val context: Context = requireContext()
            layoutManager = LinearLayoutManager(context)
            //layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PokemonAdapter(getPokemonList())
        }
    }

    private fun getPokemonList(): List<Pokemon> {
        val pokemonList = mutableListOf<Pokemon>()
        pokemonList.add(Pokemon("Pikachu","https://www.google.com/url?sa=i&url=https%3A%2F%2Far.pinterest.com%2Fflor1531%2Fpikachu%2F&psig=AOvVaw15R8WhCbXgYaFLxEI4roFa&ust=1687657967581000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCKiOvY_m2v8CFQAAAAAdAAAAABAE"))
        pokemonList.add(Pokemon("Charizard", "https://www.google.com/url?sa=i&url=https%3A%2F%2Festrategiaspokemon.fandom.com%2Fes%2Fwiki%2FCharizard&psig=AOvVaw2qGZBR91y62IqJNaEvjPre&ust=1687658027418000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCJjss5vm2v8CFQAAAAAdAAAAABAE"))
        // Agrega más Pokémon a la lista según sea necesario
        return pokemonList
    }
}