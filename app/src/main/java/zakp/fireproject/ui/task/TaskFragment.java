package zakp.fireproject.ui.task;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import zakp.fireproject.R;
import zakp.fireproject.helper.EventPosterHelper;
import zakp.fireproject.helper.FragmentHelper;
import zakp.fireproject.ui.base.BaseFragment;

/**
 * Created by Paweł Żak on 24.04.2016.
 */
public class TaskFragment extends BaseFragment implements TaskMvpView {


    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Inject
    EventPosterHelper eventPosterHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_task, container, false);
        FragmentHelper.inject(getActivity(), this);

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        eventPosterHelper.getBus().post(new TabEvent(viewPager, false));


//        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(viewPager);
//        setupTabIcons();


        return view;
    }

//    private void setupTabIcons() {
//        int[] tabIcons = {
//                R.drawable.ic_tab_favourite,
//                R.drawable.ic_tab_call,
//                R.drawable.ic_tab_contacts
//        };
//
//        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
//        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
//        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
//    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFrag(new OneFragment(), "ONE");
        adapter.addFrag(new TwoFragment(), "TWO");
        adapter.addFrag(new ThreeFragment(), "THREE");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {

            // return null to display only the icon
            return null;
        }
    }


    public class TabEvent {
        ViewPager viewPager;
        Boolean isClose;

        public TabEvent(ViewPager viewPager, Boolean isClose) {
            this.viewPager = viewPager;
            this.isClose = isClose;
        }

        public Boolean getIsClose() {
            return isClose;
        }

        public void setIsClose(Boolean isClose) {
            this.isClose = isClose;
        }

        public ViewPager getViewPager() {
            return viewPager;
        }

        public void setViewPager(ViewPager viewPager) {
            this.viewPager = viewPager;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        eventPosterHelper.getBus().post(new TabEvent(viewPager, true));
    }
}
