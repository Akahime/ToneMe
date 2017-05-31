package com.dty.manu.toneme;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by Sarah on 24/05/2017.
 */

public class SettingsActivity extends Activity {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
    }
}
