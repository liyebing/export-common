package com.export.model;

import com.export.movie.common.Comment;

/**
 * @author liyebing created on 15/10/30.
 * @version $Id$
 */
public class User {

    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Comment("名称")
    private String name;

    @Comment("邮箱")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
