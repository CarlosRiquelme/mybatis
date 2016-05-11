package py.pol.una.ii.pw.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.ibatis.session.SqlSession;

import py.pol.una.ii.pw.mapper.CompraCabeceraMapper;
import py.pol.una.ii.pw.mapper.CompraDetalleMapper;
import py.pol.una.ii.pw.mapper.VentaCabeceraMapper;
import py.pol.una.ii.pw.model.CompraCabecera;
import py.pol.una.ii.pw.model.CompraDetalle;
import py.pol.una.ii.pw.model.Producto;
import py.pol.una.ii.pw.model.Proveedor;
import py.pol.una.ii.pw.util.Compra;
import py.pol.una.ii.pw.util.CompraDet;
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
	
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public static void registerCompra(Compra compra)  {
    	
        
    	try {

			//Proveedor proveedor = ProveedorService.selectProveedorById(compra.getProveedor());
			CompraCabecera compraCabecera = new CompraCabecera();
			compraCabecera.setFecha(compra.getFecha());
			compraCabecera.setMonto(compra.getMonto());
			compraCabecera.setProveedor(compra.getProveedor());
			SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
			CompraCabeceraMapper compracabeceraMapper = session.getMapper(CompraCabeceraMapper.class);
			compraCabecera.setId_compraCabecera(compracabeceraMapper.selectNextCompraCabecera());
			session.close();
			
			registerCompraCabecera(compraCabecera);
			
			for (CompraDet compraDet: compra.getCompraDetalle()){
				Producto producto = ProductoService.selectProductoById(compraDet.getProducto());
				System.out.println("producto "+ producto.getNombre());
				CompraDetalle compraDetalle = new CompraDetalle();
				compraDetalle.setProducto(compraDet.getProducto()); 
				compraDetalle.setCantidad(compraDet.getCantidad());
				compraDetalle.setCompraCabecera(compraCabecera.getId_compraCabecera());
				registerCompraDetalle(compraDetalle);
				Float suma = producto.getCantidad() + compraDetalle.getCantidad();
				producto.setCantidad(suma);
				ProductoService.updateProducto(producto);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
    		

    }
    
    public static void registerCompraCabecera(CompraCabecera compraCabecera)  {
    	
    	System.out.println("Registering COMPRA Cabecera --- " + compraCabecera.getProveedor());
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
    	try{
    			CompraCabeceraMapper compracabeceraMapper = session.getMapper(CompraCabeceraMapper.class);
    			compracabeceraMapper.insertCompraCabecera(compraCabecera);	
    		}finally{
    			session.close();
    	}
    }

    
    public static void registerCompraDetalle(CompraDetalle compraDetalle) {
    	
    		System.out.println("Registering COMPRA Detalle --- " + compraDetalle.getProducto());
    		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        	try{
        			CompraDetalleMapper compradetalleMapper = session.getMapper(CompraDetalleMapper.class);
        			compradetalleMapper.insertCompraDetalle(compraDetalle);	
        		}finally{
        			session.close();
        	}
   
    }
	

}
