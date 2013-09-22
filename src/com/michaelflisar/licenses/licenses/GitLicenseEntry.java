
package com.michaelflisar.licenses.licenses;

import android.os.Parcel;

import com.michaelflisar.licenses.Helper;

public class GitLicenseEntry extends BaseLicenseEntry
{
    private String mBranch;
    private String mRelLicensePath;
    private boolean mLoadLicenseDirectly;

    public GitLicenseEntry(Parcel parcel)
    {
        super(parcel);
        mRelLicensePath = parcel.readString();
        mLoadLicenseDirectly = parcel.readByte() == (byte) 1;

        if (parcel.readByte() == (byte) 1)
            mBranch = parcel.readString();
        else
            mBranch = null;
    }

    public GitLicenseEntry(String gitRepo)
    {
        super(null, "master", null, null, null);
        init(gitRepo, Licenses.LICENSE_APACHE_V2, null);
    }
    
    public GitLicenseEntry(String gitRepo, String branch)
    {
        super(null, branch, null, null, null);
        init(gitRepo, Licenses.LICENSE_APACHE_V2, null);
    }

    public GitLicenseEntry(String gitRepo, License license)
    {
        super(null, "master", null, null, null);
        init(gitRepo, license, null);
    }

    public GitLicenseEntry(String gitRepo, License license, String branch)
    {
        super(null, branch, null, null, null);
        init(gitRepo, license, branch);
    }

    private void init(String gitRepo, License license, String branch)
    {
        mLoadLicenseDirectly = false;
        mBranch = branch;

        this.license = license;
        libraryName = gitRepo.substring(gitRepo.indexOf("/") + 1);
        libraryAuthor = gitRepo.substring(0, gitRepo.indexOf("/"));
        libraryLink = "https://github.com/" + gitRepo;

        mRelLicensePath = "LICENSE.txt";
    }

    public GitLicenseEntry loadLicenseDirect()
    {
        mLoadLicenseDirectly = true;
        return this;
    }

    public GitLicenseEntry setManualLicensePath(String path)
    {
        mRelLicensePath = path;
        return this;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        super.writeToParcel(dest, flags);
        dest.writeString(mRelLicensePath);
        dest.writeByte((byte) (mLoadLicenseDirectly ? 1 : 0));

        dest.writeByte((byte) (mBranch != null ? 1 : 0));
        if (mBranch != null)
            dest.writeString(mBranch);
    }

    @Override
    public void doLoad()
    {
        String licenseLink = license.getUrl();
        if (!mLoadLicenseDirectly)
            licenseLink = (libraryLink + "/" + (mBranch != null ? mBranch : "master") + "/").replace("github.com", "raw.github.com").replace("/tree/", "/") + mRelLicensePath;
        license.setText(Helper.readFile(licenseLink));
    }

}
