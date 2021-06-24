package digi.coders.mho.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import digi.coders.mho.R;

public class TransactionHistoryActivity extends AppCompatActivity {

    Toolbar toolbarl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);

        toolbarl=findViewById(R.id.toolbar);

    }
}