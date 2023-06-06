package com.feature.details.ui.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.feature.details.R
import com.feature.details.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
private val detailsViewModel : DetailsViewModel by viewModels()
    private lateinit var detailsBinding: FragmentDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        detailsBinding = FragmentDetailsBinding.inflate(inflater)
        return detailsBinding.root
    }

}