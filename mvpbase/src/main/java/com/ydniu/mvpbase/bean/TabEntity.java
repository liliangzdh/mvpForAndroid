package com.ydniu.mvpbase.bean;

import com.flyco.tablayout.listener.CustomTabEntity;

public class TabEntity implements CustomTabEntity {

    private TabEntity() {

    }

    String title;
    int selectedIcon;
    int unselectedIcon;

    public TabEntity(String title, int selectedIcon, int unselectedIcon) {
        this.title = title;
        this.selectedIcon = selectedIcon;
        this.unselectedIcon = unselectedIcon;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unselectedIcon;
    }
}
