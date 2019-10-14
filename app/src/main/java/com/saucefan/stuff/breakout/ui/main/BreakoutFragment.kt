package com.saucefan.stuff.breakout.ui.main

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.saucefan.stuff.breakout.R
import timber.log.Timber

class BreakoutFragment : Fragment() {

    companion object {
        fun newInstance() = BreakoutFragment()
    }
        lateinit var breakoutView:BreakoutView
        private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {
         breakoutView =BreakoutView(container!!.context)
        return breakoutView // inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }
   inner class BreakoutView(context: Context) : SurfaceView(context),Runnable{
                  val debug = Timber.asTree()
                  var gameThread = Thread()
                  var mSurfaceHolder:SurfaceHolder =holder
        @Volatile var playing:Boolean =false
                  var paused = true
                  var canvas =Canvas()
                  val paint = Paint()
                  var fps = 0L
        private   var timeThisFrame=0L
                  var startFrameTime = System.currentTimeMillis()
                  var size =Point()
                  var display =getDisplay().getSize(size)
                  var screenX=size.x
                  var screenY =size.y





       override fun run() {
            while (playing) {
                //current time in milli

                if (!paused) {
                    update()
                }

                draw()

                timeThisFrame = System.currentTimeMillis() - startFrameTime
                if (timeThisFrame >= 1) {
                    fps = 1000 / timeThisFrame
                }
            }
        }

        fun update() {

        }

        fun draw() {

            if (mSurfaceHolder.surface.isValid) {
                //lock the canvas
                canvas = mSurfaceHolder.lockCanvas()

                canvas.drawColor(resources.getColor(R.color.colorBackground))

                paint.setColor(resources.getColor(R.color.colorBricks))

                //draw a paddle

                //draw ball

                //draw bricks

                //draw hud

                //draw the rest

                mSurfaceHolder.unlockCanvasAndPost(canvas)

            }
        }

            fun pause(){
                playing=false
                try {
                    gameThread.join()
                } catch (e:InterruptedException) {
                    debug.e(e, "crash at join in pause()")
                }
            }
            fun resume() {
                playing=true
                gameThread =Thread(this)
                gameThread.start()
            }





        override fun onTouchEvent(event: MotionEvent?): Boolean {

            debug.i(event?.action.toString()) ?:0


            when(event!!.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_DOWN ->{
                    return true
                }
                MotionEvent.ACTION_UP ->{
                    return true
                }
            }


            return super.onTouchEvent(event)

        }


    }

    override fun onResume() {
        super.onResume()
        breakoutView.resume()
    }

    override fun onPause() {
        super.onPause()
        breakoutView.pause()
    }
}






