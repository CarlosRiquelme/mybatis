package py.pol.una.ii.pw.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.ibatis.session.SqlSession;

import py.pol.una.ii.pw.mapper.VentaCabeceraMapper;
import py.pol.una.ii.pw.mapper.VentaDetalleMapper;
import py.pol.una.ii.pw.model.VentaCabecera;
import py.pol.una.ii.pw.model.VentaDetalle;
import py.pol.una.ii.pw.model.Producto;
import py.pol.una.ii.pw.util.Venta;
import py.pol.una.ii.pw.util.VentaDet;
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
			  return  ventacabeceraMapper.selectVentaCabeceraById(id);
		  }finally{
		   session.close();
		  }
		 }
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public static void registerVenta(Venta venta)  {
    	
        
    	try {
    		String ban ="N";
    		for (VentaDet ventaDet: venta.getVentaDetalle()){
				Producto producto = ProductoService.selectProductoById(ventaDet.getId_producto());
				if (producto != null){
					if (producto.getCantidad()<ventaDet.getCantidad()){
						System.out.println("ERROR! se desea vender "+ ventaDet.getCantidad()+" unidades del producto "+ producto.getNombre()+ " pero solo se tiene en stock "+producto.getCantidad()+" unidades.");
						ban="S";
					}
				}
				else{
					System.out.println("El producto con Id "+ventaDet.getId_producto() +" no existe.");
				}
			}
    		if (ban=="N"){
				//Cliente cliente = ClienteService.selectClienteById(venta.getCliente());
				VentaCabecera ventaCabecera = new VentaCabecera();
				ventaCabecera.setFecha(venta.getFecha());
				ventaCabecera.setMonto(venta.getMonto());
				ventaCabecera.setId_cliente(venta.getCliente());
				SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
				VentaCabeceraMapper ventacabeceraMapper = session.getMapper(VentaCabeceraMapper.class);
				ventaCabecera.setId_ventaCabecera(ventacabeceraMapper.selectNextVentaCabecera());
				session.close();
				registerVentaCabecera(ventaCabecera);
				
				for (VentaDet ventaDet: venta.getVentaDetalle()){
					Producto producto = ProductoService.selectProductoById(ventaDet.getId_producto()); 
					System.out.println("producto "+ producto.getNombre());
					VentaDetalle ventaDetalle = new VentaDetalle();
					ventaDetalle.setId_producto(ventaDet.getId_producto());
					ventaDetalle.setCantidad(ventaDet.getCantidad());
					ventaDetalle.setIdVentaCabecera(ventaCabecera.getId_ventaCabecera());
					ventaDetalle.setMonto_parcial(ventaDet.getMonto_parcial());
					/*
					System.out.println("cantidad "+ ventaDetalle.getCantidad());
					System.out.println("monto_parcial "+ ventaDetalle.getMonto_parcial());
					System.out.println("id_producto "+ ventaDetalle.getId_producto());
					System.out.println("id_ventacabecera "+ ventaDetalle.getIdVentaCabecera());
					*/
					registerVentaDetalle(ventaDetalle);
					Float resta = producto.getCantidad() - ventaDetalle.getCantidad();
					producto.setCantidad(resta);
					ProductoService.updateProducto(producto); 
				}
    		}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
    		

    }
    
    public static void registerVentaCabecera(VentaCabecera ventaCabecera)  {
    	
    	System.out.println("Registering COMPRA Cabecera --- Id_cliente: " + ventaCabecera.getId_cliente()+"Id_ventaCabecera: " + ventaCabecera.getId_ventaCabecera());
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
    	try{
    			VentaCabeceraMapper ventacabeceraMapper = session.getMapper(VentaCabeceraMapper.class);
    			ventacabeceraMapper.insertVentaCabecera(ventaCabecera);	
    		}finally{
    			session.close();
    	}
    }

    
    public static void registerVentaDetalle(VentaDetalle ventaDetalle) {
    	
    		System.out.println("Registering COMPRA Detalle --- " + ventaDetalle.getId_producto());
    		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
    		System.out.println("session " + session.getClass());
        	try{
        			VentaDetalleMapper ventadetalleMapper = session.getMapper(VentaDetalleMapper.class);
        			ventadetalleMapper.insertVentaDetalle(ventaDetalle);	
        		}finally{
        			session.close();
        	}
   
    }
	

}
