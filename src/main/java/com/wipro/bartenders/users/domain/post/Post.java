package com.wipro.bartenders.users.domain.post;

import com.wipro.bartenders.users.domain.user.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    @ManyToOne
    private User op;
}
