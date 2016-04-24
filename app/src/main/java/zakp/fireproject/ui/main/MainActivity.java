package zakp.fireproject.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mxn.soul.flowingdrawer_core.FlowingView;
import com.mxn.soul.flowingdrawer_core.LeftDrawerLayout;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import zakp.fireproject.R;
import zakp.fireproject.helper.EventPosterHelper;
import zakp.fireproject.helper.FragmentHelper;
import zakp.fireproject.ui.base.BaseActivity;
import zakp.fireproject.ui.main.flowingdrawer.MyMenuFragment;
import zakp.fireproject.ui.projectlist.ProjectListFragment;
import zakp.fireproject.ui.task.TaskFragment;


public class MainActivity extends BaseActivity implements MainMvpView {


    private LeftDrawerLayout mLeftDrawerLayout;

    private TabLayout tabLayout;

    Toolbar toolbar;

    @Inject
    EventPosterHelper eventPosterHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActivityComponent().inject(this);
        setupToolbar();
        eventPosterHelper.getBus().register(this);
        mLeftDrawerLayout = (LeftDrawerLayout) findViewById(R.id.id_drawerlayout);
        // rvFeed = (RecyclerView) findViewById(R.id.rvFeed);

        FragmentManager fm = getSupportFragmentManager();
        MyMenuFragment mMenuFragment = (MyMenuFragment) fm.findFragmentById(R.id.id_container_menu);
        FlowingView mFlowingView = (FlowingView) findViewById(R.id.sv);
        if (mMenuFragment == null) {
            fm.beginTransaction().add(R.id.id_container_menu, mMenuFragment = new MyMenuFragment()).commit();
        }
        mLeftDrawerLayout.setFluidView(mFlowingView);
        mLeftDrawerLayout.setMenuFragment(mMenuFragment);
        FragmentHelper.replaceFragment(this, new ProjectListFragment(), true);

        tabLayout = (TabLayout) findViewById(R.id.tabs);


    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        eventPosterHelper.getBus().unregister(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    public void onBackPressed() {
        if (mLeftDrawerLayout.isShownMenu()) {
            mLeftDrawerLayout.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    protected void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_white);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLeftDrawerLayout.toggle();
            }
        });


    }


    private void setupTabIcons() {
        int[] tabIcons = {
                R.drawable.ic_tab_favourite,
                R.drawable.ic_tab_call,
                R.drawable.ic_tab_contacts
        };

        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }


    private void setTabText(String title) {
        tabLayout.getTabAt(0).setText(title);
    }

    @Subscribe
    public void TabListener(TaskFragment.TabEvent event) {

        if (!event.getIsClose())
            setTab(event.getViewPager());
        else
            releseTab();
    }


    public void setTab(ViewPager viewPager) {
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        tabLayout.setVisibility(View.VISIBLE);
        toolbar.invalidate();
    }

    public void releseTab() {
        tabLayout.removeAllTabs();
        tabLayout.setVisibility(View.GONE);
        toolbar.invalidate();
    }


}
