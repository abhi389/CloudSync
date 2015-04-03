package com.se1.navdrawer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.astuetz.PagerSlidingTabStrip;
import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.se1.DropBox.DropBoxLogin;
import com.se1.DropBox.DropboxDownload;
import com.se1.main.R;

public class PageSlidingTabStripFragment extends Fragment {
    public static DropboxAPI<AndroidAuthSession> getmApi() {
        return mApi;
    }

    public static void setmApi(DropboxAPI<AndroidAuthSession> mApi) {
        PageSlidingTabStripFragment.mApi = mApi;
    }

    private static DropboxAPI<AndroidAuthSession> mApi;
	public static final String TAG = PageSlidingTabStripFragment.class
			.getSimpleName();

	public static PageSlidingTabStripFragment newInstance() {
		return new PageSlidingTabStripFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        setmApi(NavigationMainActivity.getmApi());
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.pager, container, false);
	}
     ViewPager pager=null;

    PagerSlidingTabStrip tabs=null;
    @Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		tabs = (PagerSlidingTabStrip) view
				.findViewById(R.id.tabs);
        pager= (ViewPager) view.findViewById(R.id.pager);

        final ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
        MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager());
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);



	}



    public class MyPagerAdapter extends FragmentPagerAdapter implements Runnable {
        private DropboxAPI<AndroidAuthSession> mApi;
		public MyPagerAdapter(android.support.v4.app.FragmentManager fm) {
			super(fm);
		}

		private final String[] TITLES = { "All Files", "Images",
				"Documents" };

		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES[position];
		}

		@Override
		public int getCount() {
			return TITLES.length;
		}

		@Override
		public SherlockFragment getItem(int position) {
            mApi = PageSlidingTabStripFragment.getmApi();
                    /*
                    if(position == 0 && mApi != null)
                    {

                        DropboxDownload dropboxDownload= new DropboxDownload();
                        dropboxDownload.setDataFromLogin(getActivity().getApplicationContext(),mApi);
                        return dropboxDownload;


                    }
                    else */return SuperAwesomeCardFragment.newInstance(position);


		}

        @Override
        public void run() {

        }
    }


}
