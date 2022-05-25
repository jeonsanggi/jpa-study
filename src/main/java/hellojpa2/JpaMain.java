package hellojpa2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;


public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            /*
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("Member1");
            member.setTeam(team);
            em.persist(member);

            Member findMember = em.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers();

             */
            //페치 조인1
            Team teamA = new Team();
            teamA.setName("팀A");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("팀B");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setTeam(teamB);
            em.persist(member3);

            em.flush();
            em.clear();

            /* 페치 조인을 사용하지 않으면
             회원1, 팀A(SQL)
             회원2, 팀A(1차 캐시)
             회원3, 팀B(SQL)
             총 3번의 쿼리를 사용하며 N + 1 문제가 발생
             fetch join을 사용하면 1번의 쿼리를 발생하여 문제 해결
             */
            String query = "select m from Member m join fetch m.team";

            List<Member> result = em.createQuery(query, Member.class)
                            .getResultList();
            for (Member member : result){
                System.out.println("member = " + member.getUsername() + ", " + "teamName = " + member.getTeam().getName());
            }



            //named 쿼리
            List<Member> resultList = em.createNamedQuery("Member.findById", Member.class)
                    .getResultList();

            em.flush();
            em.clear();

            // 벌크 연산 예제
            int resultCount = em.createQuery("update Member m set m.age = 20")
                    .executeUpdate();



            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
