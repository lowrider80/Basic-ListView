package com.supervision.sqlite;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.supervision.communications.GetterHTTP;
import com.supervision.sqlite.dao.ACTACOLEGIOSITEM_DAO;
import com.supervision.sqlite.dao.COLEGIOSITEM_DAO;
import com.supervision.sqlite.dao.COMPONENTERACION_DAO;
import com.supervision.sqlite.dao.CONTRATO_DAO;
import com.supervision.sqlite.dao.DETALLELIBERACION_DAO;
import com.supervision.sqlite.dao.FICHATECNICAPRESENTACION_DAO;
import com.supervision.sqlite.dao.MATERIAPRIMA_DAO;
import com.supervision.sqlite.dao.SMBCOPREGUNTAS_DAO;
import com.supervision.sqlite.dao.SMCAUSALES_DAO;
import com.supervision.sqlite.dao.SMFORMATO_DAO;
import com.supervision.sqlite.dao.SMMAEFICHAS_DAO;
import com.supervision.sqlite.dao.TABLETPOSTORES_DAO;
import com.supervision.sqlite.dao.TABLETPROVEEDORES_DAO;
import com.supervision.sqlite.dao.PROVEEDORESITEMS;
import com.supervision.sqlite.dao.TABLETSUPERVISORES_DAO;
import com.supervision.sqlite.dao.TABLETVEHICULOS_DAO;;

public class  setServicio {

	
	public static int updateSMBCOPREGUNTAS(Context context)
	{
		int index = 0;
		GetterHTTP servicio = new GetterHTTP();
		servicio.execute("GetListaBancoPreguntas");
		SMBCOPREGUNTAS_DAO.Borrar(context);
		
		try
		{
			JSONObject objeto = new JSONObject(servicio.get());			
			index=SMBCOPREGUNTAS_DAO.AgregarServicio(context,  new JSONArray(objeto.getString("GetListaBancoPreguntasResult")));
		}
		catch(Exception exception)
		{
			return 0;
		}	
		return index;
	}
	
	public static int updateSMCAUSALES(Context context) 
	{
		int index = 0;
		GetterHTTP servicio = new GetterHTTP();
		servicio.execute("GetListaCausales");
		SMCAUSALES_DAO.Borrar(context);
		try
		{
			JSONObject objeto = new JSONObject(servicio.get());
			index=SMCAUSALES_DAO.AgregarServicio(context, new JSONArray(objeto.getString("GetListaCausalesResult")));
		}
		catch(Exception exception)
		{
			return 0;
		}	
		return index;
	}

	public static int updateTABLETPROVEEDORES(Context context,String icodUT) 
	{
		int index = 0;		
		try
		{
			TABLETPROVEEDORES_DAO.Borrar(context);
			int codigoTMP=Integer.parseInt(icodUT.trim());
			String dataProveedores="GetListaProveedores/?icodUT="+codigoTMP;
			GetterHTTP servicio = new GetterHTTP();
			servicio.execute(dataProveedores);
		
			JSONObject objeto = new JSONObject(servicio.get());
			
			index =TABLETPROVEEDORES_DAO.AgregarServicio(context, new JSONArray(objeto.getString("GetListaProveedoresResult")));

		}
		catch(Exception exception)
		{			
			return 0;
		}	
		return index;
	}


	public static int updateTABLETSUPERVISORES(Context context,String icodUT)
	{
		int index = 0;
		try
		{
			TABLETSUPERVISORES_DAO.Borrar(context);
			int codigoTMP=Integer.parseInt(icodUT.trim());
			String dataSupervisores="GetListaSupervisores/?icodUT="+codigoTMP;
			GetterHTTP servicio = new GetterHTTP();
			servicio.execute(dataSupervisores);

			JSONObject objeto = new JSONObject(servicio.get());

			index = TABLETSUPERVISORES_DAO.AgregarServicio(context, new JSONArray(objeto.getString("GetListaSupervisoresResult")));

		}
		catch(Exception exception)
		{
			return 0;
		}
		return index;
	}


	public static int updateTABLETVEHICULOS(Context context,String icodUT)
	{
		int index = 0;
		try
		{
			TABLETVEHICULOS_DAO.Borrar(context);
			int codigoTMP=Integer.parseInt(icodUT.trim());
			String dataSupervisores="GetListaVehiculos/?icodUT="+codigoTMP;
			GetterHTTP servicio = new GetterHTTP();
			servicio.execute(dataSupervisores);

			JSONObject objeto = new JSONObject(servicio.get());

			index = TABLETVEHICULOS_DAO.AgregarServicio(context, new JSONArray(objeto.getString("GetListaVehiculosResult")));

		}
		catch(Exception exception)
		{
			return 0;
		}
		return index;
	}

