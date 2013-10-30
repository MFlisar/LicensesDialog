package com.michaelflisar.licensesdialog.demo;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.michaelflisar.licenses.dialog.LicensesDialog;
import com.michaelflisar.licenses.licenses.BaseLicenseEntry;
import com.michaelflisar.licenses.licenses.Licenses;
import com.michaelflisar.licensesdialogdemo.R;
import com.michaelflisar.universalloader.ULActivity;

public class MainActivity extends ULActivity implements OnClickListener
{
    private List<BaseLicenseEntry> list = new ArrayList<BaseLicenseEntry>();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        list.add(Licenses.createGitLicense("Prototik/HoloEverywhere", "Custom", "LICENSE")); // not default LicenseFile name/path, default would be "LICENSE.txt"
        list.add(Licenses.createGitLicense("jfeinstein10/SlidingMenu"));
        list.add(Licenses.createGitLicense("bauerca/drag-sort-listview", Licenses.LICENSE_APACHE_V2)); // no license file in GIT Repo...
        list.add(Licenses.createGitLicense("JakeWharton/Android-ViewPagerIndicator", Licenses.LICENSE_APACHE_V2)); // no license file in GIT Repo...
        list.add(Licenses.createGitLicense("lorensiuswlt/NewQuickAction3D"));
        list.add(Licenses.createGitLicense("JakeWharton/ActionBarSherlock"));
        list.add(Licenses.createGitLicense("ManuelPeinado/MultiChoiceAdapter"));

        list.add(Licenses.createGitLicense("MichaelFlisar/MessageBar"));
        list.add(Licenses.createGitLicense("MichaelFlisar/UniversalLoader"));
        list.add(Licenses.createGitLicense("MichaelFlisar/LicensesDialog"));
    }

    @Override
    public void onClick(View v)
    {
        LicensesDialog dialog = new LicensesDialog(list);
        dialog.show(getSupportFragmentManager(), this.getClass().getName());
    }
}
