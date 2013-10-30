
package com.michaelflisar.licenses.licenses;

import android.os.Parcel;

import com.michaelflisar.licenses.Helper;

public class GitLicenseEntry extends BaseLicenseEntry
{
    public GitLicenseEntry(Parcel parcel)
    {
        super(parcel);
    }

    public GitLicenseEntry(String licenseName, String gitRepo, String branch, License license,  String relLicensePath)
    {
        super(null, branch, null, null, null);
        init(licenseName, gitRepo, branch, license, relLicensePath);
    }

    private void init(String licenseName, String gitRepo, String branch, License license, String relLicensePath)
    {
        libraryName = gitRepo.substring(gitRepo.indexOf("/") + 1);
        libraryAuthor = gitRepo.substring(0, gitRepo.indexOf("/"));
        libraryLink = "https://github.com/" + gitRepo;

        if (license == null)
        {
            String licenseLink = (libraryLink + "/" + (branch != null ? branch : "master") + "/").replace("github.com", "raw.github.com").replace("/tree/", "/") + relLicensePath;
            this.license = new License(licenseName, licenseLink);
        }
        else
            this.license = license;
    }

    @Override
    public void doLoad()
    {
        String licenseLink = license.getUrl();
        license.setText(Helper.readFile(licenseLink));
    }

}
