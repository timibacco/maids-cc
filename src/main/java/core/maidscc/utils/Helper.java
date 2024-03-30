package core.maidscc.utils;

import core.maidscc.dto.productDTO;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.FeatureDescriptor;
import java.util.stream.Stream;


@Service
public class Helper {



    public boolean isEmailValid(String email) {
        return email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
    }

    public boolean isPasswordValid(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    }

    public boolean isPasswordCorrect(String rawPassword, String hashedPassword) {
//        encoder.matches(rawPassword, hashedPassword);
        return BCrypt.checkpw(rawPassword, hashedPassword);
    }

    public  <T> String[] getNullPropertyNames(T source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }
}