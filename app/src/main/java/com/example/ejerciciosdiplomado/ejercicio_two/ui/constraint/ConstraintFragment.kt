package com.example.ejerciciosdiplomado.ejercicio_two.ui.constraint

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
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
            //val pokeAdapter = PokemonAdapter(getPokemonList())
            /*pokeAdapter.onItemSelected = { pokemon ->
                Toast.makeText(context, pokemon.name, Toast.LENGTH_SHORT).show()
            }*/
            adapter = PokemonAdapter(getPokemonList())
        }
    }

    private fun getPokemonList(): List<Pokemon> {
        val pokemonList = mutableListOf<Pokemon>()
        pokemonList.add(Pokemon("Pikachu","https://i0.wp.com/eltallerdehector.com/wp-content/uploads/2022/06/6420b-pikachu-sentado-png.png?resize=450%2C450&ssl=1"))
        pokemonList.add(Pokemon("Charizard", "https://static.wikia.nocookie.net/espokemon/images/9/95/Charizard.png/revision/latest?cb=20180325003352"))
        // Agrega más Pokémon a la lista según sea necesario
        return pokemonList
    }
}