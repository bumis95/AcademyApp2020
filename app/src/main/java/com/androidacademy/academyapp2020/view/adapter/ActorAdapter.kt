package com.androidacademy.academyapp2020.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.androidacademy.academyapp2020.R
import com.androidacademy.academyapp2020.data.model.Actor
import com.androidacademy.academyapp2020.databinding.ViewHolderActorBinding

class ActorAdapter(
    private val actorList: List<Actor>
) : RecyclerView.Adapter<ActorAdapter.ActorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder =
        ActorViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_actor,
                parent,
                false
            )
        )

    override fun onBindViewHolder(viewHolder: ActorViewHolder, position: Int) {
        viewHolder.bind(actorList[position])
    }

    override fun getItemCount(): Int = actorList.size

    class ActorViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ViewHolderActorBinding.bind(view)

        fun bind(actor: Actor) {
            binding.apply {
//                TODO make class loader
                ivActorPicture.load(actor.picture) {
                    transformations(RoundedCornersTransformation(16f))
                }
                tvActorName.text = actor.name
            }
        }
    }
}