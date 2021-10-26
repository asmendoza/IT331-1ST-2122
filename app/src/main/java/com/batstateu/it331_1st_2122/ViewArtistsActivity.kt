package com.batstateu.it331_1st_2122

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import java.lang.IllegalArgumentException
import java.lang.RuntimeException

class ViewArtistsActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    internal lateinit var artistList: ArrayList<Artist>


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_artists)

        //initialize the listview screen
        listView = findViewById(R.id.listViewArtists)
        loadArtists()
    }

    //this will retrieve data from the database server using HTTP/GET
    @RequiresApi(Build.VERSION_CODES.O)
    private fun loadArtists() {
        
        showSimpleProgressDialog(this, "Loading...", "Fetching Json", false)
        
        val stringRequest = StringRequest(Request.Method.GET,
            EndPoints.URL_GET_ARTIST,
                Response.Listener { retrievedStr ->
                    try {
                        Log.d("Debugging: ",retrievedStr)
                        val obj = JSONObject(retrievedStr)
                        if (!obj.getBoolean("error")) {
                            
                            artistList = ArrayList()
                            val array = obj.getJSONArray("artists")

                            for (i in 0 until array.length()) {
                                val objectArtist = array.getJSONObject(i)
                                val artist = Artist(
                                    objectArtist.getString("name"),
                                    objectArtist.getString("genre")
                                )
                                artistList.add(artist)
                                
                            }
                            setupListview()
                            
                        } else {
                            Toast.makeText(applicationContext, obj.getString("message"), Toast.LENGTH_LONG).show()
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }, { volleyError -> Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show() })

        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }
    

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupListview() {
        removeSimpleProgressDialog() //will remove progress dialog
        listView.adapter = ArtistList(this, artistList)
    }

    //loading dialog
    companion object{
        //@SuppressLint("StaticFieldLeak")
        private var mProgressDialog: ProgressDialog? = null
        
        @RequiresApi(Build.VERSION_CODES.O)
        fun removeSimpleProgressDialog() {
            try {
                if (mProgressDialog != null) {
                    if (mProgressDialog!!.isShowing) {
                        mProgressDialog!!.dismiss()
                        mProgressDialog = null
                    }
                }
            } catch (ie: IllegalArgumentException) {
                ie.printStackTrace()
            } catch (re: RuntimeException) {
                re.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        
        fun showSimpleProgressDialog(
            context: Context, title: String,
            msg: String, isCancelable: Boolean
        ){
            try {
                if (mProgressDialog == null) {
                    mProgressDialog = ProgressDialog.show(context, title, msg)
                    mProgressDialog!!.setCancelable(isCancelable)
                }
                
                if (!mProgressDialog!!.isShowing) {
                    mProgressDialog!!.show()
                }
            } catch (ie: IllegalArgumentException) {
                ie.printStackTrace()
            } catch (re: RuntimeException) {
                re.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}