	public static int updateTABLETPOSTORES(Context context,String icodUT) 
	{
		int index = 0;		
		try
		{
			TABLETPOSTORES_DAO.Borrar(context);
			int codigoTMP=Integer.parseInt(icodUT.trim());
			String dataProveedores="GetListaPostores/?iCodUT="+codigoTMP;
			GetterHTTP servicio = new GetterHTTP();
			servicio.execute(dataProveedores);
		
			JSONObject objeto = new JSONObject(servicio.get());
			
			index =TABLETPOSTORES_DAO.AgregarServicio(context, new JSONArray(objeto.getString("GetListaPostoresResult")));

		}
		catch(Exception exception)
		{			
			return 0;
		}	
		return index;
	}
	
	public static int updateSMFORMATO(Context context) 
	{
		int index=0;
		GetterHTTP servicio = new GetterHTTP();
		servicio.execute("GetListaFormatos");	
		SMFORMATO_DAO.Borrar(context);
		try
		{
			JSONObject objeto = new JSONObject(servicio.get());
			index = SMFORMATO_DAO.AgregarServicio(context, new JSONArray(objeto.getString("GetListaFormatosResult")));
		}
		catch(Exception exception)
		{
			return 0;
		}
		
		return index;
	}
		
	public static int updateSMMAEFICHAS(Context context)
	{
		int index=0;
		GetterHTTP servicio = new GetterHTTP();
		servicio.execute("GetListaFichas");		
		
		SMMAEFICHAS_DAO.Borrar(context);
		try
		{
			JSONObject objeto = new JSONObject(servicio.get());
			index=SMMAEFICHAS_DAO.AgregarServicio(context, new JSONArray(objeto.getString("GetListaFichasResult")));               
           
		}
		catch(Exception exception)
		{
			return 0;
		}
		
		return index;
	}
		
	/*	
	public static int updateMAEDEPARTAMENTO(Context context) 
	{
		int index=0;
		GetterHTTP servicio = new GetterHTTP();
		servicio.execute("GetDepartamento");		
		MAEDEPARTAMENTO_DAO.Borrar(context);
		try
		{
			JSONObject objeto = new JSONObject(servicio.get());
			
			index = MAEDEPARTAMENTO_DAO.AgregarServicio(context, new JSONArray(objeto.getString("GetDepartamentoResult")));
		
			Log.e("updateMAEDEPARTAMENTO","insertando " + String.valueOf(index) + " registros");
		}
		catch(Exception exception)
		{
			return 0;
		}
		
		return index;
	}
	
	public static int updateMAEDISTRITO(Context context) 
	{
		int index=0;
		GetterHTTP servicio = new GetterHTTP();
		servicio.execute("GetDistrito");		
		MAEDISTRITO_DAO.Borrar(context);
		try
		{
			JSONObject objeto = new JSONObject(servicio.get());
			
			index = MAEDISTRITO_DAO.AgregarServicio(context, new JSONArray(objeto.getString("GetDistritoResult")));
		
			Log.e("updateMAEDISTRITO","insertando " + String.valueOf(index) + " registros");
		}
		catch(Exception exception)
		{
			return 0;
		}
		
		return index;
	}
	
	public static int updateMAEPROVINCIA(Context context) 
	{
		int index=0;
		GetterHTTP servicio = new GetterHTTP();
		servicio.execute("GetProvincia");		
		MAEPROVINCIA_DAO.Borrar(context);
		try
		{
			JSONObject objeto = new JSONObject(servicio.get());
			
			index = MAEPROVINCIA_DAO.AgregarServicio(context, new JSONArray(objeto.getString("GetProvinciaResult")));
		
			Log.e("MAEPROVINCIA","insertando " + String.valueOf(index) + " registros");
		}
		catch(Exception exception)
		{
			return 0;
		}
		
		return index;
	}
	
*/
	public static int updateDETALLELIBERACION(Context context,String icodUT) 
	{
		int index = 0;		
		try
		{
			DETALLELIBERACION_DAO.Borrar(context);
			int codigoTMP=Integer.parseInt(icodUT.trim());
			String dataProveedores="GetListaLiberacionDetalleXUT_B/?UnidadTerritorial="+codigoTMP;
			GetterHTTP servicio = new GetterHTTP();
			servicio.execute(dataProveedores);
		
			JSONObject objeto = new JSONObject(servicio.get());
			
			//index =DETALLELIBERACION_DAO.AgregarServicio(context, new JSONArray(objeto.getString("GetListaLiberacionDetalleXUTResult")));
			index =DETALLELIBERACION_DAO.AgregarServicio(context, new JSONArray(objeto.getString("GetListaLiberacionDetalleXUT_BResult")));

		}
		catch(Exception exception)
		{			
			return 0;
		}	
		return index;
	}
	public static int updateCONTRATO(Context context,String icodUT) 
	{
		int index = 0;		
		try
		{
			DETALLELIBERACION_DAO.Borrar(context);
			int codigoTMP=Integer.parseInt(icodUT.trim());
			String dataProveedores="GetListaContratoXUT/?UnidadTerritorial="+codigoTMP;
			GetterHTTP servicio = new GetterHTTP();
			servicio.execute(dataProveedores);
		
			JSONObject objeto = new JSONObject(servicio.get());
			
			index =CONTRATO_DAO.AgregarServicio(context, new JSONArray(objeto.getString("GetListaContratoXUTResult")));

		}
		catch(Exception exception)
		{			
			return 0;
		}	
		return index;
	}
	public static int updateFICHATECNICAPRESENTACION(Context context) 
	{
		int index=0;
		GetterHTTP servicio = new GetterHTTP();
		servicio.execute("GetListaFichaTecnicaPresentacion");		
		FICHATECNICAPRESENTACION_DAO.Borrar(context);
		try {
			JSONObject objeto = new JSONObject(servicio.get());
			
			index = FICHATECNICAPRESENTACION_DAO.AgregarServicio(context, new JSONArray(objeto.getString("GetListaFichaTecnicaPresentacionResult")));
		}
		catch(Exception exception) {
			return 0;
		}
		return index;
	}
	
