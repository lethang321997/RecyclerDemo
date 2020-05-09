package com.example.recyclerdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.recyclerdemo.adapter.RecyclerAdapter
import com.example.recyclerdemo.model.ItemList
import kotlinx.android.synthetic.main.fragment_my.*
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class MyFragment : Fragment() {
    private var addArray = true
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my, container, false)
    }

    private val arrayList: ArrayList<ItemList> = ArrayList()
    private val arrayList2 : ArrayList<ItemList> = ArrayList()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initAdapter()
    }

    private fun initData() {
        if(addArray){
            arrayList.add(
                ItemList(
                    TITLE1,
                    DATE1,
                    IMAGE1,
                    isStar = false,
                    isWebView = false,
                    http = null
                )
            )
            arrayList.add(
                ItemList(
                    TITLE2,
                    DATE2,
                    IMAGE2,
                    isStar = false,
                    isWebView = true,
                    http = HTTP2
                )
            )

            arrayList.add(
                ItemList(
                    TITLE3,
                    DATE3,
                    IMAGE3,
                    isStar = false,
                    isWebView = true,
                    http = HTTP3
                )
            )
            arrayList.add(
                ItemList(
                    TITLE4,
                    DATE4,
                    IMAGE4,
                    isStar = false,
                    isWebView = false,
                    http = null
                )
            )
            arrayList.add(
                ItemList(
                    TITLE5,
                    DATE5,
                    IMAGE5,
                    isStar = false,
                    isWebView = false,
                    http = null
                )
            )
            arrayList.add(
                ItemList(
                    TITLE6,
                    DATE6,
                    IMAGE6,
                    isStar = false,
                    isWebView = true,
                    http = HTTP6
                )
            )
            arrayList2.addAll(arrayList)
            addArray = false
        }
    }

    fun initAdapter(type: Int = 1) {
        when (type) {
            1 -> {
                setRecyclerView(arrayList2)
            }
            2 -> {
                arrayList.sortBy { it.title }
                setRecyclerView(arrayList)
            }
            3 -> {
                arrayList.sortBy { it.date }
                setRecyclerView(arrayList)
            }
            4 -> {
                arrayList.sortByDescending { it.isStar }
                setRecyclerView(arrayList)
            }
            5 -> {
                arrayList.sortByDescending { it.isWebView }
                setRecyclerView(arrayList)
            }
            6 -> {
                my_recycler_view.setPadding(
                    MARGIN_ZERO,
                    MARGIN_ZERO,
                    MARGIN_ZERO,
                    MARGIN_ZERO
                )
                val layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                my_recycler_view.layoutManager = layoutManager
                val recyclerAdapter = context?.let { RecyclerAdapter(it, arrayList, 6) }
                my_recycler_view.adapter = recyclerAdapter
            }
        }
    }

    private fun setRecyclerView(arrayList: ArrayList<ItemList>) {
        my_recycler_view.setPadding(
            MARGIN_HORIZONTAL,
            MARGIN_ZERO,
            MARGIN_HORIZONTAL,
            MARGIN_ZERO
        )
        val layoutManager =
            StaggeredGridLayoutManager(SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL)
        my_recycler_view.layoutManager = layoutManager
        val recyclerAdapter = context?.let { RecyclerAdapter(it, arrayList, 0) }
        my_recycler_view.adapter = recyclerAdapter
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MyFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MyFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

        const val TITLE1 = "Heart-shaped Keao Yai in Rawai"
        const val TITLE2 = "Rapeseed Summer Field"
        const val TITLE3 = "Bluebell Landscape"
        const val TITLE4 = "Ang Thong Marine National Park"
        const val TITLE5 = "The Yi Peng Festival in Chiang Mai"
        const val TITLE6 = "Mountainous Landscape In Iceland"
        const val IMAGE1 = "https://c2.staticflickr.com/4/3721/33130667832_42d4a96d97_k.jpg"
        const val IMAGE2 =
            "https://i1.wp.com/www.fotoviva.co.uk/wp-content/uploads/cm/data/prods/rapeseed-field.jpg?w=500&ssl=1"
        const val IMAGE3 =
            "https://i0.wp.com/www.fotoviva.co.uk/wp-content/uploads/cm/data/prods/Bluebell-Wood-Landscape.jpg?w=500&ssl=1"
        const val IMAGE4 = "https://c2.staticflickr.com/4/3867/32408020133_f4f6485bc3_h.jpg"
        const val IMAGE5 = "https://c2.staticflickr.com/4/3836/32903716610_9022bc8cd4_o.jpg"
        const val IMAGE6 =
            "https://i2.wp.com/www.fotoviva.co.uk/wp-content/uploads/cm/data/prods/Iceland.jpg?w=500&ssl=1"
        val DATE1: LocalDate = LocalDate.parse("2018-02-03")
        val DATE2: LocalDate = LocalDate.parse("2018-05-23")
        val DATE3: LocalDate = LocalDate.parse("2017-12-15")
        val DATE4: LocalDate = LocalDate.parse("2019-11-20")
        val DATE5: LocalDate = LocalDate.parse("2017-08-16")
        val DATE6: LocalDate = LocalDate.parse("2018-06-26")
        const val HTTP2 =
            "https://www.fotoviva.co.uk/product/rapeseed-summer-field/"
        const val HTTP3 =
            "https://www.fotoviva.co.uk/product/Bluebell-Landscape"
        const val HTTP6 =
            "https://www.fotoviva.co.uk/product/icelandic-view/"
        const val MARGIN_HORIZONTAL = 25
        const val MARGIN_ZERO = 0
        const val SPAN_COUNT = 2
    }
}
