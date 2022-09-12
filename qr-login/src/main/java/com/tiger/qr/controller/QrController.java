package com.tiger.qr.controller;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import com.tiger.qr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author Zenghu
 * @Date 2021/10/1 15:18
 * @Description
 * @Version: 1.0
 **/
@Controller("/")
@CrossOrigin
public class QrController {


    @Autowired
    private UserService userService;


    @RequestMapping(value = "getLoginQr", method = RequestMethod.GET)
    public void createQrCodeImage(HttpServletRequest request, HttpServletResponse response) {

        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setHeader("Content-Type", "image/jpeg");

        String content="http://192.168.100.6/";
        String uuid = UUID.randomUUID().toString();
        response.setHeader("uuid", uuid);
        response.addHeader("Access-Control-Expose-Headers", "uuid");

        try {
            QrCodeUtil.generate(content, 300, 300, ImgUtil.IMAGE_TYPE_JPG, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "bindUserIdAndToken", method = RequestMethod.GET)
    @ResponseBody
    public Object bindUserIdAndToken(@RequestParam("token") String token, @RequestParam("userId") Integer userId) {
        try {
            userService.bindUserIdAndToken(userId, token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        return "ok";
    }
}
