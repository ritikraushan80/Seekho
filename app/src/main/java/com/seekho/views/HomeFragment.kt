package com.seekho.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.seekho.base.BaseFragment
import com.seekho.databinding.FragmentHomeBinding
import com.seekho.helpers.StringConstants
import com.seekho.model.AnimeSeriesList
import com.seekho.model.NetworkState
import com.seekho.utils.CallbackListener
import com.seekho.utils.SharedPref
import com.seekho.views.adapter.AnimeSeriesAdapter

class HomeFragment : BaseFragment(), CallbackListener {
    private lateinit var _binding: FragmentHomeBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * Observer and request to call API
         */
        if (animeViewModel.animeSeries.value !is NetworkState.Success) {
            animeViewModel.getAnimeSeriesList()
        }
        animeViewModel.animeSeries.observe(viewLifecycleOwner, ::onGettingAnimeSeriesList)
    }

    /**
     * Function to handle api result of Anime Series
     */
    private fun onGettingAnimeSeriesList(state: NetworkState<ArrayList<AnimeSeriesList>>?) {
        if (state is NetworkState.Loading) {
            binding.loader.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
            return
        }
        when (state) {
            is NetworkState.Success -> {
                binding.loader.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE

                state.data?.let { animeSeriesList(it) }
            }

            is NetworkState.Error -> {
                Log.e("Error", state.message.toString() + state.errCode)
                state.message?.let { showToast(it) }
            }

            is NetworkState.Failure -> {
                showToast(StringConstants.CONNECTION_ERROR)
            }

            else -> {
                showToast("Error occurred ")
            }
        }
    }

    /**
     * Function to list of Anime Series
     */
    private fun animeSeriesList(data: ArrayList<AnimeSeriesList>) {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager
        val adapter = AnimeSeriesAdapter(data, this)
        binding.recyclerView.adapter = adapter
    }

    override fun clickOnAnimeItem(data: AnimeSeriesList) {
        if (isAdded && activity != null) {
            requireView().findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToAnimeSeriesDetailsFragment())
            SharedPref.write(StringConstants.ANIME_ID, data.malId.toString())
            SharedPref.write(StringConstants.TITLE, data.title)
        }
    }
}