package org.dreamtech.bookstore.web.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Program: BookStore
 * @Description: 验证码生成程序
 * @Author: YiQing Xu
 * @Create: 2019-03-09 21:39
 */
@WebServlet("/imageCode")
public class CheckImgServlet extends HttpServlet {


    private List<String> words = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        String path = getServletContext().getRealPath("/WEB-INF/new_words.txt");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", -1);

        int width = 120;
        int height = 30;

        BufferedImage bufferedImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);

        Graphics graphics = bufferedImage.getGraphics();
        graphics.setColor(getRandColor(200, 250));
        graphics.fillRect(0, 0, width, height);

        graphics.setColor(Color.WHITE);
        graphics.drawRect(0, 0, width - 1, height - 1);

        Graphics2D graphics2d = (Graphics2D) graphics;

        graphics2d.setFont(new Font("宋体", Font.BOLD, 18));

        Random random = new Random();
        int index = random.nextInt(words.size());
        String word = words.get(index);

        int x = 10;
        for (int i = 0; i < word.length(); i++) {

            graphics2d.setColor(new Color(20 + random.nextInt(110), 20 + random
                    .nextInt(110), 20 + random.nextInt(110)));

            int angle = random.nextInt(60) - 30;

            double theta = angle * Math.PI / 180;

            char c = word.charAt(i);

            graphics2d.rotate(theta, x, 20);
            graphics2d.drawString(String.valueOf(c), x, 20);
            graphics2d.rotate(-theta, x, 20);
            x += 30;
        }

        request.getSession().setAttribute("checkcode_session", word);

        graphics.setColor(getRandColor(160, 200));
        int x1;
        int x2;
        int y1;
        int y2;
        for (int i = 0; i < 30; i++) {
            x1 = random.nextInt(width);
            x2 = random.nextInt(12);
            y1 = random.nextInt(height);
            y2 = random.nextInt(12);
            graphics.drawLine(x1, y1, x1 + x2, x2 + y2);
        }

        graphics.dispose();
        ImageIO.write(bufferedImage, "jpg", response.getOutputStream());

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * 取其某一范围的color
     *
     * @param fc int 范围参数1
     * @param bc int 范围参数2
     * @return Color
     */
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

}
