package py.pol.una.ii.pw.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.ibatis.session.SqlSession;

import py.pol.una.ii.pw.mapper.ClienteMapper;
import py.pol.una.ii.pw.model.Cliente;
import py.pol.una.ii.pw.util.MyBatisUtil;

@Stateless
public class ClienteService {
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public static  List<Cliente> getAllCliente() {
		 SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		  try{
			  
			  ClienteMapper clienteMapper = session.getMapper(ClienteMapper.class);
			  return   clienteMapper.getAllCliente();
		  }finally{
		   session.close();
		  }
		 }
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public static  Cliente selectClienteById(int id) {
		 SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		  try{
			  
			  ClienteMapper clienteMapper = session.getMapper(ClienteMapper.class);
			  return  clienteMapper.selectClienteById(id);
		  }finally{
		   session.close();
		  }
		 }
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public static void createCliente(Cliente cliente){
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try{
			ClienteMapper clienteMapper = session.getMapper(ClienteMapper.class);
			clienteMapper.insertCliente(cliente);	
		}finally{
			session.close();
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public static void updateCliente(Cliente cliente){
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try{
			ClienteMapper clienteMapper = session.getMapper(ClienteMapper.class);
			clienteMapper.updateCliente(cliente);	
		}finally{
			session.close();
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public static void deleteCliente(int id){
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try{
			ClienteMapper clienteMapper = session.getMapper(ClienteMapper.class);
			clienteMapper.deleteCliente(id);;	
		}finally{
			session.close();
		}
	}
	
	

}
