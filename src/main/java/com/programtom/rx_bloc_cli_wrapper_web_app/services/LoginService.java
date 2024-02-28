package com.programtom.rx_bloc_cli_wrapper_web_app.services;

import com.programtom.rx_bloc_cli_wrapper_web_app.models.auth.*;
import com.programtom.rx_bloc_cli_wrapper_web_app.repositories.LoginRepository;
//import com.programtom.rx_bloc_cli_wrapper_web_app.repositories.UserRepository;
//import com.programtom.rx_bloc_cli_wrapper_web_app.services.mail.EmailServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class LoginService {
    @Autowired
    LoginRepository loginRepository;

   // @Autowired
   // UserRepository userRepository;
    @Autowired
    HashingService hashingService;
    //@Autowired
    //EmailServiceImpl emailService;

    //@Autowired
    //IPAttemptsService ipAttemptsService;


    public List<Login> findByEmail(String email) {
        return loginRepository.findByEmail(email);
    }

    public boolean isValid(String hashed, String plain) {
        return hashingService.bencryptVerify(plain, hashed);
    }

    @Transactional
    public void save(Login login, String language) {
        boolean sendNotification = login.getId() == null;
        if (sendNotification) {
            login.setActivationGuid(UUID.randomUUID().toString());
            login.setActivationGuidDateOfIssue(new Date());
        }

        loginRepository.save(login);
        /*User user = new User();
        user.setLogin(login.getId());
        userRepository.save(user);
        if (sendNotification) {
            emailService.sendActivateEmail(login, language);
        }
         */
    }

    public String activate(String email, String guid) {
        List<Login> list = loginRepository.findByEmail(email);
        /*
        if (list.isEmpty()) {
            return AuthBusinessCode.EMAIL_NOT_FOUND.name();
        } else {
            Login loginFromDb = list.get(0);
            if (loginFromDb.getActivationGuid() != null &&
                    loginFromDb.getActivationGuid().equals(guid)) {


                Calendar instance = Calendar.getInstance();
                instance.setTimeInMillis(loginFromDb.getActivationGuidDateOfIssue().getTime());
                //TODO parametrisize this
                instance.add(Calendar.DAY_OF_MONTH, 1);

                if (instance.getTimeInMillis() > System.currentTimeMillis()) {
                    loginFromDb.setActive(true);
                    loginRepository.save(loginFromDb);
                    return AuthBusinessCode.SUCCESS.name();
                } else {
                    return AuthBusinessCode.LINK_EXPIRED.name();

                }

            } else {
                return AuthBusinessCode.INVALID_LINK.name();
            }

         */
        return null;
    }

    public String sendResetLink(String email, String language) {
        List<Login> list = loginRepository.findByEmail(email);
      /*  if (list.isEmpty()) {
            return AuthBusinessCode.EMAIL_NOT_FOUND.name();
        } else {
            Login loginFromDb = list.get(0);
            //TODO prevent this if it is active
            loginFromDb.setResetPasswordGuid(UUID.randomUUID().toString());
            loginFromDb.setResetPasswordGuidDateOfIssue(new Date());

            loginRepository.save(loginFromDb);
            emailService.sendResetLink(loginFromDb, language);

        }*/
        return "";
    }

    public String resetPassword(String email, String newPassword, String guid) {
        List<Login> list = loginRepository.findByEmail(email);
        /*
        if (list.isEmpty()) {
            return AuthBusinessCode.EMAIL_NOT_FOUND.name();
        } else {
            Login loginFromDb = list.get(0);
            if (loginFromDb.getResetPasswordGuid() != null && loginFromDb.getResetPasswordGuid().equals(guid)) {
                loginFromDb.setPassword(newPassword);
                loginFromDb.setResetPasswordGuid(null);
                loginFromDb.setResetPasswordGuidDateOfIssue(null);
                loginRepository.save(loginFromDb);
            } else {
                return AuthBusinessCode.WRONG_PASSWORD.name();
            }
        }
         */
        return "";
    }

    public String getUserId(String token) {
        /*
        List<User> users = userRepository.findByLogin(token);
        if (!users.isEmpty()) {
            return String.valueOf(users.get(0).getId());
        }

         */
        return null;
    }

    public Login findByEmail(String email, String password, String remoteAddress, String language) {
       /*
        List<Login> logins = loginRepository.findByEmail(email);
        if (logins.isEmpty()) {
            List<IPAttempt> ipAttempts = ipAttemptsService.findByIp(remoteAddress);
            if (ipAttempts.isEmpty()) {
                ipAttemptsService.saveAttempt(remoteAddress, 1, null);
            } else {
                int attempts = ipAttempts.get(0).getAttempts();
                if (attempts > 3) {
                    throw new AuthTokenModel.AuthException(AuthBusinessCode.TOO_MANY_ATTEMPTS);
                } else {
                    ipAttemptsService.saveAttempt(remoteAddress, ipAttempts.get(0).getAttempts(), ipAttempts.get(0).getId());
                    throw new AuthTokenModel.AuthException(AuthBusinessCode.WRONG_PASSWORD);
                }
            }
            throw new AuthTokenModel.AuthException(AuthBusinessCode.EMAIL_NOT_FOUND);
        } else {
            Login loginFromDb = logins.get(0);
            boolean validPassword = hashingService.bencryptVerify(password, loginFromDb.getPassword());
            if (validPassword) {
                return loginFromDb;
//                return createAndSaveRefreshToken(getDevice(request), loginFromDb.getId());
            } else {
                if (loginFromDb.getEmailAttempts() > 3) {
                    throw new AuthTokenModel.AuthException(AuthBusinessCode.WRONG_PASSWORD_TOO_MANY_ATTEMPTS);
                } else {
                    loginFromDb.setEmailAttemptsDate(new Date());
                    loginFromDb.setEmailAttempts(loginFromDb.getEmailAttempts() + 1);
                    save(loginFromDb, language);
                    throw new AuthTokenModel.AuthException(AuthBusinessCode.WRONG_PASSWORD);

                }
            }
        }

        */
        return null;
    }
}
