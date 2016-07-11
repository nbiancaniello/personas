package com.mercel.personas.validations;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.ibm.icu.util.Calendar;

public final class ValidadorDatos {
	private static Pattern pattern;
	private static Matcher matcher;
	
	private static final String DATE_VALIDATION_PATTERN = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
	
	public static boolean esAlfabetico(String cadena){
    	for(char ch : cadena.toCharArray()){
            if(Character.isDigit(ch)){
                return false;
            }
        }
        return true;
    }
    
	public static boolean esVacio(String cadena){
    	return StringUtils.isBlank(cadena) ? true : false;
    }
	
    public static boolean esNumerico(int nro){
    	for(char ch : Integer.toString(nro).toCharArray()){
            if(!Character.isDigit(ch)){
                return false;
            }
        }
        return true;
    }
    
    public static boolean verificarLongitud(String cadena, int longitud){
    	return (cadena.length() <= longitud) ? true : false;
    }   
    
	public ValidadorDatos(){
		pattern = Pattern.compile(DATE_VALIDATION_PATTERN);
	}
	
	public static boolean validarFecha(final Date fecha){
		
	try{
		Calendar dCal = Calendar.getInstance();
		dCal.setTime(fecha);
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
		String date = format1.format(dCal.getTime());
		pattern = Pattern.compile(DATE_VALIDATION_PATTERN);
		matcher = pattern.matcher(date);
		
		if(matcher.matches()){
		
			matcher.reset();
		
			if(matcher.find()){
		
				String dd = matcher.group(1);
			    String mm = matcher.group(2);
			    int yy = Integer.parseInt(matcher.group(3));
			    
			    if (dd.equals("31") &&  
			    	(mm.equals("4") || mm .equals("6") || mm.equals("9") || mm.equals("11") || mm.equals("04") || mm .equals("06") || mm.equals("09"))) {
			    	return false;
			    } else if (mm.equals("2") || mm.equals("02")) {
			    	if(yy % 4==0){
			    		if(dd.equals("30") || dd.equals("31")){
			    			return false;
			    		}else{
			    			return true;
			    		}
			    	}else{
				         if(dd.equals("29")||dd.equals("30")||dd.equals("31")){
				        	 return false;
				         }else{
				        	 return true;
				         }
			    	}
			    }else{				 
			    	return true;				 
			      }
			   }else{
		    	      return false;
			   }		  
		     }else{
			  return false;
		     }
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}		    
	}
}
