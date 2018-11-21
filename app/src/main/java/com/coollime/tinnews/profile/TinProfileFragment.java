package com.coollime.tinnews.profile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.coollime.tinnews.R;
import com.coollime.tinnews.common.ViewModelAdapter;
import com.coollime.tinnews.mvp.MvpFragment;
import com.coollime.tinnews.save.detail.TitleViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class TinProfileFragment extends MvpFragment<ProfileContract.Presenter> implements
        ProfileContract.View {

    private ViewModelAdapter viewModelAdapter;

    public static TinProfileFragment newInstance() {
        Bundle args = new Bundle();
        TinProfileFragment fragment = new TinProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tin_profile, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewModelAdapter = new ViewModelAdapter();
        recyclerView.setAdapter(viewModelAdapter);
        return view;
    }

    @Override
    public ProfileContract.Presenter getPresenter() {
        return new ProfilePresenter();
    }

    @Override
    public void setView() {
        if (!viewModelAdapter.isEmpty()) {
            return;
        }
        // Settings title
        viewModelAdapter.addViewModel(
                new TitleViewModel(getString(R.string.setting), R.layout.setting_title_layout)
        );
        // Clear saved news
        viewModelAdapter.addViewModel(
                new RowTextViewModel(
                        getString(R.string.clear_cache), presenter.getCacheClearListener()
                )
        );
        // Change country
        viewModelAdapter.addViewModel(
                new TitleViewModel(
                        getString(R.string.change_source), R.layout.setting_title_layout
                )
        );
        // Set US edition
        viewModelAdapter.addViewModel(
                new RowTextViewModel(
                        getString(R.string.us),
                        presenter.getOnCountryChangeListener(getString(R.string.us))
                )
        );
        // Set Chinese edition
        viewModelAdapter.addViewModel(
                new RowTextViewModel(
                        getString(R.string.cn),
                        presenter.getOnCountryChangeListener(getString(R.string.cn))
                )
        );
        // Set German edition
        viewModelAdapter.addViewModel(
                new RowTextViewModel(
                        getString(R.string.de),
                        presenter.getOnCountryChangeListener(getString(R.string.de))
                )
        );
    }

    @Override
    public void onCacheCleared() {
        Toast.makeText(getContext(), "Saved news has been deleted", Toast.LENGTH_SHORT).show();
    }
}
