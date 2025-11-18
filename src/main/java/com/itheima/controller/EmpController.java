package com.itheima.controller;

import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 员工管理
 */
@Slf4j
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

}