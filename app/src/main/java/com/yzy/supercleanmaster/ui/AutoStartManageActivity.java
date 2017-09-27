package com.yzy.supercleanmaster.ui;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.base.BaseSwipeBackActivity;
import com.yzy.supercleanmaster.fragment.AutoStartFragment;
import com.yzy.supercleanmaster.fragment.WeakFragmentPagerAdapter;
import com.yzy.supercleanmaster.utils.SystemBarTintManager;
import com.yzy.supercleanmaster.utils.UIElementsHelper;
import com.yzy.supercleanmaster.views.SlidingTab;

import butterknife.InjectView;


public class AutoStartManageActivity extends BaseSwipeBackActivity {

    ActionBar ab;


    Resources res;


    @InjectView(R.id.tabs)
    SlidingTab tabs;

    @InjectView(R.id.pagerFragmentTask)
    ViewPager pager;

    private MyPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autostart_manage);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        getActionBar().setHomeButtonEnabled(true);
      //  applyKitKatTranslucency();


        res = getResources();
        adapter = new MyPagerAdapter(getSupportFragmentManager());

        pager.setAdapter(adapter);

        int pageMargin = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                        .getDisplayMetrics());
        pager.setPageMargin(pageMargin);

        tabs.setViewPager(pager);
        setTabsValue();

    }


    private void setTabsValue() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        tabs.setShouldExpand(true);
        tabs.setDividerColor(Color.TRANSPARENT);
        tabs.setUnderlineHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 1, dm));
        tabs.setIndicatorHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 3, dm));
        tabs.setTextSize((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 16, dm));
        tabs.setTextColor(Color.parseColor("#ffffff"));
        tabs.setIndicatorColor(Color.parseColor("#ffffff"));
        tabs.setSelectedTextColor(Color.parseColor("#ffffff"));
        tabs.setTabBackground(0);

    }


    /**
     * Apply KitKat specific translucency.
     */

    private void applyKitKatTranslucency() {

        // KitKat translucent navigation/status bar.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager mTintManager = new SystemBarTintManager(this);
            mTintManager.setStatusBarTintEnabled(true);
            mTintManager.setNavigationBarTintEnabled(true);
            // mTintManager.setTintColor(0xF00099CC);

            mTintManager.setTintDrawable(UIElementsHelper
                    .getGeneralActionBarBackground(this));


            getActionBar().setBackgroundDrawable(
                    UIElementsHelper.getGeneralActionBarBackground(this));

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public class MyPagerAdapter extends WeakFragmentPagerAdapter {

        private final String[] TITLES = {"Common software", "System core software"};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {
            // return SuperAwesomeCardFragment.newInstance(position);return
            AutoStartFragment fragment = new AutoStartFragment();
            Bundle bundle = new Bundle();

            bundle.putInt("position", position);
            fragment.setArguments(bundle);
            saveFragment(fragment);

            return fragment;

        }

    }


    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
