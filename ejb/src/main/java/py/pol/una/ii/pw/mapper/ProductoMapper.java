package py.pol.una.ii.pw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import py.pol.una.ii.pw.model.Producto;
import py.pol.una.ii.pw.model.ProductoDuplicado;

public interface ProductoMapper {
	
	public Producto selectProductoById(int id);

	public List<Producto> getAllProducto(@Param("desde")int desde ,@Param("cantidad") int cantidad);
	
	public void insertProducto(Producto producto);
	
	public void updateProducto(Producto producto);
	
	public void deleteProducto(int id);
	
	public Producto selectProductoNombre (String nombre);
	
	public void insertProductoDuplicado(ProductoDuplicado productoDuplicado);
	
	public void updateProductoDuplicado(ProductoDuplicado productoDuplicado);
	
	public ProductoDuplicado selectProductoDuplicadoById(int id);

}
