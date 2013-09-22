
package com.michaelflisar.licenses.dialog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.michaelflisar.licenses.LicenseBusProvider;
import com.michaelflisar.licenses.licenses.BaseLicenseEntry;
import com.michaelflisar.licensesdialog.R;
import com.michaelflisar.retainer.RKey;
import com.michaelflisar.retainer.Retainer;
import com.michaelflisar.retainer.RetainerProvider;
import com.michaelflisar.retainer.interfaces.IRetainerSingleKeyListener;

public class LicensesDialogHelper implements OnClickListener, IRetainerSingleKeyListener
{
    private final String BASE_TAG = LicensesDialogHelper.class.getName() + "|";

    private boolean mIsSupportFragment = false;
    private DialogFragment mDialogFragment = null;
    private android.support.v4.app.DialogFragment mSupportDialogFragment = null;

    private Retainer mRetainer;
    private RKey mKey = new RKey(LicensesDialogHelper.class);

    private AdapterLicenses mAdapter = null;

    private List<BaseLicenseEntry> mLicenses;

    public LicensesDialogHelper(DialogFragment fragment)
    {
        mIsSupportFragment = false;
        mDialogFragment = fragment;
    }

    public LicensesDialogHelper(android.support.v4.app.DialogFragment fragment)
    {
        mIsSupportFragment = true;
        mSupportDialogFragment = fragment;
    }   

    public LicensesDialogHelper(DialogFragment fragment, List<BaseLicenseEntry> licenses)
    {
        mIsSupportFragment = false;
        mDialogFragment = fragment;
        mLicenses = licenses;
    }
    
    public LicensesDialogHelper(android.support.v4.app.DialogFragment fragment, List<BaseLicenseEntry> licenses)
    {
        mIsSupportFragment = true;
        mSupportDialogFragment = fragment;
        mLicenses = licenses;
    }
    
    private Activity getActivity()
    {
        Activity activity;
        if (mIsSupportFragment)
            activity = mSupportDialogFragment.getActivity();
        else
            activity = mDialogFragment.getActivity();
        return activity;
    }

    public void onResume()
    {
        LicenseBusProvider.getInstance().register(this);
    }

    public void onPause()
    {
        LicenseBusProvider.getInstance().unregister(this);
    }

    public void onCreate(Bundle savedInstanceState)
    {
        if (savedInstanceState != null)
        {
            int licenses = savedInstanceState.getInt(BASE_TAG + "mLicenses|size");
            mLicenses = new ArrayList<BaseLicenseEntry>();
            for (int i = 0; i < licenses; i++)
                mLicenses.add((BaseLicenseEntry) savedInstanceState.getParcelable(BASE_TAG + "mLicenses|" + i));
        }

        mRetainer = RetainerProvider.getInstance(getActivity().getFragmentManager()).getRetainer();
    }

    public void onSaveInstanceState(Bundle outState)
    {
        outState.putInt(BASE_TAG + "mLicenses|size", mLicenses.size());
        for (int i = 0; i < mLicenses.size(); i++)
            outState.putParcelable(BASE_TAG + "mLicenses|" + i, mLicenses.get(i));
    }

    public final Dialog onCreateDialog(final Bundle savedInstanceState)
    {
        return createDialog(savedInstanceState, getActivity(), mRetainer);
    }

    @SuppressWarnings("unchecked")
    public Dialog createDialog(final Bundle savedInstanceState, Activity activity, Retainer retainer)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.ld_licenses);
        builder.setNegativeButton(R.string.ld_back, this);

        mAdapter = new AdapterLicenses(activity, mLicenses);

        // get or load data
        if (retainer.contains(mKey))
            mAdapter.updateList((List<BaseLicenseEntry>) retainer.get(mKey));
        else
        {
            final List<BaseLicenseEntry> data = mLicenses;
            Callable<List<BaseLicenseEntry>> callable = new Callable<List<BaseLicenseEntry>>()
            {
                @Override
                public List<BaseLicenseEntry> call() throws Exception
                {
                    // load data for list
                    for (int i = 0; i < data.size(); i++)
                        data.get(i).load();

                    return data;
                }
            };
            retainer.execute(mKey, callable, this);
        }

        ExpandableListView expendableList = new ExpandableListView(activity);
        expendableList.setAdapter(mAdapter);
        builder.setView(expendableList);

        return builder.create();
    }

    public void onClick(DialogInterface dialog, int which)
    {
        if (which == DialogInterface.BUTTON_NEGATIVE)
        {
            if (mIsSupportFragment)
                mSupportDialogFragment.dismiss();
            else
                mDialogFragment.dismiss();
        }
    }

    @SuppressWarnings("unchecked")
    public void onRetainerDataAdded(Object data)
    {
        mAdapter.updateList((List<BaseLicenseEntry>) data);
    }

    public RKey getRKey()
    {
        return mKey;
    }
}
