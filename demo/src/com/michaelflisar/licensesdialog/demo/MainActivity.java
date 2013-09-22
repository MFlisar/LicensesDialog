package com.michaelflisar.licensesdialog.demo;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.michaelflisar.licenses.dialog.LicensesDialog;
import com.michaelflisar.licenses.licenses.BaseLicenseEntry;
import com.michaelflisar.licenses.licenses.GitLicenseEntry;
import com.michaelflisar.licenses.licenses.License;
import com.michaelflisar.licensesdialogdemo.R;

public class MainActivity extends SherlockFragmentActivity implements OnClickListener
{
    private List<BaseLicenseEntry> list = new ArrayList<BaseLicenseEntry>();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // fremde Libraries
        License customLicense = new License("Custom", ""); // only the name is used in GitLicenseEntries!!!
        list.add(new GitLicenseEntry("Prototik/HoloEverywhere", customLicense).setManualLicensePath("LICENSE")); // not default LicenseFile nme/path, default would be "LICENSE.txt"
        list.add(new GitLicenseEntry("jfeinstein10/SlidingMenu"));
        list.add(new GitLicenseEntry("bauerca/drag-sort-listview").loadLicenseDirect()); // no license file in GIT Repo...
        list.add(new GitLicenseEntry("JakeWharton/Android-ViewPagerIndicator").loadLicenseDirect()); // no license file in GIT Repo...
        list.add(new GitLicenseEntry("lorensiuswlt/NewQuickAction3D"));
        list.add(new GitLicenseEntry("JakeWharton/ActionBarSherlock"));
        list.add(new GitLicenseEntry("ManuelPeinado/MultiChoiceAdapter"));

        // eigene Libraries
        list.add(new GitLicenseEntry("MichaelFlisar/MessageBar"));
        list.add(new GitLicenseEntry("MichaelFlisar/Retainer"));
        list.add(new GitLicenseEntry("MichaelFlisar/Debugger"));
        list.add(new GitLicenseEntry("MichaelFlisar/LicensesDialog"));
    }

    @Override
    public void onClick(View v)
    {
        LicensesDialog dialog = new LicensesDialog(this, list);
        dialog.show(getSupportFragmentManager(), this.getClass().getName());
    }
}
