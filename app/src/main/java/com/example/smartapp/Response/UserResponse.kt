package com.example.smartapp.Response

import com.google.gson.annotations.SerializedName

class UserResponse
{
    @SerializedName("_id")
    public var  mUserId:String? = null

    @SerializedName("name")
    public var  mNameData:NameResponse = NameResponse()
}