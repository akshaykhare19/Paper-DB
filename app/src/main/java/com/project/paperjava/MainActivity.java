package com.project.paperjava;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.project.paperjava.databinding.ActivityMainBinding;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    String storageKey = "UsersList";
    Users[] data = {new Users("jhkj", 6), new Users("kjh",8)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Initialization of Paper DB
        Paper.init(this);

    }


    private void saveData(List<Users> userData) {
        Paper.book().write(storageKey, userData);
    }

    private void readData(String key){
        List<Users> userData = Paper.book().read(key);

        for(Users user: userData){
            binding.dataOutput.setText(user.getName() + " : " + user.getAge() + "\n");
        }

    }


    public void dataAddition(View view) {
        List<Users> info = new ArrayList<>(Arrays.asList(data));
        info.add(new Users(binding.addedName.getText().toString(), Integer.parseInt(binding.addedAge.getText().toString())));
        saveData(info);
        readData(storageKey);
    }

}

class Users {
    private String name;
    private int age;

    public Users(String name, int age){
        this.name = name;
        this.age = age;
    }
//
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String userName){
        this.name = userName;
    }

    public void setAge(int userAge){
        this.age = userAge;
    }

}