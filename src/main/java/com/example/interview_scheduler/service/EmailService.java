package com.example.interview_scheduler.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Async
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendInterviewMail(
            String to,
            String name,
            String date,
            String time,
            String link
    ) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Interview Scheduled");

        message.setText(
                "Hi " + name + ",\n\n" +
                        "Your interview has been scheduled.\n\n" +
                        "📅 Date: " + date + "\n" +
                        "⏰ Time: " + time + "\n" +
                        "🔗 Meeting Link: " + link + "\n\n" +
                        "All the best!\n\n" +
                        "Regards,\nHR Team"
        );

        mailSender.send(message);
    }
}