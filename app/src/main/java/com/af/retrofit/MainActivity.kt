package com.af.retrofit

import android.net.http.HttpException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.af.retrofit.databinding.ActivityMainBinding
import com.af.retrofit.databinding.ItemTodoBinding
import kotlinx.coroutines.launch
import okio.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter
    val Tag = "Main Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()

        lifecycleScope.launch {
            binding.progressBar.isVisible = true

            val response = try {
                RetrofitInstance.api.getTodos()
            } catch (e: IOException) {
                Log.e(Tag, "IOException , you might not have internet connection")
                binding.progressBar.isVisible = false
                return@launch
            } catch (e: retrofit2.HttpException) {
                Log.e(Tag, "HttpException, unexpected response")
                binding.progressBar.isVisible = false
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {
                todoAdapter.todos = response.body()!!
            } else {
                Log.e(Tag, "Response not successful")
            }
            binding.progressBar.isVisible = false
        }


    }

    private fun setUpRecyclerView() = binding.rvTodos.apply {
        todoAdapter = TodoAdapter()
        adapter = todoAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }
}
