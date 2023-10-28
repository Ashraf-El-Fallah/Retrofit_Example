package com.af.anotherexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.af.anotherexample.adapter.MyAdapter
import com.af.anotherexample.model.Post
import com.af.anotherexample.repository.Repository
import com.af.retrofit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    //    private lateinit var todoAdapter: TodoAdapter
//    val Tag = "Main Activity"
    private lateinit var viewModel: MainViewModel
    private val adapter by lazy { MyAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //philip
        setUpRecyclerView()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

//        viewModel.getCustomPost(5, "id", "desc")
//        val myPost = Post("Android Developer", 2, "Ashraf", 23)
        viewModel.getPost("111222")
        viewModel.myResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                //response.body()?.let { adapter.setData(it) }
                Log.d("Main", response.body().toString())
                Log.d("Main", response.code().toString())
                Log.d("Main", response.headers().toString())
            } else {
                Toast.makeText(this, response.code(), Toast.LENGTH_LONG).show()
            }
        })

    }

    private fun setUpRecyclerView() {
        binding.rv.adapter = adapter
        binding.rv.layoutManager = LinearLayoutManager(this)
    }


//        val options: HashMap<String, String> = HashMap()
//        options["_sort"] = "id"
//        options["_order"] = "desc"
//
//        binding.button.setOnClickListener {
//            viewModel.getCustomPost2(binding.editTextNumber.text.toString().toInt(), options)
//            viewModel.myResponse4.observe(this, Observer { response ->
//                if (response.isSuccessful) {
//                    binding.textView.text = response.body().toString()
//                    response.body()?.forEach {
//                        Log.d("Response", it.userId.toString())
//                        Log.d("Response", it.id.toString())
//                        Log.d("Response", it.title)
//                        Log.d("Response", it.body)
//                        Log.d("Response", "--------")
//
//                    }
//                } else {
//                    binding.textView.text = response.code().toString()
//                }
//            })
//        }


    //philip example

//        lifecycleScope.launch {
//            binding.progressBar.isVisible = true
//
//            val response = try {
//                RetrofitInstance.api.getTodos()
//            } catch (e: IOException) {
//                Log.e(Tag, "IOException , you might not have internet connection")
//                binding.progressBar.isVisible = false
//                return@launch
//            } catch (e: retrofit2.HttpException) {
//                Log.e(Tag, "HttpException, unexpected response")
//                binding.progressBar.isVisible = false
//                return@launch
//            }
//
//            if (response.isSuccessful && response.body() != null) {
//                todoAdapter.todos = response.body()!!
//            } else {
//                Log.e(Tag, "Response not successful")
//            }
//            binding.progressBar.isVisible = false
//        }

//    }

//    private fun setUpRecyclerView() = binding.rvTodos.apply {
//        todoAdapter = TodoAdapter()
//        adapter = todoAdapter
//        layoutManager = LinearLayoutManager(this@MainActivity)
//    }
}
