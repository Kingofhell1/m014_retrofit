package com.example.m014_retrofit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.m014_retrofit.databinding.FragmentUserBinding


class UserFragment : Fragment() {


    companion object {
        fun newInstance() = UserFragment()
    }

    private lateinit var binding: FragmentUserBinding
    private val viewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.loadInitialUserInfo()

        // Наблюдение за изменениями данных пользователя
        viewModel.userLiveData.observe(viewLifecycleOwner) { user ->
            user?.let {
                // Загрузка изображения с помощью Glide
                Glide.with(this)
                    .load(user.picture.large)
                    .into(binding.imgPhoto)
            }
        }
        // вывод ошибки в запросе данных
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                 State.Error -> {
                    Toast.makeText(
                        requireContext(),
                        "Ошибка в данных.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {}
            }
        }
    }


    }