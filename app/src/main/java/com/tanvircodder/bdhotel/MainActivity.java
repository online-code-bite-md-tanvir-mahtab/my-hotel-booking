package com.tanvircodder.bdhotel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.installations.local.PersistedInstallationEntry;
import com.tanvircodder.bdhotel.network.JsonParsing;
import com.tanvircodder.bdhotel.util.NetworkUtil;
import com.tanvircodder.bdhotel.util.Utility;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List> , ListAdapter.ListItemClickListener {
    private static final int LOADER_ID = 0;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    public static final int RC_SIGN_IN = 1;
//   creating the instance of the recycerView and listAdapter..//
    private RecyclerView mRecyclerView;
    private ListAdapter mAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ListAdapter(this,this);
        mRecyclerView.setAdapter(mAdapter);
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    Toast.makeText(MainActivity.this,"Your signed in",Toast.LENGTH_SHORT).show();
                }else {
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setProviders(
                                            AuthUI.EMAIL_PROVIDER,
                                            AuthUI.GOOGLE_PROVIDER)
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };
        getSupportLoaderManager().initLoader(LOADER_ID,null,this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAuthStateListener != null){
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    @NonNull
    @Override
    public Loader<List> onCreateLoader(int id, @Nullable Bundle args) {
        return new AsyncTaskLoader<List>(this) {
            private List<Utility> mData;
            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                if (mData != null){
                    deliverResult(mData);
                }else {
                    forceLoad();
                }
            }

            @Nullable
            @Override
            public List loadInBackground() {
                try {
                    URL url = NetworkUtil.buildUrl();
                    String request = NetworkUtil.URLHttpRequest(url);
                    List<Utility> jsonResponse = JsonParsing.jsonParsing(MainActivity.this,request);
                    Log.e(LOG_TAG,"The response : "+jsonResponse);
                    return jsonResponse;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            public void deliverResult(List<Utility> data){
                mData = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List> loader, List data) {
        mAdapter.swapData(data);

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List> loader) {
        mAdapter.swapData(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sign_out,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.sign_in){
            AuthUI.getInstance().signOut(this);
        }

        return super.onOptionsItemSelected(item);
    }





    @Override
    public void onListItemClick(int clickedItemIndex) {
        Toast.makeText(this,"The position : " + clickedItemIndex,Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(MainActivity.this,BokkingActivity.class);

        Bundle bundle = new Bundle();
        bundle.putInt("heotel_id",clickedItemIndex);
//        startActivity(intent);
        Intent intent = new Intent(this,DetailActivity.class);
//        nwo i am going to put the id humber into intent..//
        intent.putExtras(bundle);
        startActivity(intent);
    }


}