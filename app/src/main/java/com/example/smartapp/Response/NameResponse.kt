package com.example.smartapp.Response

import com.google.gson.annotations.SerializedName

class NameResponse
{
    @SerializedName("first")
    public var  mFirst:String? = null

    @SerializedName("last")
    public var  mLast:String? = null
}