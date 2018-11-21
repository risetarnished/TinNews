package com.coollime.tinnews.profile;

import android.view.View;

public class ProfilePresenter implements ProfileContract.Presenter {
    private ProfileContract.View view;
    private ProfileContract.Model model;

    ProfilePresenter() {
        this.model = new ProfileModel();
        this.model.setPresenter(this);
    }

    @Override
    public void onCacheCleared() {

    }

    @Override
    public View.OnClickListener getCacheClearListener() {
        return null;
    }

    @Override
    public View.OnClickListener getOnCountryChangeListener(String country) {
        return null;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onViewAttached(ProfileContract.View view) {
        // Keep the view
        this.view = view;
    }

    @Override
    public void onViewDetached() {
        // Clear the view
        this.view = null;
    }
}
