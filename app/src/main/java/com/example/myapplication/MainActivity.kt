package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import coil.load
import com.example.myapplication.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        var retrofit = APIClient.getInstance().create(APIInterface::class.java)

        binding.button.setOnClickListener {
            var userName = binding.username.text.toString()
            var password = binding.password.text.toString()
            retrofit.login(Login(userName,password)).enqueue(object : Callback<User>{
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    binding.name.text = response.body()?.username
                    binding.avatar.load(response.body()?.image)
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.d("TAG", "$t")
                }

            })
        }

        binding.buttonProduct.setOnClickListener {
            retrofit.getAllProducts().enqueue(object : Callback<List<Products>>{
                override fun onResponse(
                    call: Call<List<Products>>,
                    response: Response<List<Products>>
                ) {
                    Log.d("TAG", "${response.body()}")
                }

                override fun onFailure(call: Call<List<Products>>, t: Throwable) {
                    Log.d("TAG", "$t")
                }

            })
        }



    }
}