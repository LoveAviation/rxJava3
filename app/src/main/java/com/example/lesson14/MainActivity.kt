package com.example.lesson14

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lesson14.adapter.FactAdapter
import com.example.lesson14.databinding.ActivityMainBinding
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val viewModel: MainVM by viewModels()
    private lateinit var binding: ActivityMainBinding

    private val disposable = CompositeDisposable()

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainRV.layoutManager = LinearLayoutManager(this)
        viewModel.getFacts(20)

        val lco : LifecycleOwner = this
        scope.launch {
            viewModel.data.observe(lco){ result ->
                binding.mainRV.adapter = FactAdapter(result)
                binding.progressBar.visibility = View.GONE
                binding.mainRV.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}