
package com.michaelflisar.licenses.dialog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.michaelflisar.licenses.licenses.BaseLicenseEntry;
import com.michaelflisar.licensesdialog.R;
import com.michaelflisar.universalloader.data.fragments.ULFragmentLoaderData.ULLoaderType;
import com.michaelflisar.universalloader.data.fragments.ULFragmentLoaders;
import com.michaelflisar.universalloader.data.main.ULFragmentKey;
import com.michaelflisar.universalloader.data.main.ULKey;
import com.michaelflisar.universalloader.fragments.ULDialogFragment;

public class LicensesDialog extends ULDialogFragment implements OnClickListener
{
    private static final ULKey mBaseKey = new ULKey(LicensesDialog.class);

    private final String BASE_TAG = LicensesDialog.class.getName();

    private AdapterLicenses mAdapter = null;
    private List<BaseLicenseEntry> mLicenses;

    public LicensesDialog()
    {
        super();
    }

    public LicensesDialog(List<BaseLicenseEntry> licenses)
    {
        super();
        mLicenses = licenses;
    }

    @Override
    public ULFragmentKey createFragmentKey()
    {
        return new ULFragmentKey(getClass().getName());
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        if (savedInstanceState != null)
        {
            int licenses = savedInstanceState.getInt(BASE_TAG + "mLicenses|size");
            mLicenses = new ArrayList<BaseLicenseEntry>();
            for (int i = 0; i < licenses; i++)
                mLicenses.add((BaseLicenseEntry) savedInstanceState.getParcelable(BASE_TAG + "mLicenses|" + i));
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt(BASE_TAG + "mLicenses|size", mLicenses.size());
        for (int i = 0; i < mLicenses.size(); i++)
            outState.putParcelable(BASE_TAG + "mLicenses|" + i, mLicenses.get(i));
    }

    @Override
    public void onPrepareBuilder(AlertDialog.Builder builder)
    {
        builder.setTitle(R.string.ld_licenses);
        builder.setNegativeButton(R.string.ld_back, this);
    }

    @Override
    public View onCreateUserView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        disableAutomaticLoadingOverlay();
        mAdapter = new AdapterLicenses(getActivity(), mLicenses);
        ExpandableListView expendableList = new ExpandableListView(getActivity());
        expendableList.setAdapter(mAdapter);
        return expendableList;
    }

    @Override
    public void onClick(DialogInterface dialog, int which)
    {
        if (which == DialogInterface.BUTTON_NEGATIVE)
            dismiss();
    }

    @Override
    public void onDataReceived(ULKey key, Object data)
    {
        if (mAdapter != null)
        {
            int pos = Integer.parseInt(key.toString().replace(mBaseKey.toString() + "|", ""));
            mAdapter.setChild(pos, (BaseLicenseEntry) data);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public ULFragmentLoaders createLoaders()
    {
        ULFragmentLoaders loaders = new ULFragmentLoaders();
        for (int i = 0; i < mLicenses.size(); i++)
        {
            final BaseLicenseEntry entry = mLicenses.get(i);
            loaders.add(mBaseKey.getSubKey(String.valueOf(i)), new Callable<Object>()
            {
                @Override
                public Object call() throws Exception
                {
                    entry.load();
                    return entry;
                }
            }, ULLoaderType.OnViewCreated);
        }
        return loaders;
    }
}
