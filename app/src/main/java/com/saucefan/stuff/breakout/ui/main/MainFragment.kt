package com.saucefan.stuff.breakout.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.SurfaceView
import android.view.View
import android.view.ViewGroup
import com.saucefan.stuff.breakout.R

class BreakoutFragment : Fragment() {

    companion object {
        fun newInstance() = BreakoutFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {
        val breakoutView =BreakoutView(container!!.context)
        return breakoutView // inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }



    class BreakoutView(context: Context) : SurfaceView(context),Runnable{
        val gameThread = Thread()



        override fun run() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }


    }


}
