package com.sriyanksiddhartha.filesystemdemo.storageoptions;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sriyanksiddhartha.filesystemdemo.R;
import com.sriyanksiddhartha.filesystemdemo.displayscreens.InternalStorageDisplay;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class InternalStorageDemo extends AppCompatActivity {

	private EditText etFileName, etMessage, etFileToDelete;
	private TextView txvInternalStoragePath, txvFilesList;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.internal_storage);

		etFileName 		= (EditText) findViewById(R.id.etFileName);
		etMessage 		= (EditText) findViewById(R.id.etMessage);
		etFileToDelete 	= (EditText) findViewById(R.id.etFileToDelete);

		txvInternalStoragePath 	= (TextView) findViewById(R.id.txvInternalStoragePath);
		txvFilesList 			= (TextView) findViewById(R.id.txvFilesList);
	}

	public void saveToInternalStorage(View view) {

		String fileName = etFileName.getText().toString();
		String messageData = etMessage.getText().toString();

		FileOutputStream fos = null;

		try {
			fos = openFileOutput(fileName, MODE_PRIVATE);
			fos.write(messageData.getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
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

	public void moveToDisplayScreen(View view) {

		Intent intent = new Intent(this, InternalStorageDisplay.class);
		startActivity(intent);
	}

	public void showInternalStoragePath(View view) {

		String path = "" + getFilesDir();
		txvInternalStoragePath.setText(path);
	}

	public void showFilesList(View view) {

		String[] filesList = fileList();

		StringBuilder stringBuilder = new StringBuilder();

		for (String file : filesList) {
			stringBuilder.append(file).append(", ");
		}

		txvFilesList.setText(stringBuilder);
	}

	public void deleteFile(View view) {

		String fileName = etFileToDelete.getText().toString();

		boolean isDeleted = deleteFile(fileName);

		if (isDeleted) {
			Toast.makeText(this, "File Deleted", Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(this, "Failed to Delet", Toast.LENGTH_LONG).show();
		}
	}
}
