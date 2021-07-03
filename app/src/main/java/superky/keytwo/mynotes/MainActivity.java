package superky.keytwo.mynotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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
        initButtonBack();
        initButtonMain();
        initButtonSettings();
        initButtonInfo();
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