package test;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;


import org.junit.Test;

import preDo.GetBook;

public  class TestClass {
	private static SessionFactory sf=null;
	
	public static void beforeclass(){
		sf=new AnnotationConfiguration().configure().buildSessionFactory();
	}
	public static Session getSession(){
		return sf.openSession();
	}
	
	@Test
	public void testSchemaExport() throws Throwable
	{
		new SchemaExport(new AnnotationConfiguration().configure()).create(true, true);
		this.beforeclass();
		Session s =this.getSession();
		Transaction tx=s.beginTransaction();
		GetBook.run("d://wordBook.txt",s);
		tx.commit();
		s.close();
	}

}
