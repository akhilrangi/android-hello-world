package com.sriyanksiddhartha.filesystemdemo.storageoptions;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sriyanksiddhartha.filesystemdemo.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CacheStorageDemo extends AppCompatActivity {

	private EditText etInternalCacheData, etExternalCacheData;
	private TextView txvInternalCacheData, txvExternalCacheData;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cache_storage);

		etInternalCacheData = (EditText) findViewById(R.id.etInternalCacheData);
		etExternalCacheData = (EditText) findViewById(R.id.etExternalCacheData);

		txvInternalCacheData = (TextView) findViewById(R.id.txvInternalCacheContent);
		txvExternalCacheData = (TextView) findViewById(R.id.txvExternalCacheContent);
	}

	public void saveToInternalCache(View view) {

		String cacheData = etInternalCacheData.getText().toString();

		File cacheDir = getCacheDir();
		File myCacheFile = new File(cacheDir, "myInternalCacheFile.txt");

		writeToFile(myCacheFile,cacheData);
	}

	public void saveToExternalCache(View view) {

		String cacheData = etExternalCacheData.getText().toString();

		File cacheDir = getExternalCacheDir();
		File myCacheFile = new File(cacheDir, "myExternalCacheFile.txt");

		writeToFile(myCacheFile,cacheData);
	}

	public void loadFromInternalCache(View view) {

		File cacheDir = getCacheDir();
		File cacheFile = new File(cacheDir, "myInternalCacheFile.txt");

		txvInternalCacheData.setText(readFromFile(cacheFile));
	}

	public void loadFromExternalCache(View view) {

		File cacheDir = getExternalCacheDir();
		File cacheFile = new File(cacheDir, "myExternalCacheFile.txt");

		txvExternalCacheData.setText(readFromFile(cacheFile));
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
}
