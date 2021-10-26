package com.batstateu.it331_1st_2122
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView


class ArtistList(private val context: Activity, private val artists: ArrayList<Artist>) : BaseAdapter() {
    // : ArrayAdapter<Artist>(context,R.layout.layout_list_artist, artists) {
    private val mContext: Context

    init {
        mContext = context
    }
    override fun getViewTypeCount(): Int {
        return count
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getCount(): Int {
        return artists.size
    }

    override fun getItem(position: Int): Artist? {
        return artists[position]
    }

    override fun getItemId(position: Int) : Long {
        return 0
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater: LayoutInflater = LayoutInflater.from(mContext)
        val rowMain = inflater.inflate(R.layout.layout_list_artist, parent, false)

        val textViewName: TextView = rowMain.findViewById(R.id.textViewName)
        val textViewGenre: TextView = rowMain.findViewById(R.id.textViewGenre)

        val artist: Artist = artists[position]
        textViewName.text = artist.name
        textViewGenre.text = artist.genre

        return rowMain
    }
    private inner class ViewHolder {

    }
}