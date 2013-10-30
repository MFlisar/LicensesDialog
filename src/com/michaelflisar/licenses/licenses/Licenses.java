package com.michaelflisar.licenses.licenses;

public class Licenses
{
    public static final License LICENSE_APACHE_V2 = new License("Apache License V2.0", "http://www.apache.org/licenses/LICENSE-2.0");
    
    private static final String DEF_LICENSE_NAME = "Apache License V2.0";
    private static final String DEF_BRANCH = "master";
    private static final String DEF_REL_LICENSE_PATH = "LICENSE.txt";
    
    public static GitLicenseEntry createGitLicense(String gitRepo)
    {
        return new GitLicenseEntry(DEF_LICENSE_NAME, gitRepo, DEF_BRANCH, null, DEF_REL_LICENSE_PATH);
    }
    
    public static GitLicenseEntry createGitLicense(String gitRepo, String relLicensePath)
    {
        return new GitLicenseEntry(DEF_LICENSE_NAME, gitRepo, DEF_BRANCH, null, relLicensePath);
    }
    
    public static GitLicenseEntry createGitLicense(String gitRepo, License license)
    {
        return new GitLicenseEntry(null, gitRepo, DEF_BRANCH, license, null);
    }
    
    public static GitLicenseEntry createGitLicense(String gitRepo, String licenseName, String relLicensePath)
    {
        return new GitLicenseEntry(licenseName, gitRepo, DEF_BRANCH, null, relLicensePath);
    }
}
