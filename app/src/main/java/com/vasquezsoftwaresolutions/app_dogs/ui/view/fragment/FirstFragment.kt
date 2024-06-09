package com.vasquezsoftwaresolutions.app_dogs.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vasquezsoftwaresolutions.app_dogs.R
import com.vasquezsoftwaresolutions.app_dogs.databinding.FragmentFirstBinding
import com.vasquezsoftwaresolutions.app_dogs.ui.view.adapter.RazasAdapter
import com.vasquezsoftwaresolutions.app_dogs.viewmodel.DogViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var _binding: FragmentFirstBinding
    private val viewModel: DogViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RazasAdapter()
        binding.RvRazas.adapter = adapter
        binding.RvRazas.layoutManager = LinearLayoutManager(context)

        viewModel.getBreedList().observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.update(it)
            }
        })

        adapter.selectedBreed().observe(viewLifecycleOwner, Observer {
            it?.let {
                viewModel.getImagesByBreedFromInternet(it.breed)
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()

    }
}