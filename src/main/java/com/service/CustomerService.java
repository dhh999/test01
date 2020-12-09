package com.service;

import com.bean.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCuts();

    void saveCuts(Customer customer);

    Customer findById(Integer id);

    void delete(Integer[] id);
}
