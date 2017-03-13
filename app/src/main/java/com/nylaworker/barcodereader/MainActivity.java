package com.nylaworker.barcodereader;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity implements OnClickListener{
    private Button scanBtn;
    private TextView formatTxt, contentTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // I think this is creating the scanner capacities of the app. This is calling the two classes that I bet I have to download
        scanBtn = (Button)findViewById(R.id.scan_button);
        formatTxt = (TextView)findViewById(R.id.scan_format);
        contentTxt = (TextView)findViewById(R.id.scan_content);

        scanBtn.setOnClickListener(this);


    }
    public void onClick(View v){
        //responds to clicks
        if(v.getId()==R.id.scan_button){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if(scanningResult != null){
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            formatTxt.setText("one:" + scanFormat );
            contentTxt.setText("CONTENT:"+ scanContent);
//            if(scanContent == "068437389716" ){
//                contentTxt.setText("CONTENT:"+"Brookside Dark Chocolate. Sorry there is no information about this product");
//            }
//            else{
//                formatTxt.setText("Sorry no info was found" + " one: " + scanFormat);
//                contentTxt.setText("CONTENT:"+ scanContent);

            //}


        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No Scan Data received ", Toast.LENGTH_SHORT);
            toast.show();

        }
    }

}
