package com.fendy.covid19.activity

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.fendy.covid19.R
import com.fendy.covid19.adapter.CovidAdapter
import com.fendy.covid19.databinding.ActivityCovidBinding
import com.fendy.covid19.fragment.CovidViewModel
import com.fendy.covid19.network.api.repository.ResponseRepository
import com.fendy.covid19.network.api.response.Attributes
import com.fendy.covid19.network.api.response.ResponseCovid

class CovidActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var binding: ActivityCovidBinding
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var rvDataCovid: RecyclerView
    private lateinit var defaultToolbar: Toolbar
    private lateinit var tvTitle: TextView
    private lateinit var progressBar: ProgressBar

    private lateinit var covidViewModel: CovidViewModel
    private lateinit var covidAdapter: CovidAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCovidBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        setupToolbar()
        initialize()
        getData()
        onGetDataSuccess()
        onGetDataCovidFailed()
    }

    private fun setupToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.default_toolbar)
        tvTitle.text = "Covid 19"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar?.setNavigationOnClickListener { onBackPressed() }
    }

    private fun initView() {
        swipeRefresh = binding.swiperefresh
        rvDataCovid = binding.rvDataCovid
        defaultToolbar = binding.defaultToolbar
        tvTitle = binding.tvTitleToolbar
        progressBar = binding.progressBar
    }

    private fun initialize() {
        val covidRepository = ResponseRepository()
        covidViewModel = CovidViewModel(covidRepository, this)
    }

    fun Context.isNetworkAvailable(): Boolean {
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val capability = cm.getNetworkCapabilities(cm.activeNetwork) ?: return false
            return capability.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) or capability.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
        } else {
            val netInfo = cm.activeNetworkInfo
            return netInfo != null && netInfo.isConnectedOrConnecting
        }
    }

    private fun getData() {
        if (this.isNetworkAvailable()) {
            progressBar.visibility = View.GONE
            covidViewModel.getDataCovid()
        } else {
            Toast.makeText(this, "Silahkan cek koneksi anda", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onGetDataSuccess() {
        covidViewModel.onGetDataCovidSuccess.observe(this, {
            onGetDataLoaded(listOf(it!!))
        })
    }

    private fun onGetDataLoaded(dataCovid: List<ResponseCovid>) {
        rvDataCovid.visibility = View.VISIBLE
        swipeRefresh.isRefreshing = false
        progressBar.visibility = View.GONE
        covidAdapter = CovidAdapter(dataCovid) {

        }
        rvDataCovid.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@CovidActivity)
            adapter = covidAdapter
        }
    }

    private fun onGetDataCovidFailed() {
        covidViewModel.onGetDataCovidFailed.observe(this, { data ->
            data.map {
                onGetDataFailed(it.value, it.key)
            }
        })
    }

    private fun onGetDataFailed(message: String?, error: Int) {

    }

    override fun onRefresh() {
        TODO("Not yet implemented")
    }
}