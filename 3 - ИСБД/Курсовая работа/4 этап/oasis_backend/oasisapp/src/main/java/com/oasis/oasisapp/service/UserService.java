package com.oasis.oasisapp.service;


import com.oasis.oasisapp.dto.UserDataResponse;
import com.oasis.oasisapp.exception.NotEnoughBalanceException;
import com.oasis.oasisapp.model.User;
import com.oasis.oasisapp.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.time.LocalDate;


@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean checkUserOnExist(String login) {
        return userRepository.existsByLogin(login);
    }

    public User getUserByLogin(String login)   {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователя с логином " + login + " не существует"));
    }

    public String getUserStatus(String login) {
        return getUserByLogin(login).getStatus();
    }

    public UserDataResponse getUserLastLoginDate(String login) {

        String status = getUserStatus(login);

        LocalDate lastLoginDate = getUserByLogin(login).getLastLoginDate();
        String lastLoginDateToSend;
        if (lastLoginDate == null) lastLoginDateToSend = "";
        else lastLoginDateToSend = lastLoginDate.toString();

        String registrationDate = getRegistrationDate(login).toString();
        return new UserDataResponse(status, lastLoginDateToSend, registrationDate);
    }

    public LocalDate getRegistrationDate(String login) {
        return getUserByLogin(login).getRegistrationDate();
    }


    public void logoutAsUser(String login)   {
        userRepository.logoutFromUser(login);
    }

    public void addBalance(String login, Double balance)   {
        userRepository.replenishBalance(login, balance);
    }

    public Double getBalance(String login)   {
        return userRepository.getBalance(login);
    }

    public void replenishBalanceSeller(String userLogin, Double balance) {
        userRepository.replenishBalanceSeller(userLogin, balance);
    }

    public void chargeBalanceCustomer(String userLogin, Double balance) {
        userRepository.chargeBalanceCustomer(userLogin, balance);
    }

    public void checkBalanceToBuyGame(User user, double marketPrice)  {
        if (user.getWallet().getBalance() < marketPrice)
            throw new NotEnoughBalanceException("У вас недостаточно средств для покупки этой вещи!");
    }


}