package common.component.verifycode;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.component.IConstants;
import common.util.StringUtil;


public class VerifyCodeServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=GBK";

    public VerifyCodeServlet()
    {
    }

    public void init()
        throws ServletException
    {
       
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        HttpSession session = request.getSession();
        String code = StringUtil.randomKey(6);
        session.setAttribute(IConstants.VERIFYCODE, code);
        java.awt.image.BufferedImage image = (new CreateValidImage()).getBufferedImage(code);
        ImageIO.write(image, "JPEG", response.getOutputStream());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        doGet(request, response);
    }

    public void destroy()
    {
    }
}
