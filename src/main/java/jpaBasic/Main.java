package jpaBasic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Team team=new Team();
            team.setName("TeamA");
            // 1. team 먼저 영속상태로 만듬
            em.persist(team);

            Member member=new Member();
            member.setUsername("member1");
            // 2. 영속상태로 만든 team에서 getId로 id를 가져와서 member에 넣음. 따라서 멤버가 팀에 들어가짐
            member.setTeamId(team.getId());
            em.persist(member);

            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally{
            em.close();
        }

        emf.close();
    }
}