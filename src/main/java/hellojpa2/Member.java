package hellojpa2;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Member {
    @Id @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(name= "USERNAME")
    private String username;

/*    @Column(name= "TEAM_ID")
    private Long teamId;*/

    @ManyToOne
    @JoinColumn(name="TEAM_ID")   //연관관계 매핑
    private Team team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    @OneToMany(mappedBy = "memeber")
    private List<MemberProduct> memberProducts = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
