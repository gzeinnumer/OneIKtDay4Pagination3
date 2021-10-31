package com.gzeinnumer.oneiktday4pagination3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gzeinnumer.oneiktday4pagination3.adapter.RVAdapter
import com.gzeinnumer.oneiktday4pagination3.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //todo 13
        initRV()

        //todo 17
        initVM()
    }

    //todo 14
    lateinit var rvAdapter: RVAdapter

    private fun initRV() {
        binding.rv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration  = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            rvAdapter = RVAdapter()
            adapter = rvAdapter
        }
    }
    //end todo 14

    //todo 18
    private fun initVM() {
        val viewModel = ViewModelProvider(this).get(MainVM::class.java)
        lifecycleScope.launchWhenCreated {
            viewModel.getListData().collectLatest {
                rvAdapter.submitData(it)
            }
        }
    }
    //end todo 18

}