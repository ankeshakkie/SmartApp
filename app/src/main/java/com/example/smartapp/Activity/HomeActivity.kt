package com.example.smartapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartapp.Adapter.CatFactsAdapter
import com.example.smartapp.DataClass.CatFactModel
import com.example.smartapp.R
import com.example.smartapp.Request.Catsdata
import com.example.smartapp.Response.AllDataResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeActivity : AppCompatActivity()
{
   private var basic_url = "https://cat-fact.herokuapp.com/"
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getCatApi()
    }

    private fun getCatApi()
    {
        var mCatFactList = ArrayList<CatFactModel>()
        mCatFactList.clear()
        var retrofit = Retrofit.Builder()
            .baseUrl(basic_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var api_service = retrofit.create(Catsdata::class.java)
        var call_data = api_service.getCatFacts()
        call_data.enqueue(object : Callback<AllDataResponse>
        {
            override fun onFailure(call: Call<AllDataResponse>, t: Throwable)
            {
                progress_bar.visibility = View.GONE
                Toast.makeText(this@HomeActivity,"Server Error",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<AllDataResponse>, response: Response<AllDataResponse>)
            {
                if (response.isSuccessful)
                {
                    progress_bar.visibility = View.GONE
                  //  Toast.makeText(this@MainActivity,"Success",Toast.LENGTH_SHORT).show()
                    var response_result = response.body()
                    var data_list = response_result!!.mCatFactData
                    for (i in 0 until data_list.size)
                    {
                        var text = data_list.get(i).mText.toString()
                        Log.d("MainActivityData",text)
                        var id = data_list.get(i).mId.toString()
                        var user_id = data_list.get(i).mUserData.mUserId.toString()
                        var first_name = data_list.get(i).mUserData.mNameData.mFirst.toString()
                        var last_name = data_list.get(i).mUserData.mNameData.mLast.toString()
                        var upvotes = data_list.get(i).mUpvotes.toString()

                        var mModeldata = CatFactModel(id,user_id,first_name,last_name,text,upvotes)
                        mCatFactList.add(mModeldata)
                    }

                    cat_facts_recycler.layoutManager = LinearLayoutManager(this@HomeActivity)
                    cat_facts_recycler.adapter = CatFactsAdapter(this@HomeActivity,mCatFactList)
                }
                else
                {
                    progress_bar.visibility = View.GONE
                    Toast.makeText(this@HomeActivity,"Something went wrong",Toast.LENGTH_SHORT).show()
                }
            }

        })
    }
}
