package com.ibo.soundcontrol.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ibo.soundcontrol.R;
import com.ibo.soundcontrol.model.SeekBar;
import com.ibo.soundcontrol.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private SeekBar equalizer;
    private List<User> users = new ArrayList<>();
    private Spinner spinnerUsers;
    private ArrayAdapter<String> adapter;
    private int currentUserIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        equalizer = new SeekBar();
        //textView = findViewById(R.id.textView3);
        spinnerUsers = findViewById(R.id.spinnerUsers);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getUserNames());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUsers.setAdapter(adapter);
        spinnerUsers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentUserIndex = position;
                updateUserDisplay();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        android.widget.SeekBar seekBar1 = findViewById(R.id.seekBar1);
        android.widget.SeekBar seekBar2 = findViewById(R.id.seekBar2);
        android.widget.SeekBar seekBar3 = findViewById(R.id.seekBar3);
        android.widget.SeekBar seekBar4 = findViewById(R.id.seekBar4);
        android.widget.SeekBar seekBar5 = findViewById(R.id.seekBar5);

        // Inicializa as variáveis com os valores atuais das SeekBars
        equalizer.setSeekBarValue(0, seekBar1.getProgress());
        equalizer.setSeekBarValue(1, seekBar2.getProgress());
        equalizer.setSeekBarValue(2, seekBar3.getProgress());
        equalizer.setSeekBarValue(3, seekBar4.getProgress());
        equalizer.setSeekBarValue(4, seekBar5.getProgress());

        // Configura os listeners para cada SeekBar
        setSeekBarListener(seekBar1, 0);
        setSeekBarListener(seekBar2, 1);
        setSeekBarListener(seekBar3, 2);
        setSeekBarListener(seekBar4, 3);
        setSeekBarListener(seekBar5, 4);


        Button buttonCreateUser = findViewById(R.id.buttonCreateUser);
        buttonCreateUser.setOnClickListener(v -> createUser());
        createUser();

        Button buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(v -> saveUser());
    }

    private void saveUser(){

        User currentUser = users.get(currentUserIndex);

        if(Objects.equals(currentUser.getUserName(), "Default")){
            Toast.makeText(getApplicationContext(), "Não é possível alterar o perfil Default", Toast.LENGTH_LONG).show();
            return;
        }

        currentUser.setOption1(equalizer.getSeekBarValue(0));
        currentUser.setOption2(equalizer.getSeekBarValue(1));
        currentUser.setOption3(equalizer.getSeekBarValue(2));
        currentUser.setOption4(equalizer.getSeekBarValue(3));
        currentUser.setOption5(equalizer.getSeekBarValue(4));

        Toast.makeText(getApplicationContext(), "Salvo com sucesso", Toast.LENGTH_SHORT).show();

        Log.i("userseekbar", "nome do usuario: " + currentUser.getUserName());
        Log.i("userseekbar", "valor1: " + currentUser.getOption1());
        Log.i("userseekbar", "valor2: " + currentUser.getOption2());
        Log.i("userseekbar", "valor3: " + currentUser.getOption3());
        Log.i("userseekbar", "valor4: " + currentUser.getOption4());
        Log.i("userseekbar", "valor5: " + currentUser.getOption5());

    }
    private void setSeekBarListener(android.widget.SeekBar seekBar, int index) {
        seekBar.setOnSeekBarChangeListener(new android.widget.SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(android.widget.SeekBar seekBar, int progress, boolean fromUser) {

                equalizer.setSeekBarValue(index, progress);

            }

            @Override
            public void onStartTrackingTouch(android.widget.SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(android.widget.SeekBar seekBar) {
            }
        });
    }
    private List<String> getUserNames() {
        List<String> names = new ArrayList<>();
        for (User user : users) {
            names.add(user.getUserName());
        }
        return names;
    }
    private void createUser() {
        User newUser = new User();
        if (users.isEmpty()){
            newUser.setUserName("Default");}
        else
            newUser.setUserName("User" + (users.size()));
        users.add(newUser);
        adapter.add(newUser.getUserName());
        adapter.notifyDataSetChanged();
        spinnerUsers.setSelection(users.size() - 1);
    }
    private void updateUserDisplay() {
        User currentUser = users.get(currentUserIndex);
        Log.i("userseekbar", "nome do usuario: " + currentUser.getUserName());

        android.widget.SeekBar seekBar1 = findViewById(R.id.seekBar1);
        android.widget.SeekBar seekBar2 = findViewById(R.id.seekBar2);
        android.widget.SeekBar seekBar3 = findViewById(R.id.seekBar3);
        android.widget.SeekBar seekBar4 = findViewById(R.id.seekBar4);
        android.widget.SeekBar seekBar5 = findViewById(R.id.seekBar5);

        seekBar1.setProgress(currentUser.getOption1());
        seekBar2.setProgress(currentUser.getOption2());
        seekBar3.setProgress(currentUser.getOption3());
        seekBar4.setProgress(currentUser.getOption4());
        seekBar5.setProgress(currentUser.getOption5());

        Log.i("userseekbar", "valor1: " + currentUser.getOption1());
        Log.i("userseekbar", "valor2: " + currentUser.getOption2());
        Log.i("userseekbar", "valor3: " + currentUser.getOption3());
        Log.i("userseekbar", "valor4: " + currentUser.getOption4());
        Log.i("userseekbar", "valor5: " + currentUser.getOption5());
    }
}