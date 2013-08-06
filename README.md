LicensesDialog
==============

Simple, automatic license dialog (for foreign licenses)

Usage
=========

    List<DefaultLicenseEntry> list = new ArrayList<DefaultLicenseEntry>();
    list.add(new GitLicenseEntry(
        "MessageBar", 
        "1.0", 
        "Apache License v2.0", 
        "https://github.com/MichaelFlisar/MessageBar", 
        "/master/LICENSE"));
        
     LicensesDialog dialog = new LicensesDialog(getActivity(), R.string.back, R.string.licenses, list);
     dialog.show(this.getActivity().getSupportFragmentManager());
