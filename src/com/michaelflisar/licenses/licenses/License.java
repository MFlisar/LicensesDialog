
package com.michaelflisar.licenses.licenses;

import android.os.Parcel;
import android.os.Parcelable;

public class License implements Parcelable
{
    private String mName;
    private String mUrl;
    private String mText;

    public License(Parcel parcel)
    {
        mName = parcel.readString();
        mUrl = parcel.readString();
        
        if (parcel.readByte() == (byte)1)
            mText = parcel.readString();
        else
            mText = null;
        
    }

    public License(String name, String url)
    {
        mName = name;
        mUrl = url;
        mText = null;
    }

    public String getName()
    {
        return mName;
    }

    public String getUrl()
    {
        return mUrl;
    }
    
    public String getText()
    {
        return mText;
    }
    
    public boolean isLoaded()
    {
        return mText != null;
    }
    
    public void setText(String text)
    {
        mText = text;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(mName);
        dest.writeString(mUrl);
        
        dest.writeByte((byte)(mText != null ? 1 : 0));
        if (mText != null)
            dest.writeString(mText);
    }
}
