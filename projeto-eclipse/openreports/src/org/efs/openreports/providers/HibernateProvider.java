/*
 * Copyright (C) 2006 Erik Swenson - erik@oreports.com
 * 
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place - Suite 330, Boston, MA 02111-1307, USA.
 *  
 */

package org.efs.openreports.providers;

import java.io.Serializable;
import java.util.List;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.efs.openreports.providers.persistence.ConstraintException;
import org.efs.openreports.util.LocalStrings;
import org.springframework.beans.factory.DisposableBean;

public class HibernateProvider implements DisposableBean
{
	protected static Logger log = Logger.getLogger(HibernateProvider.class.getName());
	
	private static SessionFactory sessionFactory;

	public HibernateProvider() throws ProviderException
	{
		try
		{
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
		catch(HibernateException he)
		{
			throw new ProviderException(he);
		}

		log.info("SessionFactory Created.");
	}
	
	public static Session openSession() throws ProviderException
	{
		try
		{
			return sessionFactory.openSession();
		}
		catch (HibernateException he)
		{
			throw new ProviderException(he);
		}
	}	

	public static void closeSession(Session session) throws ProviderException
	{
		try
		{
			if (session != null)
				session.close();
		}
		catch (HibernateException he)
		{
			throw new ProviderException(he);
		}
	}

	public static void rollbackTransaction(Transaction tx)
		throws ProviderException
	{
		try
		{
			if (tx != null)
				tx.rollback();
		}
		catch (HibernateException he)
		{
			throw new ProviderException(he);
		}
	}

	public static Object save(Object object) throws ProviderException
	{
		Session session = openSession();
		Transaction tx = null;

		try
		{
			tx = session.beginTransaction();
			session.save(object);
			tx.commit();
		}
		catch (HibernateException he)
		{
			rollbackTransaction(tx);		
			
			if (he instanceof ConstraintViolationException)
			{
				throw new ProviderException(LocalStrings.getString(LocalStrings.ERROR_UNIQUE_CONSTRAINT));
			}
			
			throw new ProviderException(he);
		}
		finally
		{
			closeSession(session);
		}

		return object;
	}

	public static void update(Object object) throws ProviderException
	{
		Session session = openSession();
		Transaction tx = null;

		try
		{
			tx = session.beginTransaction();
			session.update(object);
			tx.commit();
		}
		catch (HibernateException he)
		{
			rollbackTransaction(tx);
			
			if (he instanceof ConstraintViolationException)
			{
				throw new ProviderException(LocalStrings.getString(LocalStrings.ERROR_UNIQUE_CONSTRAINT));
			}
			
			throw new ProviderException(he);
		}
		finally
		{
			closeSession(session);
		}
	}

	public static void delete(Object object)
		throws ProviderException, ConstraintException
	{
		Session session = openSession();
		Transaction tx = null;

		try
		{
			tx = session.beginTransaction();
			session.delete(object);
			tx.commit();
		}
		catch (HibernateException he)
		{
			rollbackTransaction(tx);			
			
			if (he instanceof ConstraintViolationException)
			{
				throw new ConstraintException(he.getMessage());
			}

			throw new ProviderException(he);
		}
		finally
		{
			closeSession(session);
		}
	}

	public static Object load(Class classType, Serializable id)
		throws ProviderException
	{
		Session session = openSession();
		
		try
		{			
			Object object = session.load(classType, id);
			return object;
		}
		catch (HibernateException he)
		{			
			throw new ProviderException(he);
		}
		finally
		{
			closeSession(session);
		}
	}

	public static List query(String fromClause) throws ProviderException
	{
		Session session = openSession();
		
		try
		{
			Query query = session.createQuery(fromClause);
			query.setCacheable(true);
			
			List list = query.list();		

			return list;
		}
		catch (HibernateException he)
		{			
			throw new ProviderException(he);
		}
		finally
		{
			closeSession(session);
		}
	}	
	
	public void destroy()
	{
		try
		{
			sessionFactory.close();
			
			log.info("SessionFactory closed.");
		}
		catch(HibernateException he)
		{
			log.warn(he.toString());
		}
	}

	public static void setSessionFactory(SessionFactory sessionFactory)
	{
		HibernateProvider.sessionFactory = sessionFactory;
	}
}