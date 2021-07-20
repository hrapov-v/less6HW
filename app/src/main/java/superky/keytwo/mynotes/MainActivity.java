package superky.keytwo.mynotes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

import superky.keytwo.mynotes.observer.Publisher;

public class MainActivity extends AppCompatActivity {

    private Navigation navigation;
    private Publisher publisher = new Publisher();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_main);
        navigation = new Navigation(getSupportFragmentManager());
//        getNavigation().addFragment(SuperNotesFragment.newInstance(), false);
        initView();

    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawerLayout, toolbar, (R.string.open_drawer_menu), (R.string.close_drawer_menu));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        drawerMenuAction(navigationView);
        initButtonBack();
        initButtonMain();
        initButtonSettings();
        initButtonInfo();
    }

    private void drawerMenuAction(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (id) {
                    case R.id.action_settings:
                        fragmentTransaction.replace(R.id.container_fragment, new SettingsFragment());
                        fragmentTransaction.commit();
                        return true;
                    case R.id.action_main:
                        getNavigation().addFragment(SuperNotesFragment.newInstance(), false);
                        return true;
                    case R.id.action_info:
                        infoAlert();
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (id) {
            case R.id.action_settings:
                fragmentTransaction.replace(R.id.container_fragment, new SettingsFragment());
                fragmentTransaction.commit();
                return true;
            case R.id.action_main:
                getNavigation().addFragment(SuperNotesFragment.newInstance(), false);
                return true;
            case R.id.action_search:

                return true;
            case R.id.action_info:
                infoAlert();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        MenuItem menuItemSeacrh = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItemSeacrh.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void initButtonBack() {
        Button buttonBack = findViewById(R.id.btnBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if (Settings.isBackRemove) {
                    List<Fragment> fragmentList = fragmentManager.getFragments();
                    for (int i = 0; i < fragmentList.size(); i++) {
                        Fragment fragment = fragmentList.get(i);
                        if (fragment.isVisible()) {
                            fragmentTransaction.remove(fragment);
                        }
                    }
                } else {
                    fragmentManager.popBackStack();
                }
                fragmentTransaction.commit();
            }
        });
    }

    private void initButtonSettings() {
        Button buttonSettings = findViewById(R.id.btnStgns);
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if (Settings.isDeleteBeforeAdd) {
                    List<Fragment> fragmentList = fragmentManager.getFragments();
                    for (int i = 0; i < fragmentList.size(); i++) {
                        Fragment fragment = fragmentList.get(i);
                        if (fragment.isVisible()) {
                            fragmentTransaction.remove(fragment);
                        }
                    }
                }
                if (Settings.isAddFragment) {
                    fragmentTransaction.add(R.id.container_fragment, new SettingsFragment());
                } else {
                    fragmentTransaction.replace(R.id.container_fragment, new SettingsFragment());
                }
                if (Settings.isBackStack) {
                    fragmentTransaction.addToBackStack(null);
                }
                fragmentTransaction.commit();
            }
        });
    }

    private void initButtonInfo() {
        Button buttonInfo = findViewById(R.id.btnInfo);
        buttonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoAlert();
            }
        });
    }

    private void initButtonMain() {
        Button buttonMain = findViewById(R.id.btnMain);
        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if (Settings.isDeleteBeforeAdd) {
                    List<Fragment> fragmentList = fragmentManager.getFragments();
                    for (int i = 0; i < fragmentList.size(); i++) {
                        Fragment fragment = fragmentList.get(i);
                        if (fragment.isVisible()) {
                            fragmentTransaction.remove(fragment);
                        }
                    }
                }
                if (Settings.isAddFragment) {
                    getNavigation().addFragment(SuperNotesFragment.newInstance(), false);

                } else {
                    fragmentTransaction.replace(R.id.container_fragment, SuperNotesFragment.newInstance());
                }
                if (Settings.isBackStack) {
                    fragmentTransaction.addToBackStack(null);
                }
                fragmentTransaction.commit();
            }
        });
    }

    public Navigation getNavigation() {
        return navigation;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    //?
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void infoAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Информация")
                .setMessage("My code: https://github.com/hrapov-v/less6HW")
                .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}