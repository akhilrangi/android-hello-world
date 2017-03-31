package com.sriyanksiddhartha.filesystemdemo.storageoptions;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sriyanksiddhartha.filesystemdemo.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class ExternalStorageDemo extends AppCompatActivity {

	private EditText etExternalPrivate, etExternalPublic;
	private TextView txvExternalPrivate, txvExternalPublic;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.external_storage);

		etExternalPrivate = (EditText) findViewById(R.id.etExternalPrivate);
		etExternalPublic = (EditText) findViewById(R.id.etExternalPublic);

		txvExternalPrivate = (TextView) findViewById(R.id.txvExternalPrivate);
		txvExternalPublic = (TextView) findViewById(R.id.txvExternalPublic);
	}

	public void saveToExternalPrivate(View view) {
		String data = etExternalPrivate.getText().toString();

		if(isExternalStorageWritable()) {
            File dir = getExternalFilesDir("MyFolder");
            File file = new File(dir, "myExternalPrivate.txt");

            writeToFile(file, data);
        } else {
            Toast.makeText(this,"Ext Media Unavailable",Toast.LENGTH_SHORT).show();
        }
	}

	public void saveToExternalPublic(View view) {
		String data = etExternalPublic.getText().toString();

        if(isExternalStorageWritable()) {
            File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(dir, "myExternalPublic.txt");

            writeToFile(file, data);
        } else {
            Toast.makeText(this,"Ext Media Unavailable",Toast.LENGTH_SHORT).show();
        }
	}

	public void loadFromExternalPrivate(View view) {
        if(isExternalStorageReadable()) {
            File dir = getExternalFilesDir("MyFolder");
            File file = new File(dir, "myExternalPrivate.txt");

            txvExternalPrivate.setText(readFromFile(file));
        } else {
            Toast.makeText(this,"Ext Media Unavailable",Toast.LENGTH_SHORT).show();
        }
	}

	public void loadFromExternalPublic(View view) {
        if(isExternalStorageReadable()) {
            File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(dir, "myExternalPublic.txt");

            txvExternalPublic.setText(readFromFile(file));
        } else {
            Toast.makeText(this,"Ext Media Unavailable",Toast.LENGTH_SHORT).show();
        }
	}

	private void writeToFile(File file, String data){
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(file);
			fos.write(data.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private String readFromFile(File file){
		StringBuilder stringBuilder = new StringBuilder();
		FileInputStream fis = null;

		try {
			fis = new FileInputStream(file);

			int read;
			while ((read = fis.read()) != -1) {
				stringBuilder.append((char)read);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return stringBuilder.toString();
	}

	public boolean isExternalStorageWritable(){
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)){
            return true;
        }
        return false;
    }
    public boolean isExternalStorageReadable(){
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
            return true;
        }
        return false;
    }
}
