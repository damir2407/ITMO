package com.oasis.oasisapp.repository;

import com.oasis.oasisapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByLogin(String login);

    Boolean existsByLogin(String login);

    Boolean existsByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "CALL loginasuser(:arg_login);", nativeQuery = true)
    void loginAsUser(@Param("arg_login") String login);

    @Transactional
    @Modifying
    @Query(value = "CALL logoutfromuser(:arg_login);", nativeQuery = true)
    void logoutFromUser(@Param("arg_login") String login);


    @Transactional
    @Modifying
    @Query(value = "CALL replenishbalance(:arg_login,:arg_balance)", nativeQuery = true)
    void replenishBalance(@Param("arg_login") String login, @Param("arg_balance") Double balance);


    @Transactional
    @Modifying
    @Query(value = "CALL replenishbalanceForSoldItem(:arg_login,:arg_balance)", nativeQuery = true)
    void replenishBalanceSeller(@Param("arg_login") String login, @Param("arg_balance") Double balance);


    @Transactional
    @Modifying
    @Query(value = "CALL chargebalanceForSoldItem(:arg_login,:arg_balance)", nativeQuery = true)
    void chargeBalanceCustomer(@Param("arg_login") String login, @Param("arg_balance") Double balance);

    @Query(value = "SELECT wallets.balance FROM users" +
            " INNER JOIN wallets ON users.wallet_id=wallets.id" +
            " WHERE users.login=:arg_login", nativeQuery = true)
    Double getBalance(@Param("arg_login") String login);




}
