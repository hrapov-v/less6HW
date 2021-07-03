package superky.keytwo.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        Button buttonSettings = findViewById(R.id.btnStgns);
        Button buttonInfo = findViewById(R.id.btnInfo);

    }
}