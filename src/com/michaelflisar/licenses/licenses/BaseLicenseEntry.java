
package com.michaelflisar.licenses.licenses;

import android.os.Parcel;
import android.os.Parcelable;

public abstract class BaseLicenseEntry implements Parcelable
{
    protected String libraryName;
    protected String libraryVersion;
    protected String libraryAuthor;
    protected String libraryLink;
    
    protected License license;
    
    public BaseLicenseEntry()
    {
        libraryName = null;
        libraryVersion = null;
        libraryAuthor = null;
        libraryLink = null;
        
        license = null;
    }

    public BaseLicenseEntry(Parcel parcel)
    {
        libraryName = parcel.readString();
        libraryVersion = parcel.readString();
        libraryAuthor = parcel.readString();
        libraryLink = parcel.readString();
        
        license = parcel.readParcelable(getClass().getClassLoader());
    }
    
    public BaseLicenseEntry(String libraryName, String libraryVersion, String libraryAuthor, String libraryLink, License license)
    {
        this.libraryName = libraryName;
        this.libraryVersion = libraryVersion;
        this.libraryAuthor = libraryAuthor;
        this.libraryLink = libraryLink;
        this.license = license;
    }
    
    public License getLicense()
    {
        return license;
    }
    
    public String getLibraryName()
    {
        return libraryName;
    }

    public String getLibraryVersion()
    {
        return libraryVersion;
    }

    public String getLibraryAuthor()
    {
        return libraryAuthor;
    }

    public String getLibraryLink()
    {
        return libraryLink;
    }
    
    public boolean isLoaded()
    {
        return license.isLoaded();
    }
    
    public void load()
    {
        if (license.isLoaded())
            return;
        
        doLoad();
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(libraryName);
        dest.writeString(libraryVersion);
        dest.writeString(libraryAuthor);
        dest.writeString(libraryLink);
        dest.writeParcelable(license, 0);
    }
    
    public abstract void doLoad();
}
