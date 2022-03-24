package com.example.xmlexercise.model.dto.usersAndProducts;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserInfoRootDto {
    @XmlAttribute(name = "count")
    private long count;
    @XmlElement(name = "user")
    private List<UserCurrentDto> user;

    public List<UserCurrentDto> getUser() {
        return user;
    }

    public void setUser(List<UserCurrentDto> user) {
        this.user = user;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
