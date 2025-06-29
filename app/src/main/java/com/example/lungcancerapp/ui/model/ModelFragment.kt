package com.example.lungcancerapp.ui.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.lungcancerapp.databinding.FragmentModelBinding

class ModelFragment : Fragment() {

    private var _binding: FragmentModelBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentModelBinding.inflate(inflater, container, false)

        binding.cardArsitektur.setOnClickListener {
            val action = ModelFragmentDirections.actionNavModelToModelDetailFragment("arsitektur")
            findNavController().navigate(action)
        }
        binding.cardDataPrep.setOnClickListener {
            val action = ModelFragmentDirections.actionNavModelToModelDetailFragment("data")
            findNavController().navigate(action)
        }
        binding.cardModeling.setOnClickListener {
            val action = ModelFragmentDirections.actionNavModelToModelDetailFragment("modeling")
            findNavController().navigate(action)
        }
        binding.cardSaveModel.setOnClickListener {
            val action = ModelFragmentDirections.actionNavModelToModelDetailFragment("save")
            findNavController().navigate(action)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
