package jpaBasic;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Member {
    @Id
    @Column(name="MEMBER_ID")
    @GeneratedValue
    private Long id;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    //@Column(name="TEAM_ID")
    //private Long teamId;

    private Team team;
    public Member(){
    }

}
