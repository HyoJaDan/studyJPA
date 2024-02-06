package jpaBasic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.changeTeam(team);
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.changeTeam(team);
            em.persist(member2);


            Team findTeam1=em.find(Team.class, team.getId());
            System.out.println("findTeam1.getName() = " + findTeam1.getName());

            Member findMember=em.find(Member.class, member.getId());
            System.out.println("findMember = " + findMember.getUsername());

            Team findTeam = member.getTeam();
            System.out.println("findTeam.getName() = " + findTeam.getName());

            System.out.println("findTeamMembers = " + findTeam.getMembers());
            

            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally{
            em.close();
        }

        emf.close();
    }
}