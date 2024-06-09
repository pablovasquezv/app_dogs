package com.vasquezsoftwaresolutions.app_dogs.ui.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.vasquezsoftwaresolutions.app_dogs.R
import com.vasquezsoftwaresolutions.app_dogs.databinding.FragmentSecondBinding
import com.vasquezsoftwaresolutions.app_dogs.ui.view.adapter.ImagesAdapter
import com.vasquezsoftwaresolutions.app_dogs.viewmodel.DogViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var _binding: FragmentSecondBinding
    private val viewModel: DogViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ImagesAdapter()
        binding.RvImages.adapter = adapter
        binding.RvImages.layoutManager = GridLayoutManager(context, 1)

        viewModel.getImages().observe(viewLifecycleOwner) {
            it?.let {
                adapter.update(it)
            }
        }

        adapter.selectedImage().observe(viewLifecycleOwner) {
            it?.let {
                if (it.fav) {
                    it.fav = false
                    viewModel.updateFav(it)
                    Log.d("Fav", " no es fav")
                    Toast.makeText(context, R.string.NoesFav, Toast.LENGTH_LONG).show()
                } else {
                    it.fav = true
                    Log.d("Fav", " es fav")
                    viewModel.updateFav(it)
                    Toast.makeText(context, R.string.esFav, Toast.LENGTH_LONG).show()
                }
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()

    }
}