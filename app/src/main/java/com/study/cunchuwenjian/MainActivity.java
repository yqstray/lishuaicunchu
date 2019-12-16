package com.study.cunchuwenjian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText etInput;
    TextView tvOutput;
    Button btnSave;
    Button btnLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput=findViewById(R.id.et_input);
        tvOutput=findViewById(R.id.tv_output);
        btnSave=findViewById(R.id.btn_save);
        btnLoad=findViewById(R.id.btn_load);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!etInput.getText().toString().equals("")){
                    save(etInput.getText().toString());
                    Toast.makeText(MainActivity.this,"写入成功！",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"请填写数据！",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dataFromFile=load();
                if(!dataFromFile.equals("")){
                    tvOutput.setText(dataFromFile);
                    Toast.makeText(MainActivity.this,"读取成功！",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"没有找到数据！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void save(String inputText){
        FileOutputStream out=null;
        BufferedWriter writer=null;
        try{
            out=openFileOutput("data", Context.MODE_PRIVATE);
            writer=new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(writer!=null) writer.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public String load(){
        FileInputStream in=null;
        BufferedReader reader=null;
        StringBuilder content=new StringBuilder();
        try{
           in=openFileInput("data");
           reader=new BufferedReader(new InputStreamReader(in));
           String line="";
           while ((line=reader.readLine())!=null){
               content.append(line);
           }
        }catch (IOException e){
            e.printStackTrace();
        }
        return content.toString();
    }
}
