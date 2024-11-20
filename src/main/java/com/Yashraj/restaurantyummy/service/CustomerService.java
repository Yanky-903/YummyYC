package com.Yashraj.restaurantyummy.service;

import com.Yashraj.restaurantyummy.dto.CustomerRequest;
import com.Yashraj.restaurantyummy.dto.CustomerResponse;
import com.Yashraj.restaurantyummy.dto.LoginRequest;
import com.Yashraj.restaurantyummy.entity.Customer;
import com.Yashraj.restaurantyummy.exception.CustomerNotFoundException;
import com.Yashraj.restaurantyummy.helper.EncryptionService;
import com.Yashraj.restaurantyummy.helper.JWTHelper;
import com.Yashraj.restaurantyummy.mapper.CustomerMapper;
import com.Yashraj.restaurantyummy.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;
    public String createCustomer(CustomerRequest.CustomerCreateRequest request) {
        Customer customer = customerMapper.toCustomer(request);
        customer.setPassword(encryptionService.encode(customer.getPassword()));
        customerRepo.save(customer);
        return "Customer Created Successfully";
    }

    public Customer getCustomer(String email) {
        return customerRepo.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("Cannot update Customer:: No customer found with the provided ID:: %s", email)
                ));
    }

    public CustomerResponse retrieveCustomer(String email) {
        Customer customer = getCustomer(email);
        return customerMapper.toCustomerResponse(customer);
    }

    public String login(LoginRequest request) {
        Optional<Customer> optionalCustomer = customerRepo.findByEmail(request.email());
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            if (encryptionService.validates(request.password(), customer.getPassword())) {
                return jwtHelper.generateToken(customer.getEmail());
            } else {
                return "Invalid password";
            }
        } else {
            return "User not found";
        }

        //return jwtHelper.generateToken(request.email())
    }

    public Customer updateCustomer(String email, CustomerRequest.CustomerUpdateRequest updateRequest) {
        // Fetch the existing customer by email
        Optional<Customer> optionalCustomer = customerRepo.findByEmail(email);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();

            // Update fields only if they are present in the request
            if (updateRequest.firstName() != null) {
                customer.setFirstName(updateRequest.firstName());
            }
            if (updateRequest.lastName() != null) {
                customer.setLastName(updateRequest.lastName());
            }
            if (updateRequest.city() != null) {
                customer.setCity(updateRequest.city());
            }
            if (updateRequest.pinCode()!= null) {
                customer.setPinCode(updateRequest.pinCode());
            }
            customer.setUpdatedOn(LocalDateTime.now());
            return customerRepo.save(customer);
        } else {
            return null;
        }
    }
}