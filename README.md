LicensesDialog
==============

Simple, automatic license dialog (for foreign licenses)

Usage
=========

    List<BaseLicenseEntry> list = new ArrayList<BaseLicenseEntry>();
    list.add(new GitLicenseEntry("Apache License v2.0", "Prototik/HoloEverywhere"));
    list.add(new GitLicenseEntry("Apache License v2.0", "jfeinstein10/SlidingMenu"));
    list.add(new GitLicenseEntry("Apache License v2.0", "bauerca/drag-sort-listview"));
    list.add(new GitLicenseEntry("Apache License v2.0", "JakeWharton/Android-ViewPagerIndicator"));
    list.add(new GitLicenseEntry("Apache License v2.0", "lorensiuswlt/NewQuickAction3D"));
    list.add(new GitLicenseEntry("Apache License v2.0", "JakeWharton/ActionBarSherlock"));
    list.add(new GitLicenseEntry("Apache License v2.0", "ManuelPeinado/MultiChoiceAdapter"));
        
     LicensesDialog dialog = new LicensesDialog(getActivity(), R.string.back, R.string.licenses, list);
     dialog.show(this.getActivity().getSupportFragmentManager());
