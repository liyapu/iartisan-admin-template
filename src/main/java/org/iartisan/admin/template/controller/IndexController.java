package org.iartisan.admin.template.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.iartisan.runtime.web.contants.ReqContants;
import org.iartisan.runtime.web.controller.BaseController;
import org.iartisan.runtime.web.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * <p>
 *
 * @author King
 * @since 2018/2/9
 */
@Controller
public class IndexController extends BaseController {

    @PostMapping(ReqContants.REQ_INDEX)
    public String index() {
        return "index";
    }

    @GetMapping("")
    public String redirectIndex() {
        return _redirect + "index";
    }

    @Autowired
    private Producer producer;

    @GetMapping(value = "captcha")
    public void captcha(HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        WebUtil.getShiroSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        out.flush();
    }

}
