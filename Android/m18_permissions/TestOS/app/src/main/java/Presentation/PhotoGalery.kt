package Presentation

import Data.App
import Data.LocalRepository
import Entity.Photo
import android.app.Application
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.testos.R
import com.example.testos.databinding.FragmentPhotoGaleryBinding


class PhotoGalery : Fragment() {


    private var _binding: FragmentPhotoGaleryBinding? = null
    private val binding get() = _binding!!
    object application : Application()
    val viewModel: PhotoGaleryViewModel by viewModels {
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {

                val r = LocalRepository(requireContext())
                return PhotoGaleryViewModel(r) as T
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPhotoGaleryBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val kartinki: List<AppCompatImageView> =
            listOf(
                binding.photoOne.binding.image,
                binding.photoTwo.binding.image,
                binding.photoThree.binding.image,
                binding.photoFour.binding.image,
                binding.photoFive.binding.image,
                binding.photoSix.binding.image,
                binding.photoSeven.binding.image,
                binding.photoEight.binding.image,
                binding.photoNine.binding.image
            )
        val dats: List<TextView> =
        listOf(
            binding.photoOne.binding.dataText,
            binding.photoTwo.binding.dataText,
            binding.photoThree.binding.dataText,
            binding.photoFour.binding.dataText,
            binding.photoFive.binding.dataText,
            binding.photoSix.binding.dataText,
            binding.photoSeven.binding.dataText,
            binding.photoEight.binding.dataText,
            binding.photoNine.binding.dataText
        )
        viewModel.photoForGalery.observe(viewLifecycleOwner){
            if(it.size<8) {
                for(i in it.indices) {

                    addPhotoToGalery(it, i, kartinki, dats)
                }
            }
            else {
                for (i in 0 until 9) {

                    addPhotoToGalery(it, i, kartinki, dats)
                }
            }
        }
        viewModel.getPhoto()

        binding.toCameraButton.setOnClickListener {
            findNavController().navigate(R.id.action_photoGalery_to_photoCamera)
        }


    }

    private fun addPhotoToGalery(galery: List<Photo>, index: Int, attr: List<AppCompatImageView>, attr_data: List<TextView>) {


        var URI = galery[index].urlPath
        Uri.parse(URI)

        Glide.with(this.requireContext())
            .load(URI)
            .into(attr[index])

        attr_data[index].text = galery[index].data

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
