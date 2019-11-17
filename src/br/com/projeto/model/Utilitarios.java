/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.model;

import java.awt.Component;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class Utilitarios {
	private static final NumberFormat moneyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
//	private static final SimpleDateFormat dateFormat  = new SimpleDateFormat("dd/MM/yyyy");
	
    public static void LimpaTela(JPanel container) {
        Component components[] = container.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText(null);
            }
        }
    }
    
    public static boolean contemApenasNumeros(String s) {
    	boolean isNumber = true;
    	String pattern = "-0123456789";
    	
    	for(char c : s.toCharArray()) {
    		boolean found = false;
    		
    		for(char charPattern : pattern.toCharArray()) {
    			if(c == charPattern) {
    				found = true;
    				break;
    			}
    		}
    		
    		if(!found) {
    			isNumber = false;
    			break;
    		}
    	}
    	
    	return isNumber;
    }
    
    public static boolean valorMonetarioValido(String valor) {
    	boolean isMoney = true;
    	String pattern = "0123456789,";
    	
    	if(valor.split(",").length > 2) {
    		return false;
    	}
    	
    	for(char charValor : valor.toCharArray()) {
    		boolean found = false;
    		
    		for(char charPattern : pattern.toCharArray()) {
    			if(charValor == charPattern) {
    				found = true;
    				break;
    			}
    		}
    		
    		if(!found) {
    			isMoney = false;
    			break;
    		}
    	}
    	
    	return isMoney;
    }
    
    public static Double toDouble(String monetario) {
    	if(monetario.equals("")) {
    		return 0D;
    	}
    	
    	return Double.parseDouble(monetario.replace(",", "."));
    }
    
    public static String toMoney(Double d) {
    	String money = d.toString();
    	
    	if(d == 0D) {
    		return "0,00";
    	}
    	
    	if(money.contains(".")) {
    		money = money.replace(".", ",");
    	}
    	
    	String [] arrayStr = money.split(",");
    	if(arrayStr[1].length() == 1) {
    		money = money + "0";
    	} else if(arrayStr[1].length() > 2)  {
    		money = arrayStr[0] + "," + arrayStr[1].substring(0, 2);
    	}
    	
    	return money;
    }
    
    public static String formatDecimalNumber(Double valor) {
    	return moneyFormat.format(valor).replace("R$ ", "");
    }
    
    public static String limpaMascara(String valor) {
    	return valor
    			.replace(" ", "")
    			.replace("-", "")
    			.replace("/", "")
    			.replace("(", "")
    			.replace(")", "")
    			.replace(".", "");
    }
    
    public static String retornaNuloSeVazio(String valor) {
    	if(valor != null && !limpaMascara(valor).equals("")) {
    		return valor;
    	} else {
    		return null;
    	}
    }
}
