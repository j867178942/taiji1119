package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.service.Service1;


@Controller
public class MyController {
@Autowired
private Service1 si;

}
