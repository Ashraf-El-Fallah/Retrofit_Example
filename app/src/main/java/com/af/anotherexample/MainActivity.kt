package com.af.anotherexample
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.af.anotherexample.repository.Repository
import com.af.retrofit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
//    private lateinit var todoAdapter: TodoAdapter
//    val Tag = "Main Activity"
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setUpRecyclerView()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory)[MainViewModel::class.java]

        binding.button.setOnClickListener {
            viewModel.getPost2(binding.editTextNumber.text.toString().toInt())
            viewModel.myResponse2.observe(this, Observer {response->
                if(response.isSuccessful) {
                    binding.textView.text=response.body().toString()
                }else{
                    binding.textView.text=response.code().toString()
                }

            })


        }




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

    }

//    private fun setUpRecyclerView() = binding.rvTodos.apply {
//        todoAdapter = TodoAdapter()
//        adapter = todoAdapter
//        layoutManager = LinearLayoutManager(this@MainActivity)
//    }
}
