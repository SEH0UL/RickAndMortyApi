package com.sehoul.rickandmortyapi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.sehoul.rickandmortyapi.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    // VAS POR LA DIAPOSITIVA 42
    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: RickandMortyAdapter


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

        adapter = RickandMortyAdapter()
        binding.rvCharacterRickMorty.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = this@MainActivity.adapter
        }
    }

    private fun searchByName(query: String) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val apiService = retrofit.create(ApiService::class.java)

            // Si query es un número, busca por ID; si es texto, busca por nombre.
            val myResponse: Response<RickandMortyDataResponse> = if (query.toIntOrNull() != null) {
                // Si el query es un número, buscamos por ID
                apiService.getRickandMortyDetail(query.toInt().toString())
            } else {
                // Si no, buscamos por nombre
                apiService.getRickandMortyDetail(query) // Usamos el método adecuado para buscar por nombre
            }

            if (myResponse.isSuccessful) {
                val response = myResponse.body()
                response?.let {
                    runOnUiThread {
                        adapter.updateList(it) // Actualizamos la lista con los personajes encontrados
                        binding.progressBar.isVisible = false
                    }
                }
            } else {
                runOnUiThread {
                    binding.progressBar.isVisible = false
                    Log.e("Error", "Búsqueda fallida: ${myResponse.message()}")
                }
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
