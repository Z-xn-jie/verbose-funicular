package com.sprout.baselibrary.interaction;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public interface IHomeService {
    void launch(Context ctx, String targetClass);

    Fragment newUserInfoFragment(FragmentManager fragmentManager, int viewId, Bundle bundle);
}
