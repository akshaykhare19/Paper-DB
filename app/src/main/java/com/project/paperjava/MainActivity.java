package com.project.paperjava;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.project.paperjava.databinding.ActivityMainBinding;
import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    String data = "";
    String storageKey = "UsersList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Initialization of Paper DB
        Paper.init(this);

        initDb();

    }

    private void initDb() {
        Paper.book().write(storageKey, data);
    }


    private void addData(String info) {
        data = data.concat(info);
        saveData(data);
        readData();
    }

    private void readData() {
        //Paper DB method to read data
        String info = Paper.book().read(storageKey);
        binding.dataOutput.setText(info);
    }

    private void saveData(String info) {
        //Paper DB method to save data
        Paper.book().write(storageKey, info);
    }

    public void dataAddition(View view) {
        String newData = binding.addedText.getText().toString();
        if(Paper.book().contains(storageKey)){
            addData("\n" + newData);
        }
        binding.addedText.setText("");
    }
}