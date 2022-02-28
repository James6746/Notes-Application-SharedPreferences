package com.example.notesapplicationsharedpreferencesjava;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;

import java.util.Calendar;
import java.util.Date;

class CustomDialogClass extends Dialog {

    public Context context;
    public Button btn_save, btn_cancel;
    SaveListener listener;
    EditText et_new_note;

    public CustomDialogClass(Context context, SaveListener listener) {
        super(context);
        this.context = context;
        this.listener = listener;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);
        Log.d("@@@@ABC", "Dialog created!");

        btn_save = findViewById(R.id.btn_save);
        btn_cancel = findViewById(R.id.btn_cancel);
        et_new_note = findViewById(R.id.et_new_note);

        btn_save.setEnabled(false);
        btn_save.setBackgroundColor(Color.GRAY);

        et_new_note.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().trim().equals("")){
                    btn_save.setEnabled(false);
                    btn_save.setBackgroundColor(Color.GRAY);
                } else {
                    btn_save.setEnabled(true);
                    btn_save.setBackgroundColor(Color.WHITE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btn_save.setOnClickListener(it -> {
            String str = et_new_note.getText().toString();
            int year = Calendar.getInstance().get(Calendar.SHORT_FORMAT);
            int day = Calendar.getInstance().get(Calendar.DATE);
            int month = Calendar.getInstance().get(Calendar.MONTH);
            int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            int minute = Calendar.getInstance().get(Calendar.MINUTE);

            String date = day + "." + month + 1 + "." + year + "  " + hour + ":" + minute;

            listener.save(date, str);
            dismiss();
        });

        btn_cancel.setOnClickListener(it -> {
            dismiss();
        });

    }

    public interface SaveListener {
        void save(String currentDate, String newNote);
    }
}