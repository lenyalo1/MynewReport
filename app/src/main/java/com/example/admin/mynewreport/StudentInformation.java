package com.example.admin.mynewreport;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentInformation extends AppCompatActivity {

    EditText editText_Full_Names,editText_Surname,
            editText_Id,editText_Nationality,
            editText_Gender,editText_Language,editText_Contacts,
            editText_Next_Of_Kin,editText_Next_Of_Kin_Contacts,
            editText_Email,editText_Physical_Address,editText_Grade;
    Button btn_InsertData,btn_View,btn_Update,btn_Delete;

    DatabaseHelper db;
    private android.widget.SearchView SearchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentinformation);


        //casting Edit Text
        editText_Full_Names=(EditText)findViewById(R.id.edit_Full_Name);
        editText_Surname=(EditText)findViewById(R.id.edit_Surname);
        editText_Id=(EditText)findViewById(R.id.edit_ID);
        editText_Nationality=(EditText)findViewById(R.id.edit_Nationality);
        editText_Gender=(EditText)findViewById(R.id.edit_Gender);
        editText_Language=(EditText)findViewById(R.id.edit_Language);
        editText_Contacts=(EditText)findViewById(R.id.edit_Contacts);
        editText_Next_Of_Kin=(EditText)findViewById(R.id.edit_Next_Of_Keen);
        editText_Next_Of_Kin_Contacts=(EditText)findViewById(R.id.edit_Next_Keen_Contact);
        editText_Email=(EditText)findViewById(R.id.edit_Email);
        editText_Physical_Address=(EditText)findViewById(R.id.edit_Physical_Address);
        editText_Grade=(EditText)findViewById(R.id.edit_Grade);


        SearchView = (android.widget.SearchView)findViewById(R.id.search);



        //casting the Buttons

        btn_InsertData=(Button)findViewById(R.id.btn_InsertData);
        btn_View=(Button)findViewById(R.id.btn_View);
        btn_Update=(Button)findViewById(R.id.btn_Update);
        btn_Delete=(Button)findViewById(R.id.btn_Delete);

        db =new DatabaseHelper(this);
        btn_InsertData();
        btn_View();
        btn_Update();
        btn_Delete();

    }

    public void   btn_Delete() {
        btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deleteRows =
                        db.deleteData(editText_Id.getText().toString());
                if (deleteRows > 0){
                    Toast.makeText(StudentInformation.this,"Data Deleted" ,Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(StudentInformation.this,"Data not Deleted", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public  void btn_Update() {
        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdated = db.UpdateData(editText_Id.getText().toString(),editText_Full_Names.getText().toString(),
                        editText_Surname.getText().toString(), editText_Nationality.getText().toString(), editText_Gender.getText().toString(),
                        editText_Language.getText().toString(), editText_Contacts.getText().toString(), editText_Next_Of_Kin.getText().toString(),
                        editText_Next_Of_Kin_Contacts.getText().toString(), editText_Email.getText().toString(), editText_Physical_Address.getText().toString(),
                        editText_Grade.getText().toString());


                if (isUpdated == true) {
                    Toast.makeText(StudentInformation.this, "Update Successful", Toast.LENGTH_LONG).show();
                } else

                    Toast.makeText(StudentInformation.this, "Update failed", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void btn_View() {
        btn_View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor results = db.GetAllData();
                if (results.getCount() == 0) {

                    //show message
                    showMessage("Error ", "No Data found");

                    return;

                }
                StringBuffer buffer = new StringBuffer();
                while (results.moveToNext()) {
                    buffer.append(" ID :" + results.getString(0) + "\n");
                    buffer.append(" Full_Names :" + results.getString(1) + "\n");
                    buffer.append(" Surname :" + results.getString(2) + "\n");
                    buffer.append(" Nationality :" + results.getString(3) + "\n");
                    buffer.append(" Gender :" + results.getString(4) + "\n");
                    buffer.append(" Language :" + results.getString(5) + "\n");
                    buffer.append(" Contacts:" + results.getString(6) + "\n");
                    buffer.append(" Next_Of_Kin :" + results.getString(7) + "\n");
                    buffer.append(" Next_Of_Kin_Contacts :" + results.getString(8) + "\n");
                    buffer.append(" Email :" + results.getString(9) + "\n");
                    buffer.append(" Physical_Address :" + results.getString(10) + "\n");
                    buffer.append(" Grade :" + results.getString(11) + "\n");
                }
                showMessage("Data",buffer.toString());
            }
        });
    }


    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }

    public void btn_InsertData() {
        btn_InsertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted =
                        db.insert(editText_Full_Names.getText().toString(),
                                editText_Surname.getText().toString(),editText_Nationality.getText().toString(),
                                editText_Gender.getText().toString(),editText_Language.getText().toString(),
                                editText_Contacts.getText().toString(),editText_Next_Of_Kin.getText().toString(),
                                editText_Next_Of_Kin_Contacts.getText().toString(),editText_Email.getText().toString(),
                                editText_Physical_Address.getText().toString(),editText_Grade.getText().toString());

                if (isInserted == true){

                    Toast.makeText(StudentInformation.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                }else{

                    Toast.makeText(StudentInformation.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public boolean onQueryTextSubmit(String query) {

        int id = Integer.parseInt(query);
        Cursor c = db.StudentInformation(id);
        if(c.getCount()> 0) {
            editText_Id.setText(c.getString(c.getColumnIndex("ID")));
            editText_Full_Names.setText(c.getString(c.getColumnIndex("FULL_NAMES")));
            editText_Surname.setText(c.getString(c.getColumnIndex("SURNAME")));
            editText_Nationality.setText(c.getString(c.getColumnIndex("NATIONALITY")));
            editText_Gender.setText(c.getString(c.getColumnIndex("GENDER")));
            editText_Language.setText(c.getString(c.getColumnIndex("LANGUAGE")));
            editText_Contacts.setText(c.getString(c.getColumnIndex("CONTACTS")));
            editText_Next_Of_Kin.setText(c.getString(c.getColumnIndex("NEXT_OF_KIN")));
            editText_Next_Of_Kin_Contacts.setText(c.getString(c.getColumnIndex("NEXT_OF_KIN_CONTACTS")));
            editText_Email.setText(c.getString(c.getColumnIndex("EMAIL")));
            editText_Physical_Address.setText(c.getString(c.getColumnIndex("PHYSICAL_ADDRESS")));
            editText_Grade.setText(c.getString(c.getColumnIndex("GRADE")));
            return true;
        }
        return false;
    }
}
