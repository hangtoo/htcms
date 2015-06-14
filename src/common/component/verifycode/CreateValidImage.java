package common.component.verifycode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CreateValidImage
{

    public CreateValidImage()
    {
    }

    private Color getRandColor(int fc, int bc)
    {
        Random random = new Random();
        if(fc > 255)
            fc = 255;
        if(bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public BufferedImage getBufferedImage(String content)
    {
        int width = 90;
        int height = 20;
        return getBufferedImage(content, width, height);
    }

    public BufferedImage getBufferedImage(String content, int width, int height)
    {
        BufferedImage image = new BufferedImage(width, height, 1);
        Graphics g = image.getGraphics();
        Random random = new Random();
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", 0, 18));
        g.setColor(new Color(255, 255, 255));
        g.drawRect(0, 0, width - 1, height - 1);
        g.setColor(getRandColor(160, 200));
        for(int i = 0; i < 155; i++)
        {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        String sRand = "";
        for(int i = 0; i < 6; i++)
        {
            String rand = content.charAt(i) + "";
            sRand = sRand + rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }

        g.dispose();
        return image;
    }
}
