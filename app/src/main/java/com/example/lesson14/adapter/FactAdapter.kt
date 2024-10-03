package com.example.lesson14.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson14.RandomFact
import com.example.lesson14.databinding.FactItemBinding

class FactAdapter(private var facts: List<RandomFact>) : RecyclerView.Adapter<FactAdapter.FactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {
        val binding = FactItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
        val fact = facts[position]
        holder.bind(fact)
    }

    override fun getItemCount(): Int {
        return facts.size
    }

    inner class FactViewHolder(private val binding: FactItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(fact: RandomFact) {
            binding.textView.text = fact.text
        }
    }
}