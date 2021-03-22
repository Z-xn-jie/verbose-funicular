package com.sprout.baselibrary.interaction;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class EmptyHomeService implements IHomeService {
    @Override
    public void launch(Context ctx, String targetClass) {

    }

    @Override
    public Fragment newUserInfoFragment(FragmentManager fragmentManager, int viewId, Bundle bundle) {
        return null;
    }
}
