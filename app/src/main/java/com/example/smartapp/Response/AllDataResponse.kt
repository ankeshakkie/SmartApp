package com.example.smartapp.Response

import com.google.gson.annotations.SerializedName

class AllDataResponse
{
    @SerializedName("all")
    public var  mCatFactData:ArrayList<CatsFactResponse> = ArrayList()
}