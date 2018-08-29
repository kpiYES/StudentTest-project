package com.app.util;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class UserNameTag extends TagSupport {


    private String firstName;

    public void setFirstname(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public int doStartTag() throws JspException {
        try{
            JspWriter out = pageContext.getOut();
            out.write(firstName );
        }catch (IOException e){
        }
        return SKIP_BODY;
    }
}
