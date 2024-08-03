package com.buildlive.userservice.service.client;

import com.buildlive.userservice.dto.PlanResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "ADMIN-SERVICE",url = "http://18.190.72.144:8090")
public interface AdminClient {

    @GetMapping("/api/v1/admin/plans/{id}")
    PlanResponse getPlanById(@PathVariable("id") UUID id);

}
