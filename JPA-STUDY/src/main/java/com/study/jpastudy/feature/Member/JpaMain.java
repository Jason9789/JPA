package com.study.jpastudy.feature.Member;

import com.study.jpastudy.core.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class JpaMain {

    public static void main(String[] args) {
        // entity manager factory - create
        EntityManagerFactory emf = Persistence.createEntityManagerFactory();

        // entity Manager - create
        EntityManager em = emf.createEntityManager();

        // transaction
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            logic(em);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void logic(EntityManager em) {

        String id  = "id1";
        Member member = new Member();
        member.setId(id);
        member.setId(id);
        member.setUsername("Gilbert");
        member.setAge(2);

        em.persist(member);

        member.setAge(20);

        Member findMember = em.find(Member.class, id);
        System.out.println("findMember : " + findMember.getUsername());

        List<Member> memberList = em.createQuery("select m from Member m", Member.class).getResultList();

        System.out.println("Members.size = " + memberList.size());

        em.remove(member);
    }
}
