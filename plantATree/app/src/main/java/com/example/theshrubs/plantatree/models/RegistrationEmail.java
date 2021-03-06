package com.example.theshrubs.plantatree.models;

import android.content.Context;
import android.os.AsyncTask;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class RegistrationEmail extends AsyncTask<Void,Void,Void>{
    Context context;
    Session session;

    String email;
    String subject;
    String message;

    public RegistrationEmail(Context context, String email){
        this.context = context;
        this.email = email;
        this.subject = "Welcome to PlantATree";
        this.message = "Thank you for registering with PlantATree! \nPlantATree";
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("TheShrubsPlantATree@gmail.com", "plantatree");
            }
        });

        try {
            Message mm = new MimeMessage(session);
            mm.setFrom(new InternetAddress("PlantATree@gmail.com"));
            mm.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            mm.setSubject(subject);
            mm.setText(message);
            Transport.send(mm);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
