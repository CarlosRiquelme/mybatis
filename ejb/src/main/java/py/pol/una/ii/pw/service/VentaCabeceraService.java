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
import py.pol.una.ii.pw.model.Cliente;
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
				Producto producto = ProductoService.selectProductoById(ventaDet.getProducto());
				if (producto.getCantidad()<ventaDet.getCantidad()){
					System.out.println("ERROR! se desea vender "+ ventaDet.getCantidad()+" unidades del producto "+ producto.getNombre()+ " pero solo se tiene en stock "+producto.getCantidad()+" unidades.");
					ban="S";
				}
			}
    		if (ban=="N"){
				//Cliente cliente = ClienteService.selectClienteById(venta.getCliente());
				VentaCabecera ventaCabecera = new VentaCabecera();
				ventaCabecera.setFecha(venta.getFecha());
				ventaCabecera.setMonto(venta.getMonto());
				ventaCabecera.setId_cliente(venta.getCliente());
				
				registerVentaCabecera(ventaCabecera);
				
				for (VentaDet ventaDet: venta.getVentaDetalle()){
					Producto producto = ProductoService.selectProductoById(ventaDet.getProducto()); 
					VentaDetalle ventaDetalle = new VentaDetalle();
					ventaDetalle.setProducto(producto);
					ventaDetalle.setCantidad(ventaDet.getCantidad());
					ventaDetalle.setVentaCabecera(ventaCabecera);
					ventaDetalle.setMonto_parcial(ventaDet.getMonto_parcial());
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
    	
    	System.out.println("Registering COMPRA Cabecera --- Id_cliente: " + ventaCabecera.getId_cliente());
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
    	try{
    			VentaCabeceraMapper ventacabeceraMapper = session.getMapper(VentaCabeceraMapper.class);
    			ventacabeceraMapper.insertVentaCabecera(ventaCabecera);	
    		}finally{
    			session.close();
    	}
    }

    
    public static void registerVentaDetalle(VentaDetalle ventaDetalle) {
    	
    		System.out.println("Registering COMPRA Detalle --- " + ventaDetalle.getProducto().getNombre());
    		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        	try{
        			VentaDetalleMapper ventadetalleMapper = session.getMapper(VentaDetalleMapper.class);
        			ventadetalleMapper.insertVentaDetalle(ventaDetalle);	
        		}finally{
        			session.close();
        	}
   
    }
	

}
