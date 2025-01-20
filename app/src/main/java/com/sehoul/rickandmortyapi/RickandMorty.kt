package com.sehoul.rickandmortyapi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.sehoul.rickandmortyapi.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RickandMorty : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        initUI()
    }

    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?) = false
        })
    }

    private fun searchByName(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: Response<RickandMortyDataResponse> =
                retrofit.create(ApiService::class.java).getRickandMorty(query)
            // evaluando si la respuesta ha tenido éxito.
            if (myResponse.isSuccessful) {
                Log.i("Consulta", "Funciona :)")
            } else {
                Log.i("Consulta", "No funciona :(")
                Log.i("Consulta", "No funciona :(")
            }

        }
    }



    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
