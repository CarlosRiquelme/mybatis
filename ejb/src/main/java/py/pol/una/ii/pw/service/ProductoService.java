package py.pol.una.ii.pw.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;

import py.pol.una.ii.pw.mapper.ProductoMapper;
import py.pol.una.ii.pw.model.Producto;
import py.pol.una.ii.pw.model.ProductoDuplicado;
import py.pol.una.ii.pw.util.MyBatisUtil;

@Stateless
public class ProductoService {
	
	//@Inject
	//private static Event<Producto> productoEventSrc;
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public static  List<Producto> getAllProducto(int desde, int cantidad) {
		 SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		  try{
			  
			  ProductoMapper productoMapper = session.getMapper(ProductoMapper.class);
			  return   productoMapper.getAllProducto(desde, cantidad);
		  }finally{
		   session.close();
		  }
		 }
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public static  Producto selectProductoById(int id) {
		 SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		  try{
			  
			  ProductoMapper productoMapper = session.getMapper(ProductoMapper.class);
			  return  productoMapper.selectProductoById(id);
		  }finally{
		   session.close();
		  }
		 }
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public static void createProducto(Producto producto) throws Exception{
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try{
			try{
				ProductoMapper productoMapper = session.getMapper(ProductoMapper.class);
				//System.out.println("producto " + productoMapper.getClass().getName());
				Producto p = productoMapper.selectProductoNombre(producto.getNombre());
				//System.out.println("producto " + p);
				if (p == null){
					productoMapper.insertProducto(producto);
					//productoEventSrc.fire(producto);
				}else{
					System.out.println("El producto "+ producto.getNombre()+" ya exite, se guardata en la tabla de productos duplicados.");
					guardarDuplicado(producto);
				}
			}catch(Exception e){
				//System.out.println("producto error");
				throw e;
			}
		}finally{
			
			session.close();
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public static void guardarDuplicado(Producto producto){
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try{
			ProductoMapper productoMapper = session.getMapper(ProductoMapper.class);
			Producto existente= productoMapper.selectProductoNombre(producto.getNombre());
			Integer id=existente.getId_producto();
			ProductoDuplicado pd= productoMapper.selectProductoDuplicadoById(id);
			
			if (pd == null){
				pd= new ProductoDuplicado();
				pd.setId_producto(id);
				pd.setCantidad(1L);
				productoMapper.insertProductoDuplicado(pd);	
			}else{
				pd.setCantidad(pd.getCantidad() + 1);
				productoMapper.updateProductoDuplicado(pd);
			}
			
			
		}finally{
			session.close();
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public static void updateProducto(Producto producto){
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try{
			ProductoMapper productoMapper = session.getMapper(ProductoMapper.class);
			productoMapper.updateProducto(producto);	
		}finally{
			session.close();
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public static void deleteProducto(int id){
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try{
			ProductoMapper productoMapper = session.getMapper(ProductoMapper.class);
			productoMapper.deleteProducto(id);;	
		}finally{
			session.close();
		}
	}

}
