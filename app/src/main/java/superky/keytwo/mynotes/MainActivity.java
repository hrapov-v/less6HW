package superky.keytwo.mynotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
        Button buttonBack = findViewById(R.id.btnBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Button buttonMain = findViewById(R.id.btnMain);
        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if(Settings.isDeleteBeforeAdd) {
                    List<Fragment> fragmentList = fragmentManager.getFragments();
                    for(int i = 0; i < fragmentList.size(); i++) {
                        Fragment fragment = fragmentList.get(i);
                        if(fragment.isVisible()) {
                            fragmentTransaction.remove(fragment);
                        }
                    }
                }
                //if(Settings.isAddFragment) {
                    fragmentTransaction.add(R.id.container_fragment, new MainFragment());
                //}
                fragmentTransaction.commit();
            }
        });
        Button buttonSettings = findViewById(R.id.btnStgns);
        Button buttonInfo = findViewById(R.id.btnInfo);

    }
}