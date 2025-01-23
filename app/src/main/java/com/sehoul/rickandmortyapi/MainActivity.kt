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
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false) // Cambiado a orientación vertical
            adapter = this@MainActivity.adapter
        }
    }

    private fun searchByName(query: String) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val apiService = retrofit.create(ApiService::class.java)

            if (query.toIntOrNull() != null) {
                // Caso: búsqueda por ID
                val myResponse = apiService.getRickandMortyDetail(query.toInt().toString())
                if (myResponse.isSuccessful) {
                    val character = myResponse.body()
                    character?.let {
                        runOnUiThread {
                            adapter.updateList(listOf(it)) // Convertimos el único resultado en una lista
                            binding.progressBar.isVisible = false
                        }
                    }
                } else {
                    runOnUiThread {
                        binding.progressBar.isVisible = false
                        Log.e("Error", "Búsqueda fallida por ID: ${myResponse.message()}")
                    }
                }
            } else {
                // Caso: búsqueda por nombre
                val nameResponse = apiService.searchCharacterByName(query)
                if (nameResponse.isSuccessful) {
                    val characterList = nameResponse.body()?.results ?: emptyList()
                    runOnUiThread {
                        adapter.updateList(characterList) // Actualizamos con la lista de resultados
                        binding.progressBar.isVisible = false
                    }
                } else {
                    runOnUiThread {
                        binding.progressBar.isVisible = false
                        Log.e("Error", "Búsqueda fallida por nombre: ${nameResponse.message()}")
                    }
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
