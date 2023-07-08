package com.example.ejerciciosdiplomado.ejercicio_two.ui.constraint

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.ejerciciosdiplomado.R
import com.squareup.picasso.Picasso

class PokemonAdapter(private val pokemonList: List<Pokemon>) :
    RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {
    private lateinit var mContext: Context
    var onItemSelected : ((Pokemon) -> Unit)? = null
    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pokemonImage: ImageView = itemView.findViewById(R.id.pokemonImage)
        val pokemonName: TextView = itemView.findViewById(R.id.pokemonName)
        //val root: ConstraintLayout = itemView.findViewById(R.id.root)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        mContext = parent.context
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val currentPokemon = pokemonList[position]
        // holder.pokemonImage.setImageResource()
        holder.pokemonName.text = currentPokemon.name
        Picasso.get()
            .load(currentPokemon.imageResId)
            .error(R.drawable.ic_people)
            .placeholder(R.drawable.img_logo)
            .into(holder.pokemonImage)
        /*holder.root.setOnClickListener {
            onItemSelected?.invoke(currentPokemon)
        }*/
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }
}