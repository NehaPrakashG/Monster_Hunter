package com.example.monsterhunter.Views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.monsterhunter.Model.ArmorDataResponse
import com.example.monsterhunter.R

class ArmorDetailFragment : Fragment() {
    var details = ArmorDataResponse();

    fun newInstance(): ArmorDetailFragment {
        return ArmorDetailFragment()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(getString(R.string.armor), details)
    }

    override fun onCreate(savedInstanceState: Bundle?) {  /*saving and retriving bundle data onpause */
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            details = savedInstanceState.getParcelable(getString(R.string.armor))!!
        } else {
            details = arguments?.getParcelable(getString(R.string.armor))!!

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.armor_details, container, false)
        initUi(rootView)
        return rootView
    }

    private fun initUi(rootView: View) { /*initializing and setting data to views*/
        val name_tv = rootView.findViewById<TextView>(R.id.name_tv)
        val rank_tv = rootView.findViewById<TextView>(R.id.rank_tv)
        val female_tv = rootView.findViewById<TextView>(R.id.female_tv)
        val male_tv = rootView.findViewById<TextView>(R.id.male_tv)
        val male_imgv = rootView.findViewById<ImageView>(R.id.male_imgv)
        val female_imgv = rootView.findViewById<ImageView>(R.id.female_imgv)
        val base_tv = rootView.findViewById<TextView>(R.id.base_tv)
        val max_tv = rootView.findViewById<TextView>(R.id.max_tv)
        val augmented_tv = rootView.findViewById<TextView>(R.id.augmented_tv)

        name_tv.setText(details.name)
        rank_tv.setText(getString(R.string.rank)+" "+ details.rank.toString().toUpperCase())
        base_tv.setText(details.defense?.base.toString())
        max_tv.setText(details.defense?.max.toString())
        augmented_tv.setText(details.defense?.augmented.toString())
        female_tv.setText(getString(R.string.female))
        male_tv.setText(getString(R.string.male))



        if (details.assets?.imageFemale != null && details.assets?.imageMale != null) {
            Glide.with(this).load(details.assets?.imageMale).into(male_imgv);
            Glide.with(this).load(details.assets?.imageFemale).into(female_imgv);
        } else {
            female_tv.visibility = View.GONE
            male_tv.setText(getString(R.string.no_img))
        }
    }

}