package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.viewmodel.ShoeViewModel
import com.udacity.shoestore.databinding.*
import kotlinx.android.synthetic.main.item_shoe.view.*

class ShoeListFragment : Fragment() {

    private val viewModel: ShoeViewModel by activityViewModels()
    private val binding by lazy {
        FragmentShoeListBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel.shoeList.observe(viewLifecycleOwner) {
            binding.shoeList.removeAllViews()
            for (shoes in it) {
                addShoes(shoes.name)
            }
        }

        binding.addShoeButton.setOnClickListener {
            it.findNavController().navigate(
                ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment()
            )
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_shoe_list, menu)
    }
    private fun addShoes(shoeName: String) {
        val view = layoutInflater.inflate(R.layout.item_shoe, null)
        view.textView.text = shoeName
        binding.shoeList.addView(view.textView)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_logout ->
                findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
        }
        return super.onOptionsItemSelected(item)
    }


}