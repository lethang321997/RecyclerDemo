package com.example.recyclerdemo


import android.graphics.PorterDuff
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.recyclerdemo.adapter.DropDownListAdapter
import com.example.recyclerdemo.adapter.TabLayoutAdapter
import com.example.recyclerdemo.model.DropDownList
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.custom_dropdown.*
import kotlinx.android.synthetic.main.custom_popup.*


class MainActivity : AppCompatActivity() {

    companion object {
        const val TEXT_OPTION_1 = "カテゴリ表示"
        const val TEXT_OPTION_2 = "重要"
        const val TEXT_OPTION_3 = "おすすめ"
        const val TEXT_OPTION_4 = "お気に入り"
        const val TEXT_OPTION_5 = "未読"
        const val TEXT_OPTION_6 = "一覧で見る"
    }

    private lateinit var fm1: MyFragment
    private var optionSelected = 1
    private var dropDownArray: ArrayList<DropDownList> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initDropDownList()
        initAction()
    }

    private fun initView() {
        supportActionBar?.hide()
        fm1 = MyFragment()
        setViewTabLayout()
    }

    private fun initAction() {
        optionView.setOnClickListener {
            if (dropdown_list.visibility == View.INVISIBLE) {
                dropdown_list.visibility = View.VISIBLE
                imageViewList.visibility = View.VISIBLE
            } else {
                dropdown_list.visibility = View.INVISIBLE
                imageViewList.visibility = View.INVISIBLE
            }
        }

    }


    private fun setViewTabLayout() {
        val adapter = TabLayoutAdapter(supportFragmentManager)
        adapter.addFragment(fm1, resources.getString(R.string.tab1))
        adapter.addFragment(MyFragment(), resources.getString(R.string.tab2))
        adapter.addFragment(MyFragment(), resources.getString(R.string.tab3))
        adapter.addFragment(MyFragment(), resources.getString(R.string.tab4))
        view_pager.adapter = adapter
        tabs.setupWithViewPager(view_pager)
        tabs.getTabAt(0)?.setIcon(R.drawable.ic_home_black)
        tabs.getTabAt(1)?.setIcon(R.drawable.ic_phone_bar_black)
        tabs.getTabAt(2)?.setIcon(R.drawable.ic_notification_black)
        tabs.getTabAt(3)?.setIcon(R.drawable.ic_person_black)
        val tabIconColor = ContextCompat.getColor(applicationContext, R.color.tabSelected)
        tabs.getTabAt(0)?.icon?.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)
        tabs.setOnTabSelectedListener(
            object : TabLayout.ViewPagerOnTabSelectedListener(view_pager) {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    super.onTabSelected(tab)
                    tab.icon!!.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {
                    super.onTabUnselected(tab)
                    val colorUnselectedIcon =
                        ContextCompat.getColor(applicationContext, R.color.tabUnselected)
                    tab.icon!!.setColorFilter(colorUnselectedIcon, PorterDuff.Mode.SRC_IN)
                }

                override fun onTabReselected(tab: TabLayout.Tab) {
                }
            }
        )
    }

    private fun initDropDownList() {
        dropDownArray.add(DropDownList(R.drawable.popup_item_1, TEXT_OPTION_1))
        dropDownArray.add(DropDownList(R.drawable.popup_item_2, TEXT_OPTION_2))
        dropDownArray.add(DropDownList(R.drawable.popup_item_3, TEXT_OPTION_3))
        dropDownArray.add(DropDownList(R.drawable.popup_item_4, TEXT_OPTION_4))
        dropDownArray.add(DropDownList(R.drawable.popup_item_5, TEXT_OPTION_5))
        dropDownArray.add(DropDownList(R.drawable.popup_item_6, TEXT_OPTION_6))
        val dropDownAdapter = DropDownListAdapter(applicationContext, dropDownArray)
        dropdown_list.adapter = dropDownAdapter
        dropdown_list.choiceMode = ListView.CHOICE_MODE_SINGLE
        dropdown_list.setItemChecked(0,true)
        dropdown_list.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                dropdown_list.visibility = View.INVISIBLE
                imageViewList.visibility = View.INVISIBLE
                when (position) {
                    0 -> {
                        setOptionChange(
                            1,
                            R.drawable.if000_staggered_selected,
                            resources.getString(R.string.option1)
                        )
                    }
                    1 -> {
                        setOptionChange(
                            2,
                            R.drawable.if000_popup_item_important_selected,
                            resources.getString(R.string.option2)
                        )
                    }
                    2 -> {
                        setOptionChange(
                            3,
                            R.drawable.if000_popup_item_recommend_selected,
                            resources.getString(R.string.option3)
                        )
                    }
                    3 -> {
                        setOptionChange(
                            4,
                            R.drawable.if000_popup_item_fav_selected,
                            resources.getString(R.string.option4)
                        )
                    }
                    4 -> {
                        setOptionChange(
                            5,
                            R.drawable.if000_popup_item_unread_selected,
                            resources.getString(R.string.option5)
                        )
                    }
                    5 -> {
                        setOptionChange(
                            6,
                            R.drawable.if000_popup_item_list_selected,
                            resources.getString(R.string.option6)
                        )
                    }
                }
            }
    }

    private fun setOptionChange(type: Int, image: Int, text: String) {
        fm1.initAdapter(type)
        iconOption.setImageResource(image)
        textOption.text = text
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("my",dropdown_list.selectedItemPosition)
    }
}



