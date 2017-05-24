package com.dty.manu.toneme;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by Sarah on 24/05/2017.
 */

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Load the preferences from XML
        addPreferencesFromResource(R.xml.preferences);
    }
}
