package com.example.smartapp.Response

import com.google.gson.annotations.SerializedName

class CatsFactResponse
{
    @SerializedName("id")
    public var  mId:String? = null

    @SerializedName("text")
    public var  mText:String? = null

    @SerializedName("type")
    public var  mType:String? = null

    @SerializedName("upvotes")
    public var  mUpvotes:String? = null

    @SerializedName("userUpvoted")
    public var  mUserUpvoted:String? = null

    @SerializedName("user")
    public var  mUserData:UserResponse = UserResponse()
}