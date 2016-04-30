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
public class CompraCabeceraService {
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public static  List<CompraCabecera> getAllCompraCabecera() {
		 SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		  try{
			  
			  CompraCabeceraMapper compracabeceraMapper = session.getMapper(CompraCabeceraMapper.class);
			  return   compracabeceraMapper.getAllCompraCabecera();
		  }finally{
		   session.close();
		  }
		 }
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public static  CompraCabecera selectCompraCabeceraById(int id) {
		 SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		  try{
			  
			  CompraCabeceraMapper compracabeceraMapper = session.getMapper(CompraCabeceraMapper.class);
			  return  compracabeceraMapper.selectCompraCabeceraById(id);
		  }finally{
		   session.close();
		  }
		 }
	

}
