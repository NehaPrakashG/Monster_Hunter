package com.example.monsterhunter.Views

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.monsterhunter.Interface.IOnstateChanged
import com.example.monsterhunter.Interface.IRecyclerV_ItemClick
import com.example.monsterhunter.Interface.IarmView
import com.example.monsterhunter.Model.ArmorDataResponse
import com.example.monsterhunter.Presenter.armourPresenter
import com.example.monsterhunter.R
import com.example.monsterhunter.adapter.RecyclerviewAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), IarmView.View, IRecyclerV_ItemClick {
    private lateinit var mainPresenter: armourPresenter
    lateinit var recyclerviewAdapter: RecyclerviewAdapter
    var onActivityStateChanged: IOnstateChanged? = null

    val ArmResponseList: ArrayList<ArmorDataResponse> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPresenter = armourPresenter(this)
        mainPresenter.requestDataFromServer()
        initUI();
    }


    private fun initUI() {
        recyclerviewAdapter = RecyclerviewAdapter(baseContext, ArmResponseList, ArmResponseList, this)
        onActivityStateChanged = recyclerviewAdapter.registerActivityState()

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = recyclerviewAdapter

        search_filter_sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                recyclerviewAdapter.filter.filter(newText)
                return false
            }
        })
    }

    override fun showProgress() {
        pb_load.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pb_load.visibility = View.GONE
    }

    override fun setDataToRecyclerView(armList: ArrayList<ArmorDataResponse>) {
        ArmResponseList.addAll(armList)
        recyclerviewAdapter.notifyDataSetChanged()
    }

    override fun onCellClickListener(position: Int?) {
        Toast.makeText(this, position.toString(), Toast.LENGTH_SHORT).show()
        val bundle = Bundle()
        if (position != null) {
            if (position > 0) {
                bundle.putParcelable(getString(R.string.armor), ArmResponseList.get(position - 1))
            }  /*Id starts from 1 and position from 0*/
        }
        CallDetailsFragment(bundle) /*On click of item opens fragment*/
    }

    private fun CallDetailsFragment(bundle: Bundle) {
        val setupFragment = ArmorDetailFragment()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        setupFragment.arguments = bundle
        transaction.replace(R.id.placeholder, setupFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onPause() {
        onActivityStateChanged?.onPaused()
        super.onPause()
    }

    override fun onResume() {
        onActivityStateChanged?.onResumed()
        super.onResume()
    }

    override fun onBackPressed() {
        val fragments = supportFragmentManager.backStackEntryCount
        if (fragments == 1) {
            super.onBackPressed()
        } else if (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStack()
        } else {
            AlertDialog.Builder(this).setMessage(getString(R.string.exit_message_alert))
                .setCancelable(false)
                .setPositiveButton(
                    getString(R.string.yes),
                    DialogInterface.OnClickListener { dialog, id -> super.onBackPressed() })
                .setNegativeButton(getString(R.string.no), null)
                .show()
        }
    }
}

