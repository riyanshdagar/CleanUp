package www.testing.cleanup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Requestpage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_page);

        Spinner spinner = (Spinner) findViewById(R.id.spinner11);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.None, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);



    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String choice = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getApplicationContext(), choice, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void buttonSendEmail (View view){

        try {
            EditText stringSenderEmail = (EditText) findViewById(R.id.el);
            String stringReceiverEmail = "rd8386@srmist.edu.in";
            EditText PasswordSenderEmail = (EditText) findViewById(R.id.pd);
            EditText name = (EditText) findViewById(R.id.ne);
            EditText hostelname = (EditText) findViewById(R.id.hn);
            EditText roomnumber = (EditText) findViewById(R.id.rn);
            EditText phonenumber = (EditText) findViewById(R.id.pn);
            EditText description = (EditText) findViewById(R.id.desc);

            Spinner spinner = (Spinner) findViewById(R.id.spinner11);

            String stringHost = "smtp.gmail.com";

            Properties properties = System.getProperties();

            properties.put("mail.smtp.host", stringHost);
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");

            javax.mail.Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(stringSenderEmail.getText().toString(), PasswordSenderEmail.getText().toString());
                }
            });

            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(stringReceiverEmail));


            if(spinner.getSelectedItemPosition() == 1) {
                mimeMessage.setSubject("Subject: Complaint regarding " + spinner.getSelectedItem().toString());
                mimeMessage.setText("Name = " + name.getText().toString() + "\n" + "Hostel Name = " + hostelname.getText().toString() + "\n" + "Room Number = " + roomnumber.getText().toString() + "\n" + "With due respect this is to inform you that the problem I'm facing is regarding = " + spinner.getSelectedItem().toString() + "." + "\n" + "What problem are you facing = " + description.getText().toString() + "\n" + "Thank You" + "\n" + name.getText().toString() + "\n" + "Phone Number = +91-" + phonenumber.getText().toString());

            }

            else if(spinner.getSelectedItemPosition() == 2){
                mimeMessage.setSubject("Subject: Complaint regarding " + spinner.getSelectedItem().toString());
                mimeMessage.setText("Name = " + name.getText().toString() + "\n" + "Hostel Name = " + hostelname.getText().toString() + "\n" + "Room Number = " + roomnumber.getText().toString() + "\n" + "With due respect this is to inform you that the problem I'm facing is regarding = " + spinner.getSelectedItem().toString() + "." + "\n" + "What problem are you facing = " + description.getText().toString() + "\n" + "Thank You" + "\n" + name.getText().toString() + "\n" + "Phone Number = +91-" + phonenumber.getText().toString());
            }

            else if(spinner.getSelectedItemPosition() == 3){
                mimeMessage.setSubject("Subject: Complaint regarding " + spinner.getSelectedItem().toString());
                mimeMessage.setText("Name = " + name.getText().toString() + "\n" + "Hostel Name = " + hostelname.getText().toString() + "\n" + "Room Number = " + roomnumber.getText().toString() + "\n" + "With due respect this is to inform you that the problem I'm facing is regarding = " + spinner.getSelectedItem().toString() + "." + "\n" + "What problem are you facing = " + description.getText().toString() + "\n" + "Thank You" + "\n" + name.getText().toString() + "\n" + "Phone Number = +91-" + phonenumber.getText().toString());
            }

            else if(spinner.getSelectedItemPosition() == 4){
                mimeMessage.setSubject("Subject: Complaint regarding " + spinner.getSelectedItem().toString());
                mimeMessage.setText("Name = " + name.getText().toString() + "\n" + "Hostel Name = " + hostelname.getText().toString() + "\n" + "Room Number = " + roomnumber.getText().toString() + "\n" + "With due respect this is to inform you that the problem I'm facing is regarding = " + spinner.getSelectedItem().toString() +"." + "\n" + "What problem are you facing = " + description.getText().toString()  + "\n" + "Thank You" + "\n" + name.getText().toString() + "\n" + "Phone Number = +91-" + phonenumber.getText().toString());
            }

            else if(spinner.getSelectedItemPosition() == 5){
                mimeMessage.setSubject("Subject: Complaint regarding " + spinner.getSelectedItem().toString());
                mimeMessage.setText("Name = " + name.getText().toString() + "\n" + "Hostel Name = " + hostelname.getText().toString() + "\n" + "Room Number = " + roomnumber.getText().toString() + "\n" + "With due respect this is to inform you that the problem I'm facing is regarding = " + spinner.getSelectedItem().toString() +"." + "\n" + "What problem are you facing = " + description.getText().toString() + "\n" + "Thank You" + "\n" + name.getText().toString() + "\n" + "Phone Number = +91-" + phonenumber.getText().toString());
            }

            /*else{
                    mimeMessage.setSubject("Subject: Complaint regarding ");
                    mimeMessage.setText("Name = " + name.getText().toString() + "\n" + "Hostel Name = " + hostelname.getText().toString() + "\n" + "Room Number = " + roomnumber.getText().toString() + "\n" + "With due respect this is to inform you that the problem " + "." + "\n" + "Thank You" + "\n" + name.getText().toString() + "\n" + "Phone Number = " + phonenumber.getText().toString());

            }*/

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Transport.send(mimeMessage);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }



}
