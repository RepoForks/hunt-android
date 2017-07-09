package com.ctech.eaty.ui.home.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity.START
import com.ctech.eaty.R
import com.ctech.eaty.base.BaseActivity
import com.ctech.eaty.tracking.FirebaseTrackManager
import com.ctech.eaty.ui.home.navigation.HomeNavigation
import com.ctech.eaty.util.GlideImageLoader
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_home_content.*
import javax.inject.Inject


class HomeActivity : BaseActivity(), HasSupportFragmentInjector {
    override fun getScreenName() = "Home"

    @Inject
    lateinit var trackingManager: FirebaseTrackManager

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var homeNavigation: HomeNavigation

    @Inject
    lateinit var imageLoader: GlideImageLoader

    companion object {

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, HomeActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        var fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)

        if (fragment == null) {
            fragment = HomeFragment.newInstance()
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit()
        }
        trackingManager.trackScreenView(getScreenName())
    }

    private fun setupToolbar() {
        toolbar.setNavigationOnClickListener {
            drawer.openDrawer(START)
        }
        navigation.setNavigationItemSelectedListener {
            homeNavigation.delegate(it.itemId).subscribe()
            false
        }
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    override fun onLowMemory() {
        super.onLowMemory()
        imageLoader.clearMemory()
    }


}
