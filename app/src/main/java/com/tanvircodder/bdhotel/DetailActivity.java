package com.tanvircodder.bdhotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



import java.util.Random;


public class DetailActivity extends AppCompatActivity {
    TextView mTokenTextView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent= getIntent();
        getSupportActionBar().setTitle("Hotel details page");
    }

    public  void jenarateTheToken(View view){
        mTokenTextView = (TextView) findViewById(R.id.detail_hotel_token);
        Random newRandom = new Random();
        Intent intent =getIntent();
        int nameIndex =intent.getIntExtra("heotel_id",0);
        int random_number = newRandom.nextInt(nameIndex + 100);
        mTokenTextView.setText(String.valueOf(random_number) + "%");
    }
    public void bookOnClick(View view){
        Intent intent =getIntent();
        int nameIndex =intent.getIntExtra("heotel_id",0);
        String tokenNo = mTokenTextView.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putInt("romm_no",nameIndex);
        bundle.putString("token_no",tokenNo);
        Intent newIntent = new Intent(getApplicationContext(),BookingActivity.class);
        newIntent.putExtras(bundle);
        startActivity(newIntent);
    }
}