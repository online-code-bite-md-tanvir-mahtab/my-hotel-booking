package com.tanvircodder.bdhotel;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BookingActivity extends AppCompatActivity {
    EditText mEmailEdiText;
    EditText mFirstNuberEditText;
    EditText mSecondNumberText;
    EditText mOcupationEditTextView;
    EditText mAdultEditText;
    EditText mChildrenText;
    EditText mRoomTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

//        nwo i am going to find the view.//
        mEmailEdiText = (EditText) findViewById(R.id.email_booking);
        mFirstNuberEditText = (EditText) findViewById(R.id.first_number);
        mSecondNumberText  = (EditText) findViewById(R.id.second_number);
        mOcupationEditTextView = (EditText) findViewById(R.id.ocupation_text);
        mAdultEditText  = (EditText) findViewById(R.id.adult);
        mChildrenText = (EditText) findViewById(R.id.children);
        mRoomTextView = (EditText) findViewById(R.id.room);
        getSupportActionBar().setTitle("Booking hotel page");
    }
    public void sendToClick(View view){
        Intent intent =getIntent();
        int nameIndex =intent.getIntExtra("romm_no",0);
        String romTken = intent.getStringExtra("token_no");
        String email= mEmailEdiText.getText().toString();
        String number = mFirstNuberEditText.getText().toString() + mSecondNumberText.getText().toString();
        String ocupation = mOcupationEditTextView.getText().toString();
        String adult = mAdultEditText.getText().toString();
        String children = mChildrenText.getText().toString();

//        nwo i am going to pass the string and index value to the method..///
        validationCheck(email,number,ocupation,adult,children,nameIndex,romTken);


    }

    private void validationCheck(String email, String number, String ocupation, String adult, String children, int nameIndex,String romToken) {
//        now i  am going to create conditional statement..//
//        that will check every data is empty of not..//
        if ((email==null && number == null && ocupation == null && nameIndex == 0)||( adult == null || children == null) ){
            Toast.makeText(getApplicationContext(),"Some table is still empty",Toast.LENGTH_SHORT).show();
        }else {
//             if the value isn't null or empty then the email app will trigger with the current information
            SendinTheMail(email,number,ocupation,adult,children,nameIndex,romToken);
        }
    }

    /*
    *now I ma going to create an method that will hold the data of the
    * intent of the emil sending method
    */
    private void SendinTheMail(String email, String number, String ocupation, String adult, String children, int nameIndex,String romToken){
        Intent intent1 = new Intent(Intent.ACTION_SENDTO);
        intent1.setData(Uri.parse("mailto:tanzin736@gmail.com"));
        intent1.putExtra(Intent.EXTRA_EMAIL,email);
        intent1.putExtra(Intent.EXTRA_SUBJECT,nameIndex+"No room for booking");
        intent1.putExtra(Intent.EXTRA_TEXT,"Number:"+number+"Occupation:"+ocupation+"Adult:" + adult+"Children:" +children+ "price range:" + romToken + "% off");
        if (intent1.resolveActivity(getPackageManager()) != null) {
            startActivity(intent1);
        }
    }
}