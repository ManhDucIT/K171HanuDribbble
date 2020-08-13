package com.androidtraining.k171hanudribbble.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.androidtraining.k171hanudribbble.R;
import com.androidtraining.k171hanudribbble.fragment.ActivityFragment;
import com.androidtraining.k171hanudribbble.fragment.MessagesFragment;
import com.androidtraining.k171hanudribbble.fragment.MoreFragment;
import com.androidtraining.k171hanudribbble.fragment.ShotsFragment;
import com.androidtraining.k171hanudribbble.fragment.UploadFragment;
import com.androidtraining.k171hanudribbble.providers.ServicesProvider;
import com.androidtraining.k171hanudribbble.webservice.inputs.UsersInput;
import com.androidtraining.k171hanudribbble.webservice.models.UserModel;
import com.androidtraining.k171hanudribbble.webservice.outputs.BaseOutput;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.util.List;

import bolts.Continuation;
import bolts.Task;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity {

    private CircleImageView actionbar_avatar;
    public static UserModel user;
    private BottomNavigationView bottom_nav;
    private Toolbar toolbar;
    private TextView actionbar_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //binding Vỉew and initializing Properties
        reflection();

        //set default Fragment
        bottom_nav.setSelectedItemId(R.id.Nav_shots);
        final Fragment defaultFragment = new ShotsFragment();
        FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
        fragmentTransaction1.replace(R.id.fragment_container, defaultFragment).commit();
        actionbar_title.setText("Shots");

        //Check latest Fragment
        if (savedInstanceState != null) {
            try {
                Fragment currentFragment = (Fragment) Class.forName(savedInstanceState.getString("item_selected")).getConstructor().newInstance();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, currentFragment).commit();
                getSupportFragmentManager().beginTransaction().addToBackStack(currentFragment.getClass().getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //Set selected item for default fragment
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                    bottom_nav.setSelectedItemId(R.id.Nav_shots);
                }
            }
        });

        //setting support action bar
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        //Bottom navigation event
        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return itemNavigationBottomEvent(item);
            }
        });

        getUserProfile();
    }

    public void getUserProfile() {
        final ProgressDialog loadingDialog = new ProgressDialog(this);
        loadingDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loadingDialog.setTitle("Loading");
        loadingDialog.setMessage("Loading. Please wait...");
        loadingDialog.setIndeterminate(true);
        loadingDialog.setCanceledOnTouchOutside(false);

        loadingDialog.show();

        UsersInput usersInput = new UsersInput();

        ServicesProvider.getInstance().getDribbbleClient().getUsers(usersInput).continueWith(new Continuation<BaseOutput<UserModel>, Object>() {
            @Override
            public Object then(Task<BaseOutput<UserModel>> task) throws Exception {
                loadingDialog.dismiss();

                final BaseOutput<UserModel> result = task.getResult();

                if (result.getStatusCode() == BaseOutput.STATUS_CODE_OK) {
                    UserModel user = result.getData();
                    MainActivity.this.user = user;
                    Picasso.get().load(user.getAvatar_url()).into(actionbar_avatar);
                }

                return null;
            }
        }, Task.UI_THREAD_EXECUTOR);
    }

    private void reflection() {
        bottom_nav = findViewById(R.id.bottom_nav);
        toolbar = findViewById(R.id.main_activity_toolbar);
        actionbar_title = findViewById(R.id.actionbar_title);
        actionbar_avatar = findViewById(R.id.actionbar_avatar);
    }

    public boolean itemNavigationBottomEvent(MenuItem item) {
        Fragment frg_selected = null;
        switch (item.getItemId()) {
            case R.id.Nav_more:
                frg_selected = new MoreFragment();
                actionbar_title.setText("More");
                break;
            case R.id.Nav_shots:
                frg_selected = new ShotsFragment();
                actionbar_title.setText("Shots");
                break;
            case R.id.Nav_activity:
                frg_selected = new ActivityFragment();
                actionbar_title.setText("Activity");
                break;
            case R.id.Nav_messages:
                frg_selected = new MessagesFragment();
                actionbar_title.setText("Messages");
                break;
            case R.id.Nav_upload:
                frg_selected = new UploadFragment();
                actionbar_title.setText("Upload");
                break;
        }

        // add item to backstack
        if (R.id.Nav_shots != item.getItemId()) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            if (fragmentManager.getBackStackEntryCount() > 0) {
                fragmentManager.popBackStack();
            }
            FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
            fragmentTransaction2.replace(R.id.fragment_container, frg_selected).commit();
            fragmentTransaction2.addToBackStack(frg_selected.getClass().getName());
        } else {
            getSupportFragmentManager().popBackStack();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, frg_selected).commit();
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("item_selected", findCurrentFragment().getClass().getName());
    }

    public Fragment findCurrentFragment() {
        List<Fragment> lstFragment = getSupportFragmentManager().getFragments();
        for (Fragment fragment : lstFragment) {
            if (fragment != null && fragment.isVisible()) {
                return fragment;
            }
        }
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.frg_more_menu, menu);
        return true;
    }
}