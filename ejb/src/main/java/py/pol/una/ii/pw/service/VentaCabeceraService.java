package py.pol.una.ii.pw.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.ibatis.session.SqlSession;

import py.pol.una.ii.pw.mapper.CompraCabeceraMapper;
import py.pol.una.ii.pw.model.CompraCabecera;
import py.pol.una.ii.pw.util.MyBatisUtil;

@Stateless
public class VentaCabeceraService {
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public static  List<VentaCabecera> getAllVentaCabecera() {
		 SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		  try{
			  
			  VentaCabeceraMapper ventacabeceraMapper = session.getMapper(VentaCabeceraMapper.class);
			  return   ventacabeceraMapper.getAllVentaCabecera();
		  }finally{
		   session.close();
		  }
		 }
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public static  VentaCabecera selectVentaCabeceraById(int id) {
		 SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		  try{
			  
			  VentaCabeceraMapper ventacabeceraMapper = session.getMapper(VentaCabeceraMapper.class);
			  return  ventacabeceraMapper.selectCompraCabeceraById(id);
		  }finally{
		   session.close();
		  }
		 }
	

}
