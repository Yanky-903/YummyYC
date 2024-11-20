package com.Yashraj.restaurantyummy.Controller;

import com.Yashraj.restaurantyummy.dto.CustomerRequest;
import com.Yashraj.restaurantyummy.dto.CustomerResponse;
import com.Yashraj.restaurantyummy.entity.Customer;
import com.Yashraj.restaurantyummy.helper.AuthHelper;
import com.Yashraj.restaurantyummy.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final AuthHelper authHelper;

    @GetMapping("/{email}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable("email") String email) {
        return ResponseEntity.ok(customerService.retrieveCustomer(email));
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest.CustomerCreateRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCustomer(@RequestBody @Valid CustomerRequest.CustomerUpdateRequest updateRequest, HttpServletRequest request) {
        String email = authHelper.extractAndCheckJWT(request);
        if (email == null) {
            return ResponseEntity.status(401).body("Unauthorized: Invalid or expired token"); // Custom error response
        }

        Customer updatedCustomer = customerService.updateCustomer(email, updateRequest);

        if (updatedCustomer == null) {
            return ResponseEntity.status(404).body("User not found!");
        }
        return ResponseEntity.ok(updatedCustomer);
    }
}
