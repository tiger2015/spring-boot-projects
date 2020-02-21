package com.tiger.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @ClassName VerifyCodeServlet
 * @Description TODO
 * @Author zeng.h
 * @Date 2019/11/16 16:36
 * @Version 1.0
 **/
public class VerifyCodeServlet extends HttpServlet {
    private int width = 100;
    private int height = 30;
    private int codeCount = 4;
    private int fontHeight;
    private int interLine = 16;
    private int codeX;
    private int codeY;
    char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    @Override
    public void init() throws ServletException {
        codeX = (width-4) / (codeCount+1);
        fontHeight = height - 10;
        codeY = height - 7;
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D gd = buffImg.createGraphics();
        Random random = new Random();
        gd.setColor(Color.LIGHT_GRAY);
        gd.fillRect(0, 0, width, height);
        Font font = new Font("Times New Roman", Font.PLAIN, fontHeight);
        gd.setFont(font);
        gd.setColor(Color.BLACK);
        gd.drawRect(0, 0, width - 1, height - 1);
        gd.setColor(Color.gray);
        for (int i = 0; i < interLine; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            gd.drawLine(x, y, x + xl, y + yl);
        }
        StringBuffer randomCode = new StringBuffer();
        int red = 0, green = 0, blue = 0;
        for (int i = 0; i < codeCount; i++) {
            // 得到随机产生的验证码数字。
            String strRand = String.valueOf(codeSequence[random.nextInt(36)]);
            // 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            // 用随机产生的颜色将验证码绘制到图像中。
            gd.setColor(new Color(red,green,blue));
            gd.drawString(strRand, (i + 1) * codeX, codeY);
            // 将产生的四个随机数组合在一起。
            randomCode.append(strRand);
        }
        // 将四位数字的验证码保存到Session中。
        HttpSession session = request.getSession();
        session.setAttribute("validateCode", randomCode.toString());
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(buffImg, "jpeg", sos);
        sos.close();
    }
}
