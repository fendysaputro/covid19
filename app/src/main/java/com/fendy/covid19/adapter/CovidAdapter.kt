package com.fendy.covid19.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fendy.covid19.R
import com.fendy.covid19.databinding.CustomListCountryAdapterBinding
import com.fendy.covid19.network.api.response.Attributes
import com.fendy.covid19.network.api.response.ResponseCovid
import kotlinx.android.synthetic.main.custom_list_country_adapter.view.*


/**
 * Created by Fendy Saputro on 13/12/2020.
 * vidis194@gmail.com
 */
class CovidAdapter (private val covidList: List<ResponseCovid>, private val listener: (ResponseCovid) -> Unit) :
       RecyclerView.Adapter<CovidAdapter.CovidViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CovidAdapter.CovidViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_list_country_adapter, parent, false)
        return CovidViewHolder(view)
    }

    override fun onBindViewHolder(holder: CovidAdapter.CovidViewHolder, position: Int) {
        holder.bind(covidList[position], listener)
    }

    override fun getItemCount() = covidList.size

    inner class CovidViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: ResponseCovid, listener: (ResponseCovid) -> Unit) {
            itemView.tvListView.text = item.attributes.Provinsi
            itemView.confirmedCase.text = item.attributes.KasusPosi.toString()
            itemView.deathsCase.text = item.attributes.KasusMeni.toString()
            itemView.getWellCase.text = item.attributes.KasusSemb.toString()

            itemView.setOnClickListener {
                listener(item)
            }
        }
    }

}