package com.buildlive.userservice.controller;

import com.buildlive.userservice.dto.PaymentSuccessDto;
import com.buildlive.userservice.entity.UserSubscribedPlan;
import com.buildlive.userservice.service.ISubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserPlanController {

    @Autowired
    ISubscriptionService subscriptionService;
    @PostMapping("/purchase-plan")
    public ResponseEntity<?> buySubscription(@RequestParam(name = "planId")UUID planId,
                                             @RequestParam(name = "userId") UUID userId){

        return ResponseEntity.ok(subscriptionService.createTransaction(planId));
    }

    @PostMapping("/subscription-success")
    public ResponseEntity<?> onPaymentSuccess(@RequestBody PaymentSuccessDto paymentSuccessDto){
        System.out.println(paymentSuccessDto+"sgsad");
        subscriptionService.successPayment(paymentSuccessDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{userId}/plans/{planId}/purchased")
    public ResponseEntity<?> checkPlanPurchased(@PathVariable UUID userId, @PathVariable UUID planId) {
        System.out.println("ll");

        return new ResponseEntity<>(subscriptionService.hasUserPurchasedPlan(userId, planId),HttpStatus.OK);
    }

    @GetMapping("/{userId}/plans/isPremiumUser")
    public ResponseEntity<?> isPremiumUserCheck(@PathVariable UUID userId){
        return new ResponseEntity<>(subscriptionService.isPremiumUser(userId),HttpStatus.OK);
    }
}
