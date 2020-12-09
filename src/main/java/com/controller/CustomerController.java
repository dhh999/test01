package com.controller;

import com.bean.Customer;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
      * 查询所有数据，给页面返回json格式数据
      * easyui的datagrid组件，需要展示数提供json数据： [ {id:1,name:xxx},{id:1,name:xxx} ]
      * @return: java.util.List<com.bean.Customer>
      **/
    @RequestMapping(value = "/cuts")
    @ResponseBody
    public List<Customer> getCuts(){
        List<Customer> list = customerService.getAllCuts();
        return list;
    }


    //设计Map聚合存储需要给页面的对象数据
    Map<String,Object> result = new HashMap<String,Object>();


    /**
     *分页查询，给页面返回json格式数据
     *easyui的datagrid组件，需要展示数提供json数据：{total：总记录数  rows:当前页面数据}（一定要是total和rows）
     * @Param page:
     * @Param rows:
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping(value = "/cutsByPage")
    @ResponseBody
    public Map<String,Object> getCutsList(Integer page,Integer rows){
        //设置分页参数 page:当前页码  rows：每页显示记录数
        PageHelper.startPage(page, rows);
        //查询所有数据
        List<Customer> list = customerService.getAllCuts();

        //使用PageInfo封装查询结果
        PageInfo<Customer> pageInfo = new PageInfo<Customer>(list);

        //从PageInfo对象取出查询结果
        //总记录数
        long total = pageInfo.getTotal();
        //当前页数据列表
        List<Customer> custList = pageInfo.getList();

        //datagrid要进行分页查询要求返回：{total：总记录数  rows:当前页面数据}（一定要是total和rows）
        result.put("total", total);
        result.put("rows", custList);

        return result;
    }


    /**
      * 保存数据
      *
      * @Param customer:
      * @return: java.util.Map<java.lang.String,java.lang.Object>
      **/
    @RequestMapping(value = "/save")
    @ResponseBody
    public Map<String,Object> saveCuts(Customer customer){
        try {
            customerService.saveCuts(customer);
            result.put("success",true);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("msg",e.getMessage());
        }
        return result;
    }


    /**
      * 通过id查询数据，回显表单
      *
      * @Param id:
      * @return: com.bean.Customer
      **/
    @RequestMapping(value = "/findById")
    @ResponseBody
    public Customer findCuts(Integer id){
        Customer cut = customerService.findById(id);
        return cut;
    }


    /**
      * 删除用户
      *
      * @Param id:
      * @return: java.util.Map<java.lang.String,java.lang.Object>
      **/
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Map<String,Object> deleteCuts(Integer[] id){
        try {
            customerService.delete(id);
            result.put("success",true);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("msg",e.getMessage());
        }
        return result;
    }
}
