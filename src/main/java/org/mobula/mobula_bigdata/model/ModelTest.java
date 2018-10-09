package org.mobula.mobula_bigdata.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by zw on 18-10-10.
 */
@Component
public class ModelTest {
    @Value("${com.didispace.blog.name}")
    private String name;
    @Value("${com.didispace.blog.title}")
    private String title;

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }
}
