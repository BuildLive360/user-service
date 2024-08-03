package com.buildlive.userservice.service;

import com.buildlive.userservice.dto.PaymentSuccessDto;
import com.buildlive.userservice.dto.TransactionDetails;
import com.buildlive.userservice.entity.UserSubscribedPlan;

import java.util.List;
import java.util.UUID;

public interface ISubscriptionService {

    TransactionDetails createTransaction(UUID planId);

    void successPayment(PaymentSuccessDto paymentSuccessDto);

    boolean hasUserPurchasedPlan(UUID userId, UUID planId);

    boolean isPremiumUser(UUID userId);

}
