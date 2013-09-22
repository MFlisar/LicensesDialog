
package com.michaelflisar.licenses.dialog;

import java.util.List;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.michaelflisar.licenses.licenses.BaseLicenseEntry;

public class LicensesSupportDialog extends DialogFragment
{
    private LicensesDialogHelper mHelper;

    public LicensesSupportDialog()
    {
        mHelper = new LicensesDialogHelper(this);
    }

    public LicensesSupportDialog(List<BaseLicenseEntry> licenses)
    {
        mHelper = new LicensesDialogHelper(this, licenses);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        mHelper.onResume();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        mHelper.onPause();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mHelper.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        mHelper.onSaveInstanceState(outState);
    }

    @Override
    public final Dialog onCreateDialog(final Bundle savedInstanceState)
    {
        return mHelper.onCreateDialog(savedInstanceState);
    }
}
