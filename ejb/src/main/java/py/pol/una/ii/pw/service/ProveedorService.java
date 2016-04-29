package py.pol.una.ii.pw.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.ibatis.session.SqlSession;

import py.pol.una.ii.pw.mapper.ProveedorMapper;
import py.pol.una.ii.pw.model.Proveedor;
import py.pol.una.ii.pw.util.MyBatisUtil;



@Stateless
public class ProveedorService {
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public static  List<Proveedor> getAllProveedor() {
		 SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		  try{
			  
			  ProveedorMapper proveedorMapper = session.getMapper(ProveedorMapper.class);
			  return   proveedorMapper.getAllProveedor();
		  }finally{
		   session.close();
		  }
		 }
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public static  Proveedor selectProveedorById(int id) {
		 SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		  try{
			  
			  ProveedorMapper proveedorMapper = session.getMapper(ProveedorMapper.class);
			  return  proveedorMapper.selectProveedorById(id);
		  }finally{
		   session.close();
		  }
		 }
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public static void createProveedor(Proveedor proveedor){
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try{
			ProveedorMapper proveedorMapper = session.getMapper(ProveedorMapper.class);
			proveedorMapper.insertProveedor(proveedor);	
		}finally{
			session.close();
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public static void updateProveedor(Proveedor proveedor){
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try{
			ProveedorMapper proveedorMapper = session.getMapper(ProveedorMapper.class);
			proveedorMapper.updateProveedor(proveedor);	
		}finally{
			session.close();
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public static void deleteProveedor(int id){
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try{
			ProveedorMapper proveedorMapper = session.getMapper(ProveedorMapper.class);
			proveedorMapper.deleteProveedor(id);;	
		}finally{
			session.close();
		}
	}
	


}
