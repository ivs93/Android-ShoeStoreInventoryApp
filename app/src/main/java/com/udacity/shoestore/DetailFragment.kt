package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentDetailBinding
import com.udacity.shoestore.databinding.FragmentListingBinding

class DetailFragment : Fragment() {

    private val model: ShoesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        binding.shoesViewModel = model

        binding.setLifecycleOwner(this)

        model.eventGoBack.observe(viewLifecycleOwner, Observer { goBack ->
            if(goBack){
                findNavController().navigate(DetailFragmentDirections.actionGoBack())
                model.onGoBackComplete()
            }
        })

        return binding.root
    }
}