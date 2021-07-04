package superky.keytwo.mynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initButtonBack();
        initButtonMain();
        initButtonSettings();
        initButtonInfo();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (id) {
            case R.id.action_settings:
                fragmentTransaction.add(R.id.container_fragment, new SettingsFragment());
                fragmentTransaction.commit();
                return true;
            case R.id.action_main:
                fragmentTransaction.add(R.id.container_fragment, new MainFragment());
                fragmentTransaction.commit();
                return true;
            case R.id.action_search:

                return true;
            case R.id.action_info:
                fragmentTransaction.add(R.id.container_fragment, new InfoFragment());
                fragmentTransaction.commit();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        
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
                    fragmentTransaction.add(R.id.container_fragment, new InfoFragment());
                } else {
                    fragmentTransaction.replace(R.id.container_fragment, new InfoFragment());
                }
                if (Settings.isBackStack) {
                    fragmentTransaction.addToBackStack(null);
                }
                fragmentTransaction.commit();
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
                    fragmentTransaction.add(R.id.container_fragment, new MainFragment());
                } else {
                    fragmentTransaction.replace(R.id.container_fragment, new MainFragment());
                }
                if (Settings.isBackStack) {
                    fragmentTransaction.addToBackStack(null);
                }
                fragmentTransaction.commit();
            }
        });
    }


}