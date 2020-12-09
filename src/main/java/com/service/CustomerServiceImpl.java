package com.service;

import com.bean.Customer;
import com.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    /**
      *查询所有用户
      *
      * @return: java.util.List<com.bean.Customer>
      **/
    @Override
    public List<Customer> getAllCuts() {
        return customerMapper.selectByExample(null);
    }

    /**
      * 保存数据
      *
      * @Param customer:
      * @return: void
      **/
    @Override
    public void saveCuts(Customer customer) {
        //结合页面，通过id是否为空判断是添加还是修改
        if (customer.getId()!=null){
            //根据id修改数据
            customerMapper.updateByPrimaryKeySelective(customer);
        }else {
            customerMapper.insertSelective(customer);
        }
    }

    /**
      * 根据id查询用户，回显表单
      *
      * @Param customer:
      * @return: com.bean.Customer
      **/
    @Override
    public Customer findById(Integer id) {
        return customerMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(Integer[] id) {
        customerMapper.delete(id);
    }
}
