
package com.michaelflisar.licenses;

import java.util.ArrayList;
import java.util.List;

import com.michaelflisar.licenses.licenses.BaseLicenseEntry;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class LoadingTaskFragment extends Fragment
{
    public interface DataLoadedCallback
    {
        public void dataLoaded(List<BaseLicenseEntry> mList);
    }

    private AsyncTask<Void, Void, Void> mTask = null;
    private List<BaseLicenseEntry> mList = new ArrayList<BaseLicenseEntry>();
    private boolean mDataLoaded = false;
    private DataLoadedCallback mCallback = null;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void attachCallback(DataLoadedCallback callback, List<BaseLicenseEntry> list)
    {
        mCallback = callback;
        if (mTask == null)
        {
            mList = list;
            mTask = new AsyncTask<Void, Void, Void>()
            {
                @Override
                protected Void doInBackground(Void... params)
                {                    
                    // load data for list
                    for (int i = 0; i < mList.size(); i++)
                        mList.get(i).load();
                    return null;
                }

                @Override
                protected void onPostExecute(Void result)
                {
                    mDataLoaded = true;
                    if (mCallback != null)
                        mCallback.dataLoaded(mList);
                }

            };
            mTask.execute();
        }
    }

    public void detachCallback()
    {
        mCallback = null;
    }

    public boolean isDataLoaded()
    {
        return mDataLoaded;
    }

    public List<BaseLicenseEntry> getLoadedData()
    {
        if (mDataLoaded)
            return mList;
        return null;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if (mTask != null)
            mTask.cancel(true);
    }
}
