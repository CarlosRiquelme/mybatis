package py.pol.una.ii.pw.interceptores;


import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

import javax.ws.rs.NameBinding;



@Retention(RUNTIME)
@Target({METHOD, TYPE})
@NameBinding
public @interface CompraAnnotation { }
