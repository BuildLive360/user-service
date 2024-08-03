package com.buildlive.userservice.service.impl;

import com.buildlive.userservice.dto.PaymentSuccessDto;
import com.buildlive.userservice.dto.PlanResponse;
import com.buildlive.userservice.dto.TransactionDetails;
import com.buildlive.userservice.entity.PlanType;
import com.buildlive.userservice.entity.UserCredential;
import com.buildlive.userservice.entity.UserSubscribedPlan;
import com.buildlive.userservice.repo.UserCredentialRepository;
import com.buildlive.userservice.repo.UserSubscribedPlanRepo;
import com.buildlive.userservice.service.ISubscriptionService;
import com.buildlive.userservice.service.client.AdminClient;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SubscriptionServiceImpl implements ISubscriptionService {


    @Autowired
    AdminClient adminClient;

    @Autowired
    private UserCredentialRepository userCredentialRepository;

    @Autowired
    private UserSubscribedPlanRepo userSubscribedPlanRepo;
    private static final String KEY = "rzp_test_MLtEnD2XBlBADJ";
    private static final String KEY_SECRET = "n3oaKNjoF9O31OHjc7U6eCNL";
    private static final String CURRENCY = "INR";

    @Override
    public TransactionDetails createTransaction(UUID planId) {
        PlanResponse plan = adminClient.getPlanById(planId);
        System.out.println(plan+"''''''''''");
        Double planAmount = plan.getPlanPrice();

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("amount", planAmount * 100);
            jsonObject.put("currency", CURRENCY);
            RazorpayClient client = new RazorpayClient(KEY, KEY_SECRET);
            Order order = client.orders.create(jsonObject);
            return prepareTransactionDetails(order);
        } catch (RazorpayException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void successPayment(PaymentSuccessDto paymentSuccessDto) {

        Optional<UserCredential> userCredential = userCredentialRepository.findById(paymentSuccessDto.getUserId());
        if (userCredential.isPresent()){
            UserCredential user = userCredential.get();
            PlanResponse plan = adminClient.getPlanById(paymentSuccessDto.getPlanId());
            PlanType planType = PlanType.valueOf(plan.getPlanType());
            LocalDate planEndDate = LocalDate.now().plusMonths(planType.getDurationInMonths());
            UserSubscribedPlan userSubscribedPlan = UserSubscribedPlan.builder()
                    .planId(plan.getPlanId())
                    .user(user)
                    .planStartDate(LocalDate.now())
                    .planEndDate(planEndDate)
                    .isPurchased(true)
                    .transactionId(paymentSuccessDto.getTransactionId())
            .build();
            user.setPremiumUser(true);

            user.getUserSubscribedPlans().add(userSubscribedPlan);
            userCredentialRepository.save(user);
        }
    }

    @Override
    public boolean hasUserPurchasedPlan(UUID userId, UUID planId) {
        return userSubscribedPlanRepo.existsByUserIdAndPlanId(userId, planId);
    }

    @Override
    public boolean isPremiumUser(UUID userId) {
        Optional<UserCredential> userCredential = userCredentialRepository.findById(userId);
        if (userCredential.isPresent()){
            return userCredential.get().isPremiumUser();
        }
        else {
            throw new RuntimeException("User not found ");
        }
    }


    private TransactionDetails prepareTransactionDetails(Order order){
        String orderId = order.get("id");
        String currency = order.get("currency");
        Integer amount = order.get("amount");
        return new TransactionDetails(orderId,currency,amount,KEY);

    }
}
