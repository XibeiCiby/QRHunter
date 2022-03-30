package com.example.qrhunter;

import static com.example.qrhunter.MainActivity.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;

public class ScanQRcodeActivity extends AppCompatActivity {

    Button scanbtn;
    Button upload;
    EditText comments;
    public static TextView scantext;
    public int codeworth;
    String data = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_qr_code_layout);

        // initialize the setting of textview, button and database
        scantext = (TextView) findViewById(R.id.scantext);
        scanbtn = (Button) findViewById(R.id.camera_button);
        upload = (Button) findViewById(R.id.upload_comments_button);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        comments = (EditText) findViewById(R.id.editText_comments);
        // set the functionality of switching activity of scan button
        scanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), scannerView.class));


            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = scantext.getText().toString();
                String description = comments.getText().toString();
                codeworth = calculateWorth(scantext.getText().toString());
                SharedPreferences MyProfileData = getSharedPreferences("data", 0);
                String username = MyProfileData.getString("username", null);
                Note note = new Note(title, description,codeworth);
                final CollectionReference collectionReference = db.collection("Player");
                DocumentReference noteRef = db.collection("Player").document(username);
                collectionReference.document(username)
                        .collection("QRCOde").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                String data = "";
                                collectionReference
                                        .document(username)
                                        .collection("QRCode");

                                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                    Note note = documentSnapshot.toObject(Note.class);
                                    note.setDocumentId(documentSnapshot.getId());
                                    String documentId = note.getDocumentId();
                                    String title = note.getTitle();
                                    String description = note.getDescription();

                                    data += "ID: " + documentId
                                            + "\nTitle: " + title + "\nDescription: " + description + "\n\n";
                                }
                                Toast.makeText(ScanQRcodeActivity.this, "Successfully create account", Toast.LENGTH_SHORT).show();


                            }
                        });
                collectionReference.document(username)
                        .collection("QRCOde").add(note);

            }
        });

    }

    public int calculateWorth(String qrCodeContent) {
        try {

            // calculate sha-256
            // Citation: https://stackoverflow.com/questions/5531455/how-to-hash-some-string-with-sha256-in-java
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(qrCodeContent.getBytes(StandardCharsets.UTF_8));

            final StringBuilder hashStr = new StringBuilder(hash.length);
            for (byte hashByte : hash)
                hashStr.append(Integer.toHexString(255 & hashByte));

            // Hashed string here
            final String hashedContent = hashStr.toString();

            final char[] hashedArray = hashedContent.toCharArray();

            // Calculating String
            char[] codeArray = new char[hashedContent.length()];
            int[] intCodeArray = new int[hashedContent.length() + 1];
            int comparer = 0;
            int codeWorth = 0;

            int counter = 0;

            // Copy char by char into array
            for (int i = 0; i < hashedContent.length(); i++) {
                codeArray[i] = hashedContent.charAt(i);
            }

            // hex to int. put this int in the intCodeArray
            // help from https://stackoverflow.com/questions/26839558/hex-char-to-int-conversion
            for (int i = 0; i < hashedContent.length(); i++) {
                if (codeArray[i] >= '0' && codeArray[i] <= '9')
                    intCodeArray[i] = codeArray[i] - '0';
                if (codeArray[i] >= 'A' && codeArray[i] <= 'F')
                    intCodeArray[i] = codeArray[i] - 'A' + 10;
                if (codeArray[i] >= 'a' && codeArray[i] <= 'f')
                    intCodeArray[i] = codeArray[i] - 'a' + 10;
            }

            // used for cases like "1000"; the 16 at the end breaks the counter since its the
            // end of the hash string. And 16 is out of the range of a hex so its safe to use
            intCodeArray[hashedContent.length()] = 16;

            comparer = intCodeArray[0];
            for (int i = 1; i < hashedContent.length() + 1; i++) {
                if (intCodeArray[i] == comparer) {
                    counter += 1;
                } else {
                    if (counter != 0) {
                        if (comparer != 0) {
                            codeWorth += Math.pow(comparer, counter);
                        } else {
                            codeWorth += Math.pow(20, counter);
                        }
                        counter = 0;
                    } else if (comparer == 0) {
                        codeWorth += 1;
                    }
                    comparer = intCodeArray[i];
                }
            }
        return codeWorth;


        } catch (Exception e) {
            Toast.makeText(ScanQRcodeActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            System.err.println(e.getMessage());
        }
        return 0;
    }



}


