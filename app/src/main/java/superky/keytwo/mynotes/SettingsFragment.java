package superky.keytwo.mynotes;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        initSwitchBackStack(view);
        initSwitchBackAsRemove(view);
        initSwitchDeleteBeforeAdd(view);
        initRadioAdd(view);
        initRadioReplace(view);


    }

    //отвечает за замену фрагментов
    private void initRadioReplace(View view) {
        RadioButton radioButtonRplc = view.findViewById(R.id.radioBtnReplace);
        radioButtonRplc.setChecked(Settings.isAddFragment);
        radioButtonRplc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Settings.isAddFragment = isChecked;
                saveSettings();
            }
        });

    }


    //отвечает за добавлениен фрагментов
    private void initRadioAdd(View view) {
        RadioButton radioButtonAdd = view.findViewById(R.id.radioBtnAdd);
        radioButtonAdd.setChecked(Settings.isAddFragment);
        radioButtonAdd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Settings.isAddFragment = isChecked;
                saveSettings();
            }
        });
    }

    //переключатель который отвечает за удаление фрагмента перед добавлением фрагмента.
    private void initSwitchDeleteBeforeAdd(View view) {
        SwitchCompat switchCompatDeleteBeforeAdd = view.findViewById(R.id.swichBackDeleteBeforeAdd);
        switchCompatDeleteBeforeAdd.setChecked(Settings.isDeleteBeforeAdd);
        switchCompatDeleteBeforeAdd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Settings.isDeleteBeforeAdd = isChecked;
                saveSettings();
            }
        });
    }

    //переключатель который отвечает за кнопку Back
    private void initSwitchBackAsRemove(View view) {
        SwitchCompat switchCompatBackAsRemove = view.findViewById(R.id.swichBackAsRemove);
        switchCompatBackAsRemove.setChecked(Settings.isBackRemove);
        switchCompatBackAsRemove.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Settings.isBackRemove = isChecked;
                saveSettings();
            }
        });
    }

    //переключатель который отвечает за стек
    private void initSwitchBackStack(View view) {
        SwitchCompat switchCompatBackStack = view.findViewById(R.id.swichBackStack);
        switchCompatBackStack.setChecked(Settings.isBackStack);
        switchCompatBackStack.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Settings.isBackStack = isChecked;
                saveSettings();
            }
        });
    }

    //сохраняем настройки
    private void saveSettings() {
        SharedPreferences shared = requireActivity().getSharedPreferences(Settings.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putBoolean(Settings.IS_BACK_STACK_USED, Settings.isBackStack);
        editor.putBoolean(Settings.IS_DELETE_FRAGMENT_BEFORE_ADD, Settings.isDeleteBeforeAdd);
        editor.putBoolean(Settings.IS_BACK_AS_REMOVE_FRAGMENT, Settings.isBackRemove);
        editor.putBoolean(Settings.IS_ADD_FRAGMENT_USED, Settings.isAddFragment);
        editor.apply();
    }
}