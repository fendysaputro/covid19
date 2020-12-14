package com.fendy.covid19.network.api.response

import com.google.gson.annotations.SerializedName


/**
 * Created by Fendy Saputro on 12/12/2020.
 * vidis194@gmail.com
 */

data class ResponseCovid(

        @SerializedName("attributes")
        var attributes : Attributes
)

data class Attributes (
        @SerializedName("FID")
        var FID : Int,

        @SerializedName("Kode_Provi")
        var KodeProvi : Int,

        @SerializedName("Provinsi")
        var Provinsi : String,

        @SerializedName("Kasus_Posi")
        var KasusPosi : Int,

        @SerializedName("Kasus_Semb")
        var KasusSemb : Int,

        @SerializedName("Kasus_Meni")
        var KasusMeni : Int
)