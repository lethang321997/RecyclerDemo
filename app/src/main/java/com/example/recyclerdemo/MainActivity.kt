package com.example.recyclerdemo


import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.recyclerdemo.adapter.TabLayoutAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_popup.*


class MainActivity : AppCompatActivity() {

    private lateinit var fm1: MyFragment
    private var isOpen: Boolean = false
    private var optionSelected = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initAction()
    }

    private fun initView() {
        supportActionBar?.hide()
        fm1 = MyFragment()
        setViewTabLayout()
    }

    private fun initAction() {
        optionView.setOnClickListener {
            option1.isSelected = false
            option2.isSelected = false
            option3.isSelected = false
            option4.isSelected = false
            option5.isSelected = false
            option6.isSelected = false
            when (optionSelected) {
                1 -> {
                    option1.isSelected = true
                }
                2 -> {
                    option2.isSelected = true
                }
                3 -> {
                    option3.isSelected = true
                }
                4 -> {
                    option4.isSelected = true
                }
                5 -> {
                    option5.isSelected = true
                }
                6 -> {
                    option6.isSelected = true
                }
            }
            popupOption.visibility = if (isOpen) View.GONE else View.VISIBLE
            isOpen = !isOpen
        }
        option1.setOnClickListener {
            setOptionChange(1,R.drawable.if000_staggered_selected,resources.getString(R.string.option1))
        }
        option2.setOnClickListener {
            setOptionChange(2,R.drawable.if000_popup_item_important_selected,resources.getString(R.string.option2))
        }
        option3.setOnClickListener {
            setOptionChange(3,R.drawable.if000_popup_item_recommend_selected,resources.getString(R.string.option3))
        }
        option4.setOnClickListener {
            setOptionChange(4,R.drawable.if000_popup_item_fav_selected,resources.getString(R.string.option4))
        }
        option5.setOnClickListener {
            setOptionChange(5,R.drawable.if000_popup_item_unread_selected,resources.getString(R.string.option5))
        }
        option6.setOnClickListener {
            setOptionChange(6,R.drawable.if000_popup_item_list_selected,resources.getString(R.string.option6))
        }
    }

    private fun setOptionChange(type : Int, image : Int, text : String){
        fm1.initAdapter(type)
        iconOption.setImageResource(image)
        textOption.text = text
        popupOption.visibility = View.GONE
        optionSelected = type
        isOpen = false
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

}



