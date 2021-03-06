package com.dat.parking.dao;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dat.parking.model.CarParking;

@Repository("carParkingDao")
public class CarParkingDaoImpl implements CarParkingDao{
	@Autowired
	private SessionFactory sessionFactory;
	Session session;
	
	
	
	
	public void persistInformation(CarParking carParking) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(carParking);
	}
	
	public List buildingLists() {
		// TODO Auto-generated method stub
		 session = this.sessionFactory.getCurrentSession(); 
		List buildingList = new LinkedList(new TreeSet(session.createQuery("select buildingName from CarParking").list()));	
		return buildingList;
	}
	
	public List floorLists(String buildingName) {
		// TODO Auto-generated method stub
		 session=this.sessionFactory.getCurrentSession();
		String hql="select floorName from CarParking where buildingName=:buildingName order by building_id";
		Query query=session.createQuery(hql);
		query.setParameter("buildingName", buildingName);
		List floorList= new LinkedList(new TreeSet(query.list()));
		return floorList;
	}
	
	public List slotLists(String floorName,String buildingName) {
		// TODO Auto-generated method stub
		 session=this.sessionFactory.getCurrentSession();
		String hql="select slot from CarParking where floorName=:floorName AND buildingName=:buildingName order by building_id";
		Query query=session.createQuery(hql);
		query.setParameter("floorName", floorName).setParameter("buildingName", buildingName);
		List slotList= query.list();	
		return slotList;
	}
	
	public void deleteBuilding(String buildingName) {
		// TODO Auto-generated method stub
		 session=this.sessionFactory.getCurrentSession();
		 String hql="delete from CarParking where buildingName=:buildingName";
	     Query query=session.createQuery(hql);
	     query.setParameter("buildingName",buildingName);
	    query.executeUpdate();
		

	}
	
	public void deleteFloor(String buildingName, String floorName) {
		// TODO Auto-generated method stub
		 session=this.sessionFactory.getCurrentSession();
		 String hql="delete from CarParking where buildingName=:buildingName AND floorName=:floorName";
	     Query query=session.createQuery(hql);
	     query.setParameter("buildingName",buildingName).setParameter("floorName", floorName);
	     query.executeUpdate();
		
	}
	
	public void deleteSlot(String buildingName, String floorName, String slot) {
		// TODO Auto-generated method stub
		 session=this.sessionFactory.getCurrentSession();
		 String hql="delete from CarParking where buildingName=:buildingName AND floorName=:floorName AND slot=:slot";
	     Query query=session.createQuery(hql);
	     query.setParameter("buildingName",buildingName).setParameter("floorName", floorName).setParameter("slot", slot);
	     query.executeUpdate();
	}
	
	
	
	public List buildingList(String buildingName) {
		session=this.sessionFactory.getCurrentSession();
		String sql="select buildingName from CarParking where buildingName=:buildingName";
		Query q=session.createQuery(sql);
		q.setParameter("buildingName", buildingName);
		List result=q.list();
		return result;
	}
	
	public void updateStatus(String buildingName, String floorName, String slot) {
		// TODO Auto-generated method stub
		session=this.sessionFactory.getCurrentSession();
		String hql="update CarParking set status='occupied' where buildingName=:buildingName AND floorName=:floorName AND slot=:slot";
		Query query=session.createQuery(hql);
		query.setParameter("buildingName", buildingName).setParameter("floorName", floorName).setParameter("slot", slot);
		query.executeUpdate();
	}
	
	public void updateStatusAvailable(String buildingName, String floorName, String slot) {
		// TODO Auto-generated method stub
		session=this.sessionFactory.getCurrentSession();
		String hql="update CarParking set status='available' where buildingName=:buildingName AND floorName=:floorName AND slot=:slot";
		Query query=session.createQuery(hql);
		query.setParameter("buildingName", buildingName).setParameter("floorName", floorName).setParameter("slot", slot);
		query.executeUpdate();
	}
	
	public String getStatus(String buildingName, String floorName, String slot) {
		// TODO Auto-generated method stub
		session=this.sessionFactory.getCurrentSession();
		String hql="select status from CarParking where buildingName=:buildingName AND floorName=:floorName AND slot=:slot";
		Query query=session.createQuery(hql);
		query.setParameter("buildingName", buildingName).setParameter("floorName", floorName).setParameter("slot", slot);
		return (String)query.uniqueResult();
		
	}
	
	
	//Check Exiting Building Name?
    public List buildList(String buildingName) {
    	session =this.sessionFactory.getCurrentSession();
    	String sql="select buildingName from CarParking where buildingName=:buildingName";
    	Query q=session.createQuery(sql);
    	q.setParameter("buildingName",buildingName);
    	List r=q.list();
    	return r;
    }
    
    //check for delete building with status
    public List statusBuildingList(String buildingName) {
    	session=this.sessionFactory.getCurrentSession();
    	String sql="select id from CarParking where  buildingName=:buildingName and status='occupied'";
    	Query q=session.createQuery(sql);
    	q.setParameter("buildingName",buildingName);
    	List t=q.list();
    	return t;
    	
    }
    
    //Check for delete floor with status
    public List statusFloorList(String buildingName,String floorName) {
    	session=this.sessionFactory.getCurrentSession();
    	String sql="select id from CarParking where buildingName=:buildingName and floorName=:floorName and status='occupied'";
    	Query q=session.createQuery(sql);
    	q.setParameter("buildingName",buildingName).setParameter("floorName",floorName);
    	List t=q.list();
    	return t;
    }
	
    //Check for delete slot with status
    public List statusSlotList(String buildingName,String floorName,String slot) {
    	session=this.sessionFactory.getCurrentSession();
    	String sql="select id from CarParking where buildingName=:buildingName and floorName=:floorName and slot=:slot and status='occupied'";
    	Query q=session.createQuery(sql);
    	q.setParameter("buildingName",buildingName).setParameter("floorName",floorName).setParameter("slot",slot);
    	List t=q.list();
    	return t;
    }

	@Override
	public List getAvailableSlot(String buildingName, String floorName) {
		// TODO Auto-generated method stub
		session=this.sessionFactory.getCurrentSession();
    	String sql="select slot from CarParking where buildingName=:buildingName and floorName=:floorName and status='available'";
    	Query q=session.createQuery(sql);
    	q.setParameter("buildingName",buildingName).setParameter("floorName",floorName);
    	List availableSlot=q.list();
    	return availableSlot;
	}

	@Override
	public void disableSlot(String buildingName, String floorName, String slot) {
		// TODO Auto-generated method stub
		session=this.sessionFactory.getCurrentSession();
    	String sql="update CarParking set status='disable' where buildingName=:buildingName AND floorName=:floorName AND slot=:slot";
    	Query q=session.createQuery(sql);
    	q.setParameter("buildingName",buildingName).setParameter("floorName",floorName).setParameter("slot", slot);
    	q.executeUpdate();
	}

	@Override
	public List getDisableSlot(String buildingName, String floorName) {
		// TODO Auto-generated method stub
		session=this.sessionFactory.getCurrentSession();
    	String sql="select slot from CarParking where buildingName=:buildingName and floorName=:floorName and status='disable'";
    	Query q=session.createQuery(sql);
    	q.setParameter("buildingName",buildingName).setParameter("floorName",floorName);
    	List disableSlot=q.list();
    	return disableSlot;
	}

	
	
	
}
