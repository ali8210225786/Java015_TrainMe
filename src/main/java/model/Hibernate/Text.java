package model.Hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00_init.util.HibernateUtils;

public class Text {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		List<GymBean_H> gyms = new ArrayList<GymBean_H>();
		GymBean_H gb = null;
		
		SessionFactory factory;
		factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		

		
			try {
				tx = session.beginTransaction();
				
				
				String hql = "FROM GymBean_H";
				gyms = session.createQuery(hql).getResultList();
					
				
				System.out.println("0-----------------------------------------------");
//				gyms.add(gb);
				
				
				System.out.println(gyms);
//				gyms = dao.gymList_H();
				tx.commit();
			} catch (Exception e) {
				if (tx != null) {
					tx.rollback();
				}
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
			
	}

}
