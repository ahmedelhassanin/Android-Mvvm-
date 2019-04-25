package com.example.mvvmex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class addNoteActivity extends AppCompatActivity {

    public static final String EXTRA_ID =
            "com.codinginflow.architectureexample.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "com.codinginflow.architectureexample.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.codinginflow.architectureexample.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY =
            "com.codinginflow.architectureexample.EXTRA_PRIORITY";

    private EditText editTexttittle;
    private EditText editTextdes;
    private NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        editTexttittle=(EditText)findViewById(R.id.text_tittle);
        editTextdes=(EditText)findViewById(R.id.txt_des);
        numberPicker=(NumberPicker)findViewById(R.id.num_piker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent=getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            editTexttittle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextdes.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPicker.setValue(intent.getIntExtra(EXTRA_PRIORITY,1));

            setTitle("Edit Note");
        }else {
            setTitle("Add Note");
        }
    }

    private void savenote(){
        String title=editTexttittle.getText().toString();
        String des=editTextdes.getText().toString();
        int priority=numberPicker.getValue();
        if (title.trim().isEmpty()|| des.trim().isEmpty()){

            Toast.makeText(this,"please insert a title and description",Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data=new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_DESCRIPTION,des);
        data.putExtra(EXTRA_PRIORITY,priority);
        int id=getIntent().getIntExtra(EXTRA_ID,-1);
        if (id !=1){
            data.putExtra(EXTRA_ID,id);

        }
        setResult(RESULT_OK,data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.save_note:
                savenote();
                return true;
                default:
                    return super.onOptionsItemSelected(item);

        }

    }
}
