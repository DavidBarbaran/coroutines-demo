package com.david.barbaran.coroutinesdemo.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.david.barbaran.coroutinesdemo.R
import com.david.barbaran.coroutinesdemo.adapter.UserAdapter
import com.david.barbaran.coroutinesdemo.controller.MainController
import com.david.barbaran.coroutinesdemo.model.User
import com.david.barbaran.coroutinesdemo.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), MainController.View {

    private lateinit var myJob: Job
    private val presenter: MainPresenter by inject()
    private val userAdapter: UserAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        userRecycler.layoutManager = LinearLayoutManager(this)
        userRecycler.adapter = userAdapter
        presenter.controller = this
        myJob = CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                presenter.loadUsers()
            }
        }
    }

    override fun onDestroy() {
        myJob.cancel()
        super.onDestroy()
    }

    override fun onLoadUserSuccessful(list: List<User>) {
        progressBar.visibility = View.GONE
        userAdapter.list = list
        userAdapter.notifyDataSetChanged()
    }

    override fun onLoadUserFailed(message: String) {
        progressBar.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}