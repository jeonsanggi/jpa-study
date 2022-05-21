package hellojpa;

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
            Member member = new Member();
            member.setId(2L);
            member.setName("HelloB");

            em.persist(member);*/

            /*Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloJPA");*/

            /*
            List<Member> result = em.createQuery("select m from Member as m",Member.class)
                    .setFirstResult(1)  // 1번부터
                    .setMaxResults(8)   // 8개 데이터를 가져오기 (페이징)
                    .getResultList();

            for(Member member : result){
                System.out.println("member.name = " + member.getName());
            }
             */
/*
            //영속성 컨텍스트 실습
            //비영속성
            Member member = new Member();
            member.setId((101L));
            member.setName("HelloJPA");

            //영속
            em.persist(member);

            Member findMember = em.find(Member.class, 101L);

            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());

            //회원 엔티티를 영속성 컨텍스트에서 분리, 준영속 상태
            //em.detach(member);

            //객체를 삭제한 상태(삭제)
            //em.remove(member);
*/
/*
            //영속
            Member member1 = new Member(150l, "A");
            Member member2 = new Member(160L, "B");

            em.persist(member1);
            em.persist(member2);
*/
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
