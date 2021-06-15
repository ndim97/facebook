package com.example.facebook.controller;

import com.example.facebook.component.Utility;
import com.example.facebook.service.contract.UserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
public class ResetPasswordController extends BaseController{

    private final UserService userService;
    private final JavaMailSender mailSender;

    @Autowired
    public ResetPasswordController(UserService userService, JavaMailSender mailSender) {
        this.userService = userService;
        this.mailSender = mailSender;
    }

    @PreAuthorize("!isAuthenticated()")
    @GetMapping("/reset/password")
    public ModelAndView resetPassword(){
        return send("forgotten-password");
    }

    @PreAuthorize("!isAuthenticated()")
    @PostMapping("/reset/password")
    public ModelAndView processForgotPasswordForm(HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        String email = request.getParameter("email");
        String token = RandomString.make(45);

        userService.updateResetPasswordToken(token, email);

        String resetPasswordLink = Utility.getSiteURL(request) + "/reset/password?token=" + token;
        System.out.println(resetPasswordLink);

        sendEmail(email, resetPasswordLink);

        return send("forgotten-password");
    }

    private void sendEmail(String email, String resetPasswordLink) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("hellonedned97gmail.com", "Neda Support");
        helper.setTo(email);
        String subject = "Here is the link to reset your password.";
        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><b><a href=\""+ resetPasswordLink + "\">Change my password</a><b></p>"
                + "<p>Ignore this email if you do remember your password, or you have not made the request.</p>";
        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
    }
}
