package com.example.hciasecurity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Snapshot;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hciasecurity.Logic.MyQuestions;
import com.example.hciasecurity.Logic.Questions.MultipleChoice;
import com.example.hciasecurity.Logic.Questions.SingleChoice;
import com.example.hciasecurity.Logic.Questions.TrueAndFalse;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;

public class TestFireBase extends AppCompatActivity {
    TextView textView,textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fire_base);

        textView=findViewById(R.id.editTextTextMultiLine);
        textView2=findViewById(R.id.textView2);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=database.getReference();
        DatabaseReference myRef = database.getReference();

//        final TrueAndFalse trueAndFalse =new TrueAndFalse("The question",true,0);
//        trueAndFalse.setPoints(15);
//        trueAndFalse.setImg_url("the url");
//        myRef.child("True and false question").setValue(trueAndFalse);

        final ArrayList<TrueAndFalse>trueAndFalses =new ArrayList<>();

        myRef.child("True and False question").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1:snapshot.getChildren()) {
                    TrueAndFalse trueAndFalse1=snapshot1.getValue(TrueAndFalse.class);
                    trueAndFalses.add(trueAndFalse1);
                }
                MyQuestions questions=new MyQuestions();
                questions.setTrueAndFalses(trueAndFalses);
//                textView.setText(questions.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        final SingleChoice singleChoice=new SingleChoice("The question", "correct answer",
//                new LinkedList<String>(){{
//                    add("wrong answer 1");
//                    add("wrong answer 2");
//                }},0);
//
//        databaseReference.child("my single").setValue(singleChoice);

        final ArrayList<SingleChoice>singleChoices =new ArrayList<>();

        databaseReference.child("Single Question").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               for(DataSnapshot snapshot1:snapshot.getChildren()){
                   SingleChoice singleChoice=snapshot1.getValue(SingleChoice.class);
                   singleChoices.add(singleChoice);
               }
                MyQuestions questions=new MyQuestions();
                questions.setSingleChoices(singleChoices);
                textView2.setText(questions.toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        final MultipleChoice multipleChoice = new MultipleChoice("The Question", new LinkedList<String>(){
//            {
//                add("correct 1");
//                add("correct 2");
//            }
//        }, new LinkedList<String>(){
//            {
//                add("wrong 1");
//                add("wrong 2");
//            }
//        }, 0);
//
//        databaseReference.child("Multi").setValue(multipleChoice);

        final ArrayList<MultipleChoice> multipleChoices = new ArrayList<>();

        databaseReference.child("Multiple Question").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1:snapshot.getChildren()) {
                    MultipleChoice multipleChoice1 = snapshot1.getValue(MultipleChoice.class);
                    multipleChoices.add(multipleChoice1);
                }
                MyQuestions questions = new MyQuestions();
                questions.setMultipleChoices(multipleChoices);
                textView.setText(questions.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}