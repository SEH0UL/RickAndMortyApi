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
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    // Declaración del objeto de enlace para acceder a los elementos de la interfaz
    private lateinit var binding: ActivityMainBinding
    // Instancia de Retrofit para gestionar las solicitudes HTTP
    private lateinit var retrofit: Retrofit
    // Adaptador para gestionar y mostrar los datos en el RecyclerView
    private lateinit var adapter: RickandMortyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Configuración del binding para enlazar la vista con esta actividad
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Inicialización de Retrofit llamando al método correspondiente
        retrofit = getRetrofit()
        // Configuración inicial de la interfaz de usuario
        initUI()
    }

    private fun initUI() {
        // Configuración del listener para manejar búsquedas desde el SearchView
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Llamada al método de búsqueda usando el texto ingresado
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?) = false
        })

        // Inicialización del adaptador para el RecyclerView
        adapter = RickandMortyAdapter()
        binding.rvCharacterRickMorty.apply {
            setHasFixedSize(true) // Optimización del RecyclerView cuando el tamaño del contenido no cambia
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false) // Configuración de diseño horizontal
            adapter = this@MainActivity.adapter // Asignación del adaptador al RecyclerView
        }
    }

    private fun searchByName(query: String) {
        // Muestra la barra de progreso mientras se realiza la búsqueda
        binding.progressBar.isVisible = true
        // Uso de corutinas para realizar la solicitud de red en un hilo de E/S
        CoroutineScope(Dispatchers.IO).launch {
            // Creación del servicio de la API usando Retrofit
            val apiService = retrofit.create(ApiService::class.java)

            if (query.toIntOrNull() != null) {
                // Caso: búsqueda por ID
                val myResponse = apiService.getRickandMortyDetail(query.toInt().toString())
                if (myResponse.isSuccessful) {
                    val character = myResponse.body()
                    character?.let {
                        // Actualización de la interfaz en el hilo principal con el resultado obtenido
                        runOnUiThread {
                            adapter.updateList(listOf(it)) // Convertimos el único resultado en una lista
                            binding.progressBar.isVisible = false // Oculta la barra de progreso
                        }
                    }
                } else {
                    // Manejo de errores en caso de que la respuesta no sea exitosa
                    runOnUiThread {
                        binding.progressBar.isVisible = false // Oculta la barra de progreso
                        Log.e("Error", "Búsqueda fallida por ID: ${myResponse.message()}")
                    }
                }
            } else {
                // Caso: búsqueda por nombre
                val nameResponse = apiService.searchCharacterByName(query)
                if (nameResponse.isSuccessful) {
                    val characterList = nameResponse.body()?.results ?: emptyList()
                    runOnUiThread {
                        // Actualización del adaptador con los resultados obtenidos
                        adapter.updateList(characterList)
                        binding.progressBar.isVisible = false // Oculta la barra de progreso
                    }
                } else {
                    // Manejo de errores en caso de que la respuesta no sea exitosa
                    runOnUiThread {
                        binding.progressBar.isVisible = false // Oculta la barra de progreso
                        Log.e("Error", "Búsqueda fallida por nombre: ${nameResponse.message()}")
                    }
                }
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        // Configuración e instancia de Retrofit con la URL base de la API y Gson para la conversión
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/") // URL base de la API
            .addConverterFactory(GsonConverterFactory.create()) // Conversor para manejar JSON
            .build()
    }
}
