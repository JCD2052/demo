package org.jcd2052.api.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Post {
    private int userId;
    private int id;
    private String title;
    private String body;


    public Post(int userId, String title, String body) {
        this(userId, 0, title, body);
    }
}
