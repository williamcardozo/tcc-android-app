package com.example.pokedex.pokedex;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.pokedex.pokedex.fragments.ListFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private static final String KANTO_TAG = "Kanto",
                                JOHTO_TAG = "Johto",
                                HOENN_TAG = "Hoenn",
                                SINNOH_TAG = "Sinnoh";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        configureToolbar();
        configureNavigationDrawer();
        show(KANTO_TAG);
    }

    private void configureNavigationDrawer() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
    }

    private void configureToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        menuItem.setChecked(true);
        switch (itemId) {
            case R.id.item_kanto:
                show(KANTO_TAG);
                break;
            case R.id.item_johto:
                show(JOHTO_TAG);
                break;
            case R.id.item_hoenn:
                show(HOENN_TAG);
                break;
            case R.id.item_sinnoh:
                show(SINNOH_TAG);
                break;
        }

        mDrawerLayout.closeDrawers();
        return true;
    }

    private void show(String tag) {
        FragmentManager manager = getSupportFragmentManager();
        ListFragment fragment = (ListFragment)manager.findFragmentByTag(tag);

        if(fragment == null) {
            DrawerOption option = DrawerOption.KANTO;

            switch (tag) {
                case KANTO_TAG:
                    option = DrawerOption.KANTO;
                    break;
                case JOHTO_TAG:
                    option = DrawerOption.JOHTO;
                    break;
                case HOENN_TAG:
                    option = DrawerOption.HOENN;
                    break;
                case SINNOH_TAG:
                    option = DrawerOption.SINNOH;
                    break;
            }

            fragment = ListFragment.newInstance(option);
        }

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, fragment, tag);
        setTitle(tag);
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
