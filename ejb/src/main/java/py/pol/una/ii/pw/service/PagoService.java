package py.pol.una.ii.pw.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.ibatis.session.SqlSession;
 
import py.pol.una.ii.pw.mapper.PagoMapper;
import py.pol.una.ii.pw.model.Pago;  
import py.pol.una.ii.pw.util.MyBatisUtil;

@Stateless
public class PagoService {
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public static  List<Pago> getAllPago() {
		 SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		  try{
			  
			  PagoMapper pagoMapper = session.getMapper(PagoMapper.class);
			  return   pagoMapper.getAllPago();
		  }finally{
		   session.close();
		  }
		 }
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public static  Pago selectPagoById(int id) {
		 SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		  try{
			  
			  PagoMapper pagoMapper = session.getMapper(PagoMapper.class);
			  return  pagoMapper.selectPagoById(id);
		  }finally{
		   session.close();
		  }
		 }
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public static void createPago(Pago pago){
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try{
			PagoMapper pagoMapper = session.getMapper(PagoMapper.class);
			pagoMapper.insertPago(pago);	
		}finally{
			session.close();
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public static void updatePago(Pago pago){
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try{
			PagoMapper pagoMapper = session.getMapper(PagoMapper.class);
			pagoMapper.updatePago(pago);	
		}finally{
			session.close();
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public static void deletePago(int id){
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try{
			PagoMapper pagoMapper = session.getMapper(PagoMapper.class);
			pagoMapper.deletePago(id);;
		}finally{
			session.close();
		}
	}
	

}
