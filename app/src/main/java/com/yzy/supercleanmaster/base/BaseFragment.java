package com.yzy.supercleanmaster.base;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.yzy.supercleanmaster.utils.T;


@SuppressLint("NewApi")
public class BaseFragment extends Fragment {


	protected void startActivity(Class<?> cls) {
		startActivity(cls, null);
	}

	protected void startActivity(Class<?> cls, Bundle bundle) {
		Intent intent = new Intent();
		intent.setClass(getActivity(), cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}

	protected void startActivity(String action) {
		startActivity(action, null);
	}

	protected void startActivity(String action, Bundle bundle) {
		Intent intent = new Intent();
		intent.setAction(action);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}

	/**
	 * 吐司
	 * 
	 * @param message
	 */
	protected void showShort(String message) {
		T.showShort(getActivity(), message);
	}

	protected void showLong(String message) {
		T.showLong(getActivity(), message);
	}
}
