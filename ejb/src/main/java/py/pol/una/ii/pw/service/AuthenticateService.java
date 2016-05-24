package py.pol.una.ii.pw.service;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.ibatis.session.SqlSession;

import py.pol.una.ii.pw.mapper.AuthenticateMapper;
import py.pol.una.ii.pw.model.Usuario;
import py.pol.una.ii.pw.util.Credentials;
import py.pol.una.ii.pw.util.MyBatisUtil;


@Stateless
public class AuthenticateService {
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public static String autenticar(Usuario usuario){	
			String token=Token.generar(usuario.getUsuario(), usuario.getPass());
			usuario.setAcces_token(token);
			System.out.println("token -->"+token);
			SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
			try{
				AuthenticateMapper aunthenticateMapper = session.getMapper(AuthenticateMapper.class);
				System.out.println("token2 -->"+token);
				aunthenticateMapper.insertUsuario(usuario);	
				System.out.println("token3 -->"+token);
			}finally{
				session.close();
			}
		return token;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public static Usuario selectUsuario (Credentials credentials){
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		Usuario user;
		
		try{
			AuthenticateMapper authenticateMapper = session.getMapper(AuthenticateMapper.class);
			user= authenticateMapper.selectUsuario(credentials);
			if (user.getAcces_token() == null){
				String token=Token.generar(user.getUsuario(), user.getPass());
				user.setAcces_token(token);
				guardarToken(user);
				System.out.println("nombre_user3 -->"+token);
			}
		}finally{
			
			session.close();
		}	
		return user;		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public static void guardarToken(Usuario usuario){
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		Usuario user;
		try {
			
			AuthenticateMapper authenticateMapper = session.getMapper(AuthenticateMapper.class);
			authenticateMapper.updateUsuario(usuario);
		}finally{
			session.close();
		}
		
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public static Usuario verificarToken(String token,String actividad){
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		Usuario user;
		try {
			
			AuthenticateMapper authenticateMapper = session.getMapper(AuthenticateMapper.class);
			user= authenticateMapper.selectToken(token);

			Boolean posee_rol=verificar_rol_compra(user,actividad);
			
			if(posee_rol){
				return user;
			}
			else{
				return null;
			}
		}finally{
			session.close();
		}
	}
	
	public static boolean verificar_rol_compra(Usuario user,String actividad){
		System.out.println("ROL-->"+user.getRol());
		String rol=user.getRol().trim();
		if(rol.equals(actividad) ){
			System.out.println("Tiene permiso "+actividad);
			return true;
		}else{
			System.out.println("No tiene permiso "+actividad);
				return false;
			}
		}
	}
	
	
	


