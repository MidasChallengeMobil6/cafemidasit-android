package com.midasit.challenge.ui.member.cafemenu;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.midasit.challenge.R;
import com.midasit.challenge.ui.admin.cafemenu.tabs.CoffeFragment;
import com.midasit.challenge.ui.admin.cafemenu.tabs.FoodFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {

    ViewPager viewPager;
    TabLayout tabLayout;
    AppCompatSpinner spinnerSort;
    Toolbar toolbar;

    private final String[] TEST_CATEGORIES = {
            "커피","푸드"
    };

    private MenuPagerAdapter pagerAdapter;


    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = view.findViewById(R.id.pager);
        tabLayout = view.findViewById(R.id.tab);
        spinnerSort = view.findViewById(R.id.spinner_sort);
        toolbar = view.findViewById(R.id.toolbar);

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
                getActivity().overridePendingTransition(0, 0);
            }
        });

        initTabPager(viewPager, tabLayout);
//        initSpinnerSort(spinnerSort);
//        initSpinnerViewType(spinnerViewType);
    }

    public void initTabPager(ViewPager pager, TabLayout tabLayout) {
        pagerAdapter = new MenuPagerAdapter(getFragmentManager());

        String[] categories = getCategories();
        Fragment[] fragments = new Fragment[categories.length];

        for (int i = 0; i < categories.length; i++) {
            String category = categories[i];
            tabLayout.addTab(tabLayout.newTab().setText(category));
        }
        fragments[0] = new CoffeFragment();
        fragments[1] = new FoodFragment();

        pagerAdapter.setFragments(fragments);
        pager.setAdapter(pagerAdapter);

        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void initSpinnerSort(Spinner spinnerViewType) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.main_sort, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerViewType.setAdapter(adapter);
    }


    private String[] getCategories() {
        return TEST_CATEGORIES;
    }

}
