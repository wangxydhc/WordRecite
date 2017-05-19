package test;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
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
//		new SchemaExport(new AnnotationConfiguration().configure()).create(true, true);
		Session s=sf.openSession();
		Transaction tx=s.beginTransaction();
		GetBook.run("D:/wordBook.txt",s);
		tx.commit();
		s.close();
	}

}
