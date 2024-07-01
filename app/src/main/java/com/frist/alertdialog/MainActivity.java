// MainActivity.java
package com.frist.alertdialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button queryButton = findViewById(R.id.queryButton);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQueryDialog();
            }
        });
    }

    private void showQueryDialog() {
        // Inflate the dialog with custom view
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_query, null);

        final EditText editTextName = dialogView.findViewById(R.id.editTextName);
        final EditText editTextEmail = dialogView.findViewById(R.id.editTextEmail);
        final EditText editTextQuery = dialogView.findViewById(R.id.editTextQuery);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView)
                .setTitle("Submit Query")
                .setPositiveButton("Submit", null)
                .setNegativeButton("Cancel", null);

        final AlertDialog dialog = builder.create();

        dialog.setOnShowListener(dialogInterface -> {
            Button submitButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            submitButton.setOnClickListener(view -> {
                String name = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();
                String query = editTextQuery.getText().toString();

                if (name.isEmpty() || email.isEmpty() || query.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Perform your query submission logic here

                    Toast.makeText(MainActivity.this, "Query sent successfully\n" +
                            "Name: " + name + "\nEmail: " + email + "\nQuery: " + query, Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            });

            Button cancelButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
            cancelButton.setOnClickListener(view -> dialog.dismiss());
        });

        dialog.show();
    }
}
