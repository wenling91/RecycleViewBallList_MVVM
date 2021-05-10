package com.example.recycleviewballlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleviewballlist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        val layoutManager = LinearLayoutManager(this)
        //val layoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
        binding.recycleView.layoutManager = layoutManager
        val adapter = BallAdapter()  //customized your own adapter
        binding.recycleView.adapter = adapter
        binding.recycleView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        viewModel.ballList.observe(this, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
    }

}
