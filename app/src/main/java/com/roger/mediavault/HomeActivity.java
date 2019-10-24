package com.roger.mediavault;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.security.Security;
import java.util.ArrayList;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<String> albumLst = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        recyclerView = findViewById(R.id.albumListRecycler);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        albumLst = getAlbumList();
        mAdapter = new AlbumListAdapter(albumLst, new AlbumListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            }
        });
        recyclerView.setAdapter(mAdapter);
        TextView noAlbumLbl = findViewById(R.id.noAlbumLbl);
        if (null != albumLst && albumLst.size() > 0) {
            noAlbumLbl.setVisibility(View.INVISIBLE);
        } else {
            noAlbumLbl.setVisibility(View.VISIBLE);
        }
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setTitle("Enter Album Name");
                final EditText input = new EditText(HomeActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String m_Text = input.getText().toString();
                        if (null != albumLst && albumLst.contains(m_Text)) {
                            Toast.makeText(getApplicationContext(), AppConstants.ALBUM_EXISTS_ERR, Toast.LENGTH_SHORT).show();
                        } else {
                            File folder = getFilesDir();
                            File f = new File(folder + "/" + AppConstants.MEDIA_FOLDER, m_Text);
                            f.mkdir();
                            albumLst.add(albumLst.size(), m_Text);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
    }

    private ArrayList<String> getAlbumList() {
        File folder = getFilesDir();
        File[] files = folder.listFiles();
        ArrayList<String> mediaFolderList = new ArrayList<String>();
        for (File f : files) {
            if (f.isDirectory() && f.getName().equals(AppConstants.MEDIA_FOLDER)) {
                File[] mfList = f.listFiles();
                for (File ff : mfList) {
                    mediaFolderList.add(ff.getName());
                }
                break;
            }
        }
        return mediaFolderList;
    }
}