package com.example.smartapp.Adapter

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.smartapp.DataClass.CatFactModel
import com.example.smartapp.R
import kotlinx.android.synthetic.main.cat_fat_list_item.view.*


class CatFactsAdapter(var mContext:Context,var mDataList:ArrayList<CatFactModel>) : RecyclerView.Adapter<CatFactsAdapter.CatFactsHolder>()
{
   class CatFactsHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
   {
       var first_name = itemView.first_name_txt
       var last_name = itemView.last_name_txt
       var parent_cardview = itemView.parent_card
       var right_img_arrow = itemView.right_arrow_img
       var expand_layout = itemView.expand_layout
       var text = itemView.text_txt
       var upvotes = itemView.upvotes_txt
       var down_arrow = itemView.down_arrow_img
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatFactsHolder
    {
       val view = LayoutInflater.from(mContext).inflate(R.layout.cat_fat_list_item,parent,false)
        return CatFactsHolder(view)
    }

    override fun getItemCount(): Int
    {
        return mDataList.size
    }

    override fun onBindViewHolder(holder: CatFactsHolder, position: Int)
    {
       val catmodelData:CatFactModel = mDataList.get(position)
       holder.first_name.setText(catmodelData.first_name)
       holder.last_name.setText(catmodelData.last_name)
       holder.text.setText(catmodelData.data_text)
       holder.upvotes.setText(catmodelData.upvotes)
       holder.right_img_arrow.setOnClickListener {
         holder.expand_layout.visibility = View.VISIBLE
           holder.down_arrow.visibility = View.VISIBLE
           holder.right_img_arrow.visibility = View.GONE
       }
        holder.down_arrow.setOnClickListener {
            holder.expand_layout.visibility = View.GONE
            holder.right_img_arrow.visibility = View.VISIBLE
            holder.down_arrow.visibility = View.GONE
        }

        holder.parent_cardview.setOnClickListener {
            var dialog = Dialog(mContext)
            dialog.setContentView(R.layout.dialog_cat_detail)
            var user_id:TextView = dialog.findViewById(R.id.user_id_value)
            user_id.setText(catmodelData.user_id)
            var first_name:TextView = dialog.findViewById(R.id.first_name_value)
            first_name.setText(catmodelData.first_name)
            var last_name:TextView = dialog.findViewById(R.id.last_name_value)
            last_name.setText(catmodelData.last_name)
            var description:TextView = dialog.findViewById(R.id.desc_value)
            description.setText(catmodelData.data_text)
            var upvotes:TextView = dialog.findViewById(R.id.upvotes_value)
            upvotes.setText(catmodelData.upvotes)
            var close_img:ImageView = dialog.findViewById(R.id.close_img)
            close_img.setOnClickListener {
                dialog.dismiss()
            }
            dialog.setCancelable(false)
            val param = WindowManager.LayoutParams()
            param.width = WindowManager.LayoutParams.MATCH_PARENT
            param.height = WindowManager.LayoutParams.WRAP_CONTENT

            dialog.window!!.attributes = param
            dialog.window!!.attributes.windowAnimations = R.style.Animation
            dialog.show()

        }

    }
}