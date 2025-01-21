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
        adapter = RickandMortyAdapter ()
        binding.rvCharacterRickMorty.setHasFixedSize(true)
        binding.rvCharacterRickMorty.layoutManager = LinearLayoutManager(this)
        binding.rvCharacterRickMorty.adapter = adapter

    }

    private fun searchByName(query: String) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: Response<RickandMortyDataResponse> =
                retrofit.create(ApiService::class.java).getRickandMorty(query)
            // evaluando si la respuesta ha tenido éxito.
            if (myResponse.isSuccessful) {
                Log.i("Consulta", "Funciona :)")
                val response: RickandMortyDataResponse? = myResponse.body()
                if (response != null) {
                    Log.i("Cuerpo de la consulta", response.toString())
                    adapter.updateList(response.name)
                    runOnUiThread {
                        binding.progressBar.isVisible = false
                    }

                }
            } else {
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
