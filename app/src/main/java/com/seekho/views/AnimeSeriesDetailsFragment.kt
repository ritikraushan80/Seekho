package com.seekho.views

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.seekho.R
import com.seekho.base.BaseFragment
import com.seekho.databinding.FragmentAnimeSeriesDetailsBinding
import com.seekho.helpers.StringConstants
import com.seekho.model.AnimeSeriesList
import com.seekho.model.Genres
import com.seekho.model.NetworkState
import com.seekho.utils.SharedPref
import com.seekho.views.adapter.GenresAdapter
import java.text.SimpleDateFormat
import java.util.Locale

class AnimeSeriesDetailsFragment : BaseFragment() {
    private lateinit var _binding: FragmentAnimeSeriesDetailsBinding
    private val binding get() = _binding
    private var isExpanded = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAnimeSeriesDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backBtn.setOnClickListener {
            requireView().findNavController().navigateUp()
        }

        val title = SharedPref.read(StringConstants.TITLE, null)
        val animeId = SharedPref.read(StringConstants.ANIME_ID, null)

        binding.tlTitle.text = title

        /**
         * Observer and request to call API
         */
        animeViewModel.getAnimeDetails(animeId?.toInt() ?: 0)
        animeViewModel.animeDetails.observe(viewLifecycleOwner, ::onGettingAnimeDetails)
    }

    /**
     * Function to handle api result of Anime Details
     */
    private fun onGettingAnimeDetails(state: NetworkState<AnimeSeriesList>?) {
        if (state is NetworkState.Loading) {
            binding.loader.visibility = View.VISIBLE
            binding.scrollview.visibility = View.GONE
            return
        }
        when (state) {
            is NetworkState.Success -> {
                binding.loader.visibility = View.GONE
                binding.scrollview.visibility = View.VISIBLE

                state.data.let {
                    /**
                     * Check Trailer (Video) null or not
                     */
                    if (it?.trailer?.youtubeId != null) {
                        binding.videoPlayer.visibility = View.VISIBLE
                        binding.imgBanner.visibility = View.GONE
                        binding.videoPlayer.settings.javaScriptEnabled = true
                        binding.videoPlayer.settings.domStorageEnabled = true

                        binding.videoPlayer.loadUrl(it.trailer?.embedUrl ?: "")
                    } else {
                        binding.videoPlayer.visibility = View.GONE
                        binding.imgBanner.visibility = View.VISIBLE

                        binding.imgBanner.load(it?.images?.jpg?.largeImageUrl) {
                            crossfade(true)
                            placeholder(R.drawable.logo)
                        }
                    }

                    binding.title.text = it?.title ?: "N/A"
                    setupDescription(it?.synopsis ?: "N/A")

                    genresList(it?.genres ?: ArrayList())

                    /**
                     * Broadcast Info
                     */
                    if (it?.broadcast?.day != null) {
                        binding.castCardView.visibility = View.VISIBLE
                        binding.brdLogo.visibility = View.VISIBLE
                        binding.day.text = "Day: ${it.broadcast?.day ?: "N/A"}"
                        binding.time.text =
                            "Time: ${convertTo12HourFormat(it.broadcast?.time ?: "N/A")}"
                        binding.timezone.text = "Timezone : ${it.broadcast?.timezone ?: "N/A"}"
                        binding.broadcastString.text = it.broadcast?.string ?: "N/A"
                    } else {
                        binding.castCardView.visibility = View.GONE
                        binding.brdLogo.visibility = View.GONE
                    }
                    binding.episode.text = "Episodes: ${it?.episodes ?: "N/A"}"
                    binding.rating.text = "Rating: ${it?.rating ?: "N/A"}"
                }

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
     * Function to list of Genres
     */
    private fun genresList(data: ArrayList<Genres>) {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.genresRecyclerview.layoutManager = layoutManager
        val adapter = GenresAdapter(data)
        binding.genresRecyclerview.adapter = adapter
    }

    /**
     * Function to expand description
     */
    private fun setupDescription(synopsis: String?) {
        val descriptionText = synopsis ?: ""
        binding.description.text = descriptionText

        binding.description.post {
            if (binding.description.lineCount > 3) {
                binding.expandText.visibility = View.VISIBLE
                binding.description.maxLines = 3
                binding.description.ellipsize = TextUtils.TruncateAt.END
            } else {
                binding.expandText.visibility = View.GONE
            }
        }

        binding.expandText.setOnClickListener {
            isExpanded = !isExpanded
            if (isExpanded) {
                binding.description.maxLines = Integer.MAX_VALUE
                binding.description.ellipsize = null
                binding.expandText.text = "See less.."
            } else {
                binding.description.maxLines = 3
                binding.description.ellipsize = TextUtils.TruncateAt.END
                binding.expandText.text = "See more.."
            }
        }
    }

    /**
     * Function to convert time
     */
    private fun convertTo12HourFormat(time24: String): String {
        val hour24 = SimpleDateFormat("HH:mm", Locale.getDefault())
        val hour12 = SimpleDateFormat("hh:mm a", Locale.getDefault())
        val date = hour24.parse(time24)
        return hour12.format(date!!)
    }

}