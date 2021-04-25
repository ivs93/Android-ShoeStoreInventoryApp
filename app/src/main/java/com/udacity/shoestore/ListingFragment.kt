package com.udacity.shoestore

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginStart
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentInstructionsBinding
import com.udacity.shoestore.databinding.FragmentListingBinding

class ListingFragment : Fragment() {

    private val model: ShoesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentListingBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_listing, container, false)
        binding.floatingActionButton.setOnClickListener {
            it.findNavController().navigate(ListingFragmentDirections.actionListingFragmentToDetailFragment())
        }
        setHasOptionsMenu(true)

        model.shoes.observe(viewLifecycleOwner, Observer { shoes ->
            shoes.forEach {
                val view = TextView(requireContext())
                view.textSize = 16.0F
                view.text="Name: ${it.name}\nCompany: ${it.company}\nSize: ${it.size}\nDescription: ${it.description}"
                view.setPadding(16,8,16,0)
                binding.linearLayout.addView(view)
                val line = View(requireContext())
                line.layoutParams = LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, 5)
                line.setBackgroundColor(Color.parseColor("#B3B3B3"))
                binding.linearLayout.addView(line)
            }
        })



        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.loginFragment) {
            findNavController().navigate(R.id.action_listingFragment_to_loginFragment)
        }
        return super.onOptionsItemSelected(item)
    }
}