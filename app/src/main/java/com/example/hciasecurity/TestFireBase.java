package com.example.hciasecurity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Snapshot;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hciasecurity.Logic.MyQuestions;
import com.example.hciasecurity.Logic.Questions.SingleChoice;
import com.example.hciasecurity.Logic.Questions.TrueAndFalse;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;

public class TestFireBase extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fire_base);

        textView=findViewById(R.id.editTextTextMultiLine);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=database.getReference();
        DatabaseReference myRef = database.getReference();

        final TrueAndFalse trueAndFalse =new TrueAndFalse("The question",true,0);
        trueAndFalse.setPoints(15);
        trueAndFalse.setImg_url("the url");
        myRef.child("True and false question").setValue(trueAndFalse);
        myRef.child("True and false question").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                TrueAndFalse trueAndFalse1=snapshot.getValue(TrueAndFalse.class);
                textView.setText(trueAndFalse.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        SingleChoice singleChoice=new SingleChoice("The question", "correct answer",
                new LinkedList<String>(){{
                    add("wrong answer 1");
                    add("wrong answer 2");
                }},0);
        databaseReference.child("my single").setValue(singleChoice);
        final ArrayList<SingleChoice>singleChoices =new ArrayList<>();
        databaseReference.child("my single").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               for(DataSnapshot snapshot1:snapshot.getChildren()){
                   SingleChoice singleChoice=snapshot.getValue(SingleChoice.class);
                   singleChoices.add(singleChoice);
               }
                MyQuestions questions=new MyQuestions();
               questions.setSingleChoices(singleChoices);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}