	public static int updatePROVEEDORESITEMS(Context context,String icodUT) {
		int index = 0;		
		try {
			//se borro esta llamada innecesaria			
			//DETALLELIBERACION_DAO.Borrar(context);
			int codigoTMP=Integer.parseInt(icodUT.trim());
			String dataProveedores="GetListaItemsProveedoresXUT/?UnidadTerritorial="+codigoTMP;
			GetterHTTP servicio = new GetterHTTP();
			servicio.execute(dataProveedores);
		
			JSONObject objeto = new JSONObject(servicio.get());
			
			index =PROVEEDORESITEMS.AgregarServicio(context, new JSONArray(objeto.getString("GetListaItemsProveedoresXUTResult")));

		}
		catch(Exception exception) {
			return 0;
		}	
		return index;
	}
	

	public static int updateLIBERACIONRACION(Context context,String icodUT,String fecha,String cRucProveedor ) 
	{
		int index = 0;		
		try
		{
			//se borro esta llamada innecesaria			
			//DETALLELIBERACION_DAO.Borrar(context);
			int codigoTMP=Integer.parseInt(icodUT.trim());
			String dataProveedores="GetListaLiberacionRacion/?iCodUT="+codigoTMP +"&dFechaLiberacion="+fecha+ "&cRucProveedor="+ cRucProveedor;
			GetterHTTP servicio = new GetterHTTP();
			servicio.execute(dataProveedores);
		
			JSONObject objeto = new JSONObject(servicio.get());
			
			index =ACTACOLEGIOSITEM_DAO.AgregarServicio(context, new JSONArray(objeto.getString("GetListaLiberacionRacionResult")));
		}
		catch(Exception exception)
		{			
			return 0;
		}	
		return index;
	}

	public static int updateMATERIAPRIMA(Context context,String icodUT, String cCodProveedor )
	{
		int index = 0;
		try
		{
			//se borro esta llamada innecesaria
			//DETALLELIBERACION_DAO.Borrar(context);
			int codigoTMP=Integer.parseInt(icodUT.trim());
			String dataMateriaPrima="GetListaMateriaPrima/?iCodUT="+codigoTMP +"&cCodProveedor="+ cCodProveedor;
			GetterHTTP servicio = new GetterHTTP();
			servicio.execute(dataMateriaPrima);

			JSONObject objeto = new JSONObject(servicio.get());

			index = MATERIAPRIMA_DAO.AgregarServicio(context, new JSONArray(objeto.getString("GetListaMateriaPrimaResult")));
		}
		catch(Exception exception)
		{
			return 0;
		}
		return index;
	}


	
	public static int updateCOMPONENTERACION(Context context) 
	{
		int index = 0;		
		try
		{
			
			//se borro esta llamada innecesaria			
			//DETALLELIBERACION_DAO.Borrar(context);
			String dataProveedores="GetComponentesRacion";
			GetterHTTP servicio = new GetterHTTP();
			servicio.execute(dataProveedores);
		
			JSONObject objeto = new JSONObject(servicio.get());
			
			index =COMPONENTERACION_DAO.AgregarServicio(context, new JSONArray(objeto.getString("GetComponentesRacionResult")));

		}
		catch(Exception exception)
		{			
			return 0;
		}	
		return index;
	}
	
}
