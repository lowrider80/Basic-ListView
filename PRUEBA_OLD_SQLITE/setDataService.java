package com.supervision.sqlite;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.supervision.entities.ACTACOLEGIOSITEM;
import com.supervision.entities.LIBERACIONRECORDCARDITEM;
import com.supervision.entities.RECORDCARDITEM;
import com.supervision.entities.SMALMACEN;
import com.supervision.entities.SMDETFOTOS;
import com.supervision.entities.SMEQUIPOS;
import com.supervision.entities.SMFICHAINICIALANEXO;
import com.supervision.entities.SMFICHASGBOTROS;
import com.supervision.entities.SMFICHASGRABADAS;
import com.supervision.entities.SMITEMSTRANSPORTE;
import com.supervision.entities.SMPUNTOCRITICO;
import com.supervision.sqlite.dao.ACTACOLEGIOSITEM_DAO;
import com.supervision.sqlite.dao.RECORDCARDITEM_DAO;
import com.supervision.sqlite.dao.SESION_DAO;
import com.supervision.sqlite.dao.SMALMACEN_DAO;
import com.supervision.sqlite.dao.SMDETFOTOS_DAO;
import com.supervision.sqlite.dao.SMEQUIPOS_DAO;
import com.supervision.sqlite.dao.SMFICHAINICIALANEXO_DAO;
import com.supervision.sqlite.dao.SMFICHASGOTROS_DAO;
import com.supervision.sqlite.dao.SMITEMSTRANSPORTE_DAO;
import com.supervision.sqlite.dao.SMPUNTOCRITICO_DAO;

public class setDataService {

	public static String  SendData(Context context,SMFICHASGRABADAS OBJSMFICHASGRABADAS) {
		String Data="";

		try {
			JSONObject objJSON=new JSONObject();
			objJSON.put("iCodFicha",OBJSMFICHASGRABADAS.iCodFicha);
			objJSON.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
			objJSON.put("vCodProveedor",OBJSMFICHASGRABADAS.cCodProveedor);
			objJSON.put("vCodEstablecimiento",OBJSMFICHASGRABADAS.cCodEstablecimiento);
			
			SimpleDateFormat formatted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
			
			objJSON.put("sdtFechaSupervicion",formatted.format(OBJSMFICHASGRABADAS.lFecha));
			objJSON.put("sdtFecTermino",formatted.format(OBJSMFICHASGRABADAS.lFechaFin));

			objJSON.put("vFechaVigenciaSanidad",formatted.format(new Date(OBJSMFICHASGRABADAS.vFechaVigenciaSanidad)));
			objJSON.put("vFechaVigenciaCertificadoMedico",formatted.format(new Date(OBJSMFICHASGRABADAS.vFechaVigenciaCertificadoMedico)));

			objJSON.put("vEspecialidad",OBJSMFICHASGRABADAS.vEspecialidad);
			objJSON.put("vColegiatura",OBJSMFICHASGRABADAS.vColegiatura);

			objJSON.put("vTipoVehiculo",OBJSMFICHASGRABADAS.vTipoVehiculo);			
			objJSON.put("vPlaca",OBJSMFICHASGRABADAS.vPlaca);
			objJSON.put("vGuiaRemision",OBJSMFICHASGRABADAS.vGuiaRemision);

			objJSON.put("dPorcentaje",OBJSMFICHASGRABADAS.dPuntajeFicha);
			objJSON.put("iPuntaje",RECORDCARDITEM_DAO.GetPuntos(context, OBJSMFICHASGRABADAS.idFichasGrabadas));
			objJSON.put("vObservacion",OBJSMFICHASGRABADAS.vObservacion);
			objJSON.put("iTotalOperarios",OBJSMFICHASGRABADAS.iTotalOperarios);
			//objJSON.put("iCodUsuario",Integer.parseInt(SESION_DAO.BuscarUt(context)));//falta
			objJSON.put("vComite",SESION_DAO.BuscarUt(context));

			objJSON.put("iCantidadRacionesAdjudicadas",OBJSMFICHASGRABADAS.iCantidadRacionesAdjudicadas);
			objJSON.put("iCantidadRacionesVerificadas",OBJSMFICHASGRABADAS.iCantidadRacionesVerificadas);
			objJSON.put("vNombreRacionesVerificadas",OBJSMFICHASGRABADAS.vNombreRacionesVerificadas);
			objJSON.put("vTurno",OBJSMFICHASGRABADAS.vTurno);
			objJSON.put("iNumeroOperariosHombre",OBJSMFICHASGRABADAS.iNumeroOperariosHombre);
			objJSON.put("iNumeroOperariosMujer",OBJSMFICHASGRABADAS.iNumeroOperariosMujer);
			
			objJSON.put("vTelefonoRepresentanteLegal",OBJSMFICHASGRABADAS.vTelefonoRepresentanteLegal);
			objJSON.put("vEmpresaResponsableSanidad",OBJSMFICHASGRABADAS.vEmpresaResponsableSanidad);
			objJSON.put("vEmpresaResponsableMedico",OBJSMFICHASGRABADAS.vEmpresaResponsableMedico);
			objJSON.put("vNumeroCertificadoSanidad",OBJSMFICHASGRABADAS.vNumeroCertificadoSanidad);
			objJSON.put("bCertificadoMedico",OBJSMFICHASGRABADAS.bCertificadoMedico);

			objJSON.put("vNroContrato",OBJSMFICHASGRABADAS.vNroContrato);
			objJSON.put("vNroComiteCompra",OBJSMFICHASGRABADAS.vNroComiteCompra);
			objJSON.put("vItem",OBJSMFICHASGRABADAS.vItem);
			
			objJSON.put("vNSupervisor",OBJSMFICHASGRABADAS.vNSupervisor);
			objJSON.put("vDNISupervisor",OBJSMFICHASGRABADAS.vDNISupervisor);
			objJSON.put("vPostores",OBJSMFICHASGRABADAS.vCodPostor);
			
			List<RECORDCARDITEM> Listar=RECORDCARDITEM_DAO.ListarPreguntas(context, OBJSMFICHASGRABADAS.idFichasGrabadas);
			JSONArray listJSONCausales = new JSONArray();
			JSONArray listJSONRespuestas = new JSONArray();
			JSONArray listJSONObservaciones = new JSONArray();
			
			int incremental=0;
			for(RECORDCARDITEM entidad : Listar)
			{
				incremental++;
				if(entidad.iTipoControl==1 && entidad.bcheck && (entidad.iCodTipoSeccion == 5 || entidad.iCodTipoSeccion == 6)) {
					JSONObject CausalesJSON=new JSONObject();
					CausalesJSON.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
					CausalesJSON.put("iCodTipoCausal",entidad.iCodTipoSeccion);
					CausalesJSON.put("cAlternativa",entidad.iCodBcoPreg);								
					listJSONCausales.put(CausalesJSON);
				}

				if(entidad.iTipoControl==1 && entidad.bcheck && entidad.iCodTipoSeccion > 6 ) {
					JSONObject Respuestas=new JSONObject();
					Respuestas.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
					Respuestas.put("iCodRespuestas",incremental);
					Respuestas.put("iCodBcoPreg",Integer.parseInt(entidad.iCodBcoPreg.trim()));
					//Respuestas.put("vResultado",entidad.vResultado);
					listJSONRespuestas.put(Respuestas);
				}
				
				if(entidad.iIndexCombo>0) {
					JSONObject Respuestas=new JSONObject();
					Respuestas.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
					Respuestas.put("iCodRespuestas",incremental);
					Respuestas.put("iCodBcoPreg",Integer.parseInt(entidad.iCodBcoPreg.trim()));
					listJSONRespuestas.put(Respuestas);
				}

				if(!entidad.vResultado.equals("") &&  !entidad.vResultado.equals(null) && ((entidad.iIndexCombo>0 && entidad.iCodTipoSeccion == 7 ))) {
					JSONObject Observaciones=new JSONObject();
					Observaciones.put("iCodOBSERVACIONES",incremental);
					Observaciones.put("iCodBcoPreg",Integer.parseInt(entidad.iCodBcoPreg.trim()));
					Observaciones.put("iCoDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
					Observaciones.put("vObservacion",entidad.vResultado);
					listJSONObservaciones.put(Observaciones);
				}

				if(entidad.iTipoControl==1 && entidad.bcheck && entidad.iCodTipoSeccion > 7) {
					JSONObject Observaciones=new JSONObject();
					Observaciones.put("iCodOBSERVACIONES",incremental);
					Observaciones.put("iCodBcoPreg",Integer.parseInt(entidad.iCodBcoPreg.trim()));
					Observaciones.put("iCoDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
					Observaciones.put("vObservacion",entidad.vResultado);
					listJSONObservaciones.put(Observaciones);
				}
			}

			//listJSONCausales
			objJSON.put("Causales",listJSONCausales);
			objJSON.put("Observaciones",listJSONObservaciones);	
			objJSON.put("Respuestas",listJSONRespuestas);	
			
			JSONArray listJSONParticipantes = new JSONArray();

			if(OBJSMFICHASGRABADAS.cCategoria.equals("I")) {
				JSONObject Participantes1=new JSONObject();
				Participantes1.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
				Participantes1.put("cDNI",OBJSMFICHASGRABADAS.vDNISupervisor);
				Participantes1.put("vNombres",OBJSMFICHASGRABADAS.vNSupervisor);
				Participantes1.put("iCodCargo",1);
				Participantes1.put("iCodParticipante",1);

				listJSONParticipantes.put(Participantes1);

				JSONObject Participantes2=new JSONObject();
				Participantes2.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
				Participantes2.put("cDNI",OBJSMFICHASGRABADAS.vDNIControlCalidad);
				Participantes2.put("vNombres",OBJSMFICHASGRABADAS.vNControlCalidad);
				Participantes2.put("iCodCargo",5);
				Participantes2.put("iCodParticipante",2);
				listJSONParticipantes.put(Participantes2);

				List<SMFICHAINICIALANEXO> listComites=SMFICHAINICIALANEXO_DAO.Listar(context, OBJSMFICHASGRABADAS.idFichasGrabadas);
				JSONArray listJSONComites = new JSONArray();
				for(int i=0;i<listComites.size();i++) {
					JSONObject entidad= new JSONObject();
					entidad.put("idFichaGrabadas",listComites.get(i).idFichaGrabadas);
					entidad.put("iCodModalidad",listComites.get(i).iCodModalidad);
					entidad.put("iCodComite",listComites.get(i).iCodComite);
					entidad.put("vCodPostor",listComites.get(i).vCodPostor);
					entidad.put("iNumUnidCSolidos",listComites.get(i).iCodModalidad==1? listComites.get(i).iCantSolido:listComites.get(i).iCantAlimento);
					entidad.put("iNumUnidCSolidosPostula",listComites.get(i).iCodModalidad==1? listComites.get(i).iCantSolidoP:listComites.get(i).iCantAlimentoP);
					entidad.put("iNumUnidCBIndus",listComites.get(i).iCantBebible);
					entidad.put("iNumUnidCBIndusPostula",listComites.get(i).iCantBebibleP);
					entidad.put("iNumUnidCSIndus",listComites.get(i).iCantGalleta);
					entidad.put("iNumUnidCSIndusPostula",listComites.get(i).iCantGalletaP);
					entidad.put("iNumUnidCHuev",listComites.get(i).iCantGalleta);
					entidad.put("iNumUnidCHuevPostula",listComites.get(i).iCantGalletaP);

					listJSONComites.put(entidad);
				}

				objJSON.put("Capacidades", listJSONComites);
			}
			else if(OBJSMFICHASGRABADAS.cCategoria.equals("S")) {
				JSONObject Participantes2=new JSONObject();
				Participantes2.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
				//EDITADO: SE CAMBIA COLEGIATURA EN VEZ DE DNI HASTA ACTUALIZAR EL SERVICIO Y QUE LLEVE A SECCION COLEGIATURA
				Participantes2.put("cDNI",OBJSMFICHASGRABADAS.vColegiatura);
				Participantes2.put("vNombres",OBJSMFICHASGRABADAS.vNControlCalidad);
				Participantes2.put("iCodCargo",5);
				Participantes2.put("iCodParticipante",1);
				listJSONParticipantes.put(Participantes2);
        	}
			else if(OBJSMFICHASGRABADAS.cCategoria.equals("T")) {
				JSONObject Participantes2=new JSONObject();
				Participantes2.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
				Participantes2.put("cDNI",OBJSMFICHASGRABADAS.vDNIPNAEQW);
				Participantes2.put("vNombres",OBJSMFICHASGRABADAS.vNPNAEQW);
				Participantes2.put("iCodCargo",6);
				Participantes2.put("iCodParticipante",1);
				listJSONParticipantes.put(Participantes2);
        	}
			else if(OBJSMFICHASGRABADAS.cCategoria.equals("O")) {
				JSONObject Participantes2=new JSONObject();
				Participantes2.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
				Participantes2.put("cDNI",OBJSMFICHASGRABADAS.vDNIControlCalidad);
				Participantes2.put("vNombres",OBJSMFICHASGRABADAS.vNControlCalidad);
				Participantes2.put("iCodCargo",5);
				Participantes2.put("iCodParticipante",1);
				listJSONParticipantes.put(Participantes2);
        	}

			objJSON.put("Participantes",listJSONParticipantes);


			//nueva tabla

			JSONArray listJSONVehiculos=new JSONArray();

			List<SMFICHASGBOTROS> SM= SMFICHASGOTROS_DAO.SeleccionarFichas(context, OBJSMFICHASGRABADAS.iCodFicha
					, OBJSMFICHASGRABADAS.idFichasGrabadas);
			for(SMFICHASGBOTROS entidad : SM)
			{
				JSONObject Vehiculos = new JSONObject();
				Vehiculos.put("idSMFICHAS", entidad.idSMFICHAS);
				Vehiculos.put("cCodEstablecimiento", entidad.cCodEstablecimiento);
				Vehiculos.put("cCodProveedor", entidad.cCodProveedor);
				Vehiculos.put("iCodFicha", entidad.iCodFicha);
				Vehiculos.put("iNumDiasPlazo",entidad.iNumDiasPlazo);
				Vehiculos.put("lFechaPlazo", formatted.format(new Date(entidad.lFechaPlazo)));
				Vehiculos.put("vNSupervisor", entidad.vNSupervisor);
				Vehiculos.put("vDNISupervisor", entidad.vDNISupervisor);
				Vehiculos.put("iTipoEstablecimiento", entidad.iTipoEstablecimiento);
				Vehiculos.put("iNumConforme", entidad.iNumConforme);
				Vehiculos.put("iNumNoConforme", entidad.iNumNoConforme);
				Vehiculos.put("iNumTotalVh", entidad.iNumTotalVh);
				Vehiculos.put("iNumTotalVhRv", entidad.iNumTotalVhRv);
				listJSONVehiculos.put(Vehiculos);
			}
			objJSON.put("DetalleFichaTransporte",listJSONVehiculos);

			//PuntosCriticos
			JSONArray listJSONPuntosCriticos = new JSONArray();
			List<SMPUNTOCRITICO> listaPC=SMPUNTOCRITICO_DAO.Listar(context, OBJSMFICHASGRABADAS.idFichasGrabadas);
			for(int i=0;i<listaPC.size();i++) {
				JSONObject PuntosCriticos=new JSONObject();
				PuntosCriticos.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
				PuntosCriticos.put("iCodPuntoC",(i+1));
				PuntosCriticos.put("vDesPCControl",listaPC.get(i).vPuntoCriticoControl);
				PuntosCriticos.put("vDesLimCritico",listaPC.get(i).vLimiteCritico);
				PuntosCriticos.put("vDesFrecRevision",listaPC.get(i).vFrecuenRevision);
				PuntosCriticos.put("vDesCalFrecuencia",listaPC.get(i).vCalibracionFrecuencia);
				PuntosCriticos.put("iOrden",(i+1));
					
				listJSONPuntosCriticos.put(PuntosCriticos);
			}
			objJSON.put("PuntosCriticos",listJSONPuntosCriticos);	
			
			//Fotos
			JSONArray listJSONFotos = new JSONArray();

				
				List<SMDETFOTOS> listFotos=SMDETFOTOS_DAO.ListarTodo(context, OBJSMFICHASGRABADAS.idFichasGrabadas);
				
				if(listFotos.size()>0)	
				{
					for(int i=0;i<listFotos.size();i++)
					{
						JSONObject Fotos=new JSONObject();	
						Fotos.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
						Fotos.put("iCodDetFoto",(i+1));
						Fotos.put("vNombreFoto",listFotos.get(i).vNombreFoto+".jpg");	
						Fotos.put("vLatitud",""+listFotos.get(i).vLatitud);	
						Fotos.put("vLongitud",""+listFotos.get(i).vLongitud);
						listJSONFotos.put(Fotos);
					}
				}
				else
				{
					JSONObject Fotos=new JSONObject();	
					Fotos.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
					Fotos.put("iCodDetFoto",1);
					Fotos.put("vNombreFoto","none.jpg");	
					Fotos.put("vLatitud","0");	
					Fotos.put("vLongitud","0");
					listJSONFotos.put(Fotos);
				}
				
			objJSON.put("Fotos",listJSONFotos);	
			
			//Almacenes
			JSONArray listJSONAlmacenes = new JSONArray();
					
			List<SMALMACEN> listaAlamcen =SMALMACEN_DAO.Listar(context, OBJSMFICHASGRABADAS.idFichasGrabadas);
			for(int i=0;i<listaAlamcen.size();i++) {
				JSONObject Almacenes=new JSONObject();
				Almacenes.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
				Almacenes.put("iCodFichaEquipo",(i+1));
				Almacenes.put("iTipoEqAl",3);
				Almacenes.put("vNombre",listaAlamcen.get(i).vAlmacen);
				Almacenes.put("vDetalle",listaAlamcen.get(i).vArea);
				listJSONAlmacenes.put(Almacenes);
			}

			List<SMEQUIPOS> listaEquipos=SMEQUIPOS_DAO.Listar(context, OBJSMFICHASGRABADAS.idFichasGrabadas);
			for(int i=0;i<listaEquipos.size();i++)
			{
				JSONObject Almacenes=new JSONObject();
				Almacenes.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
				Almacenes.put("iCodFichaEquipo",(i+1));
				Almacenes.put("iTipoEqAl",2);
				Almacenes.put("vNombre",listaEquipos.get(i).vEquipo);
				Almacenes.put("vDetalle",listaEquipos.get(i).vMarca);
				Almacenes.put("vNSerie",listaEquipos.get(i).vNSerie);
				listJSONAlmacenes.put(Almacenes);
			}
			objJSON.put("Almacenes",listJSONAlmacenes);

			//Almacenes
			if(OBJSMFICHASGRABADAS.cCategoria.equals("I")) {
				if(OBJSMFICHASGRABADAS.iCodFicha==1) {
					JSONArray listJSONCapacidades = new JSONArray();
					JSONObject Capacidades=new JSONObject();
					Capacidades.put("iCodEstCap",1);
					Capacidades.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
					Capacidades.put("cModalidad","P");
					Capacidades.put("vComponente","0");
					Capacidades.put("iNumUnidCSolidos",0);
					Capacidades.put("iNumUnidBIndus",0);
					Capacidades.put("iNumUnidRaciones",0);
					Capacidades.put("dTotEstimadokilos",Double.parseDouble(OBJSMFICHASGRABADAS.dOpcion1));
					Capacidades.put("dTotEstimadoLitros",OBJSMFICHASGRABADAS.dOpcion2);
					Capacidades.put("dTotAlimentosKilos",OBJSMFICHASGRABADAS.dOpcion3);
					Capacidades.put("dTotAlimentosLitros",OBJSMFICHASGRABADAS.dOpcion4);
					listJSONCapacidades.put(Capacidades);
					objJSON.put("Capacidades",listJSONCapacidades);
				}
				else if(OBJSMFICHASGRABADAS.iCodFicha==2) {
					JSONArray listJSONCapacidades = new JSONArray();
					JSONObject Capacidades=new JSONObject();
					Capacidades.put("iCodEstCap",1);
					Capacidades.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
					Capacidades.put("cModalidad","A");
					Capacidades.put("vComponente",""+OBJSMFICHASGRABADAS.dOpcion1);
					Capacidades.put("iNumUnidCSolidos",OBJSMFICHASGRABADAS.dOpcion2);
					Capacidades.put("iNumUnidBIndus",OBJSMFICHASGRABADAS.dOpcion3);
					Capacidades.put("iNumUnidRaciones",OBJSMFICHASGRABADAS.dOpcion4);
					Capacidades.put("dTotEstimadokilos",0);
					Capacidades.put("dTotEstimadoLitros",0);
					Capacidades.put("dTotAlimentosKilos",0);
					Capacidades.put("dTotAlimentosLitros",0);
					listJSONCapacidades.put(Capacidades);
					objJSON.put("Capacidades",listJSONCapacidades);
				}
			}
			Data=objJSON.toString();
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		return Data;
	}

	public static String  SendDataSeguimientoRaciones(Context context,SMFICHASGRABADAS OBJSMFICHASGRABADAS)	{
		String Data="";

		try {
			JSONObject objJSON=new JSONObject();
			objJSON.put("iCodFicha",OBJSMFICHASGRABADAS.iCodFicha);
			objJSON.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
			objJSON.put("vCodProveedor",OBJSMFICHASGRABADAS.cCodProveedor);
			objJSON.put("vCodEstablecimiento",OBJSMFICHASGRABADAS.cCodEstablecimiento);
			
			SimpleDateFormat formatted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
			
			objJSON.put("sdtFechaSupervicion",formatted.format(new Date(OBJSMFICHASGRABADAS.lFecha)));
			objJSON.put("sdtFecTermino",formatted.format(new Date(OBJSMFICHASGRABADAS.lFechaFin)));
			
			
			objJSON.put("vFechaVigenciaSanidad",formatted.format(new Date(OBJSMFICHASGRABADAS.vFechaVigenciaSanidad)));
			objJSON.put("vFechaVigenciaCertificadoMedico",formatted.format(new Date(OBJSMFICHASGRABADAS.vFechaVigenciaCertificadoMedico)));

			
			objJSON.put("vEspecialidad",OBJSMFICHASGRABADAS.vEspecialidad);
			objJSON.put("vColegiatura",OBJSMFICHASGRABADAS.vColegiatura);
			
			
			objJSON.put("vTipoVehiculo",OBJSMFICHASGRABADAS.vTipoVehiculo);			
			objJSON.put("vPlaca",OBJSMFICHASGRABADAS.vPlaca);
			objJSON.put("vGuiaRemision",OBJSMFICHASGRABADAS.vGuiaRemision);
			
			
			objJSON.put("dPorcentaje",OBJSMFICHASGRABADAS.dPuntajeFicha);
			objJSON.put("iPuntaje",RECORDCARDITEM_DAO.GetPuntos(context, OBJSMFICHASGRABADAS.idFichasGrabadas));
			///objJSON.put("vObservacion",OBJSMFICHASGRABADAS.vDatosRelevantes);
			objJSON.put("iTotalOperarios",OBJSMFICHASGRABADAS.iTotalOperarios);
			//objJSON.put("iCodUsuario",Integer.parseInt(SESION_DAO.BuscarUt(context)));//falta
			objJSON.put("vComite",SESION_DAO.BuscarUt(context));
			
			
			objJSON.put("iCantidadRacionesAdjudicadas",OBJSMFICHASGRABADAS.iCantidadRacionesAdjudicadas);
			objJSON.put("iCantidadRacionesVerificadas",OBJSMFICHASGRABADAS.iCantidadRacionesVerificadas);
			objJSON.put("vNombreRacionesVerificadas",OBJSMFICHASGRABADAS.vNombreRacionesVerificadas);
			objJSON.put("vTurno",OBJSMFICHASGRABADAS.vTurno);
			objJSON.put("iNumeroOperariosHombre",OBJSMFICHASGRABADAS.iNumeroOperariosHombre);
			objJSON.put("iNumeroOperariosMujer",OBJSMFICHASGRABADAS.iNumeroOperariosMujer);
			
			objJSON.put("vTelefonoRepresentanteLegal",OBJSMFICHASGRABADAS.vTelefonoRepresentanteLegal);
			objJSON.put("vEmpresaResponsableSanidad",OBJSMFICHASGRABADAS.vEmpresaResponsableSanidad);
			objJSON.put("vEmpresaResponsableMedico",OBJSMFICHASGRABADAS.vEmpresaResponsableMedico);
			objJSON.put("vNumeroCertificadoSanidad",OBJSMFICHASGRABADAS.vNumeroCertificadoSanidad);
			objJSON.put("bCertificadoMedico",OBJSMFICHASGRABADAS.bCertificadoMedico);
		
			
			
			objJSON.put("vNroContrato",OBJSMFICHASGRABADAS.vNroContrato);
			objJSON.put("vNroComiteCompra",OBJSMFICHASGRABADAS.vComite);//vNroComiteCompra);
			objJSON.put("vItem",OBJSMFICHASGRABADAS.vItem);
			objJSON.put("vComite",OBJSMFICHASGRABADAS.vComite);
			objJSON.put("vNombreRacionesVerificadas", OBJSMFICHASGRABADAS.vNombreRacion);
			objJSON.put("vNombreEstablecimiento", OBJSMFICHASGRABADAS.vNombreEstablecimiento);
			objJSON.put("iCantidadRacionesVerificadas", OBJSMFICHASGRABADAS.vCantidadSupervisada);
			objJSON.put("iCantidadRacionesProgramadas", OBJSMFICHASGRABADAS.vCantidadProgramada);
			
			
			objJSON.put("vNombreSupervisor", OBJSMFICHASGRABADAS.vNSupervisor);
			objJSON.put("vDNISupervisor", OBJSMFICHASGRABADAS.vDNISupervisor);
			objJSON.put("vObservacion", OBJSMFICHASGRABADAS.vObservacion);
			
			List<RECORDCARDITEM> Listar=RECORDCARDITEM_DAO.ListarPreguntasRaciones(context, OBJSMFICHASGRABADAS.idFichasGrabadas);
			JSONArray listJSONCausales = new JSONArray();
			JSONArray listJSONRespuestas = new JSONArray();
			JSONArray listJSONObservaciones = new JSONArray();
			JSONArray listJSONSeguimiento=new JSONArray();
			
			int incremental=0;
			for(RECORDCARDITEM entidad : Listar)
			{
				incremental++;
				/*if(entidad.iTipoControl==1 && entidad.bcheck && entidad.iOrden < 20 )
				{
					JSONObject CausalesJSON=new JSONObject();	
					CausalesJSON.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
					CausalesJSON.put("iCodTipoCausal",entidad.iCodTipoSeccion);
					CausalesJSON.put("cAlternativa",entidad.iCodBcoPreg);								
					listJSONCausales.put(CausalesJSON);
				}
				
				if(entidad.iTipoControl==1 && entidad.bcheck && entidad.iOrden > 20 )
				{
					
					
					
					JSONObject Respuestas=new JSONObject();	
					Respuestas.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
					Respuestas.put("iCodRespuestas",incremental);
					Respuestas.put("iCodBcoPreg",Integer.parseInt(entidad.iCodBcoPreg.trim()));
					listJSONRespuestas.put(Respuestas);
				}
				
				if(entidad.iIndexCombo==1)
					{
						JSONObject Respuestas=new JSONObject();	
						Respuestas.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
						Respuestas.put("iCodRespuestas",incremental);
						Respuestas.put("iCodBcoPreg",Integer.parseInt(entidad.iCodBcoPreg.trim()));
						listJSONRespuestas.put(Respuestas);
					}
					
					
					if(!entidad.vResultado.equals("") &&  !entidad.vResultado.equals(null) && (entidad.iIndexCombo<3 || entidad.iTipoControl==6 ))
					{
						JSONObject Observaciones=new JSONObject();	
						Observaciones.put("iCodOBSERVACIONES",incremental);
						Observaciones.put("iCodBcoPreg",Integer.parseInt(entidad.iCodBcoPreg.trim()));
						Observaciones.put("iCoDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);	
						Observaciones.put("vObservacion",entidad.vResultado);	
						listJSONObservaciones.put(Observaciones);
					}
					
					if(entidad.iIndexCombo==3)
					{
						JSONObject Observaciones=new JSONObject();	
						Observaciones.put("iCodOBSERVACIONES",incremental);
						Observaciones.put("iCodBcoPreg",Integer.parseInt(entidad.iCodBcoPreg.trim()));
						Observaciones.put("iCoDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);	
						Observaciones.put("vObservacion","No Aplica "+entidad.vResultado);	
						listJSONObservaciones.put(Observaciones);
					}*/
				
				if((entidad.iCodTipoSeccion==5 ||entidad.iCodTipoSeccion==6)&&entidad.bcheck){
					
					JSONObject CausalesJSON=new JSONObject();	
					CausalesJSON.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
					CausalesJSON.put("iCodTipoCausal",entidad.iCodTipoSeccion);
					CausalesJSON.put("cAlternativa",entidad.iCodBcoPreg);								
					listJSONCausales.put(CausalesJSON);
				}
				
				if(entidad.iCodTipoSeccion>=8 &&entidad.iCodTipoSeccion<=12){
					JSONObject Observaciones=new JSONObject();	
					Observaciones.put("iCodRecordCardItem",entidad.iRecordCardItem);
					Observaciones.put("iCodRecordCardItemPadre",entidad.iRecordCardItemPadre);
					Observaciones.put("iCodFormato",entidad.iCodFormato);	
					Observaciones.put("iValor",entidad.iValor);	
					Observaciones.put("vDescripcion",entidad.vResultado);
					Observaciones.put("vObservacion",entidad.vObservacion);
					Observaciones.put("bNoAplica",entidad.bcheck);
					listJSONSeguimiento.put(Observaciones);
					
				}
			}
			//listJSONCausales
			objJSON.put("Causales",listJSONCausales);
			objJSON.put("Observaciones",listJSONObservaciones);	
			objJSON.put("Respuestas",listJSONRespuestas);
			objJSON.put("DetalleSeguimiento",listJSONSeguimiento);
			
			JSONArray listJSONParticipantes = new JSONArray();
			if(OBJSMFICHASGRABADAS.cCategoria.equals("S"))
        	{					
					JSONObject Participantes2=new JSONObject();	
					Participantes2.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
					Participantes2.put("cDNI",OBJSMFICHASGRABADAS.vDNIControlCalidad);
					Participantes2.put("vNombres",OBJSMFICHASGRABADAS.vNControlCalidad);	
					Participantes2.put("iCodCargo",5);	
					Participantes2.put("iCodParticipante",1);	
					listJSONParticipantes.put(Participantes2);
        	}
			else if(OBJSMFICHASGRABADAS.cCategoria.equals("T"))
        	{					
					JSONObject Participantes2=new JSONObject();	
					Participantes2.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
					Participantes2.put("cDNI",OBJSMFICHASGRABADAS.vDNIPNAEQW);
					Participantes2.put("vNombres",OBJSMFICHASGRABADAS.vNPNAEQW);	
					Participantes2.put("iCodCargo",6);	
					Participantes2.put("iCodParticipante",1);	
					listJSONParticipantes.put(Participantes2);
        	}
			else if(OBJSMFICHASGRABADAS.cCategoria.equals("O"))
        	{					
					JSONObject Participantes2=new JSONObject();	
					Participantes2.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
					Participantes2.put("cDNI",OBJSMFICHASGRABADAS.vDNIControlCalidad);
					Participantes2.put("vNombres",OBJSMFICHASGRABADAS.vNControlCalidad);	
					Participantes2.put("iCodCargo",5);	
					Participantes2.put("iCodParticipante",1);	
					listJSONParticipantes.put(Participantes2);
        	}
			objJSON.put("Participantes",listJSONParticipantes);					
			
			//PuntosCriticos
			JSONArray listJSONPuntosCriticos = new JSONArray();
			List<SMPUNTOCRITICO> listaPC=SMPUNTOCRITICO_DAO.Listar(context, OBJSMFICHASGRABADAS.idFichasGrabadas);
			for(int i=0;i<listaPC.size();i++)
			{	
			
				      JSONObject PuntosCriticos=new JSONObject();	
							PuntosCriticos.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
							PuntosCriticos.put("iCodPuntoC",(i+1));
							PuntosCriticos.put("vDesPCControl",listaPC.get(i).vPuntoCriticoControl);	
							PuntosCriticos.put("vDesLimCritico",listaPC.get(i).vLimiteCritico);	
							PuntosCriticos.put("vDesFrecRevision",listaPC.get(i).vFrecuenRevision);	
							PuntosCriticos.put("vDesCalFrecuencia",listaPC.get(i).vCalibracionFrecuencia);	
							PuntosCriticos.put("iOrden",(i+1));									
					
						listJSONPuntosCriticos.put(PuntosCriticos);
			}
			objJSON.put("PuntosCriticos",listJSONPuntosCriticos);	
			
			//Fotos
			JSONArray listJSONFotos = new JSONArray();

				
				List<SMDETFOTOS> listFotos=SMDETFOTOS_DAO.ListarTodo(context, OBJSMFICHASGRABADAS.idFichasGrabadas);
				
				if(listFotos.size()>0)	
				{
					for(int i=0;i<listFotos.size();i++)
					{
						JSONObject Fotos=new JSONObject();	
						Fotos.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
						Fotos.put("iCodDetFoto",(i+1));
						Fotos.put("vNombreFoto",listFotos.get(i).vNombreFoto+".jpg");	
						Fotos.put("vLatitud",""+listFotos.get(i).vLatitud);	
						Fotos.put("vLongitud",""+listFotos.get(i).vLongitud);
						listJSONFotos.put(Fotos);
					}
				}
				else
				{
					JSONObject Fotos=new JSONObject();	
					Fotos.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
					Fotos.put("iCodDetFoto",1);
					Fotos.put("vNombreFoto","none.jpg");	
					Fotos.put("vLatitud","0");	
					Fotos.put("vLongitud","0");
					listJSONFotos.put(Fotos);
				}
				
			objJSON.put("Fotos",listJSONFotos);	
			
			//Almacenes
					JSONArray listJSONAlmacenes = new JSONArray();
					
					List<SMALMACEN> listaAlamcen =SMALMACEN_DAO.Listar(context, OBJSMFICHASGRABADAS.idFichasGrabadas);
					for(int i=0;i<listaAlamcen.size();i++)
					{
						JSONObject Almacenes=new JSONObject();	
						Almacenes.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
						Almacenes.put("iCodFichaEquipo",(i+1));
						Almacenes.put("iTipoEqAl",3);	
						Almacenes.put("vNombre",listaAlamcen.get(i).vAlmacen);	
						Almacenes.put("vDetalle",listaAlamcen.get(i).vArea);	
						listJSONAlmacenes.put(Almacenes);
					}
					
					List<SMEQUIPOS> listaEquipos=SMEQUIPOS_DAO.Listar(context, OBJSMFICHASGRABADAS.idFichasGrabadas);
					for(int i=0;i<listaEquipos.size();i++)
					{
						JSONObject Almacenes=new JSONObject();	
						Almacenes.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
						Almacenes.put("iCodFichaEquipo",(i+1));
						Almacenes.put("iTipoEqAl",2);	
						Almacenes.put("vNombre",listaEquipos.get(i).vEquipo);	
						Almacenes.put("vDetalle",listaEquipos.get(i).vMarca);	
						listJSONAlmacenes.put(Almacenes);
					}
					objJSON.put("Almacenes",listJSONAlmacenes);
		

		
		Data=objJSON.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return Data;
       
	}





	public static String  SendDataTransporte(Context context,SMFICHASGRABADAS OBJSMFICHASGRABADAS)
	{
		String Data="";

		try
		{
			JSONObject objJSON=new JSONObject();
			objJSON.put("iCodFicha",OBJSMFICHASGRABADAS.iCodFicha);
			objJSON.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
			objJSON.put("vCodProveedor",OBJSMFICHASGRABADAS.cCodProveedor);
			objJSON.put("vCodEstablecimiento",OBJSMFICHASGRABADAS.cCodEstablecimiento);

			SimpleDateFormat formatted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

			objJSON.put("sdtFechaSupervicion",formatted.format(new Date(OBJSMFICHASGRABADAS.lFecha)));
			objJSON.put("sdtFecTermino",formatted.format(new Date(OBJSMFICHASGRABADAS.lFechaFin)));


			objJSON.put("vFechaVigenciaSanidad",formatted.format(new Date(OBJSMFICHASGRABADAS.vFechaVigenciaSanidad)));
			objJSON.put("vFechaVigenciaCertificadoMedico",formatted.format(new Date(OBJSMFICHASGRABADAS.vFechaVigenciaCertificadoMedico)));


			objJSON.put("vEspecialidad",OBJSMFICHASGRABADAS.vEspecialidad);
			objJSON.put("vColegiatura",OBJSMFICHASGRABADAS.vColegiatura);


			objJSON.put("vTipoVehiculo",OBJSMFICHASGRABADAS.vTipoVehiculo);
			objJSON.put("vPlaca",OBJSMFICHASGRABADAS.vPlaca);
			objJSON.put("vGuiaRemision",OBJSMFICHASGRABADAS.vGuiaRemision);


			objJSON.put("dPorcentaje",OBJSMFICHASGRABADAS.dPuntajeFicha);
			objJSON.put("iPuntaje",RECORDCARDITEM_DAO.GetPuntos(context, OBJSMFICHASGRABADAS.idFichasGrabadas));
			///objJSON.put("vObservacion",OBJSMFICHASGRABADAS.vDatosRelevantes);
			objJSON.put("iTotalOperarios",OBJSMFICHASGRABADAS.iTotalOperarios);
			//objJSON.put("iCodUsuario",Integer.parseInt(SESION_DAO.BuscarUt(context)));//falta
			objJSON.put("vComite",SESION_DAO.BuscarUt(context));


			objJSON.put("iCantidadRacionesAdjudicadas",OBJSMFICHASGRABADAS.iCantidadRacionesAdjudicadas);
			objJSON.put("iCantidadRacionesVerificadas",OBJSMFICHASGRABADAS.iCantidadRacionesVerificadas);
			objJSON.put("vNombreRacionesVerificadas",OBJSMFICHASGRABADAS.vNombreRacionesVerificadas);
			objJSON.put("vTurno",OBJSMFICHASGRABADAS.vTurno);
			objJSON.put("iNumeroOperariosHombre",OBJSMFICHASGRABADAS.iNumeroOperariosHombre);
			objJSON.put("iNumeroOperariosMujer",OBJSMFICHASGRABADAS.iNumeroOperariosMujer);

			objJSON.put("vTelefonoRepresentanteLegal",OBJSMFICHASGRABADAS.vTelefonoRepresentanteLegal);
			objJSON.put("vEmpresaResponsableSanidad",OBJSMFICHASGRABADAS.vEmpresaResponsableSanidad);
			objJSON.put("vEmpresaResponsableMedico",OBJSMFICHASGRABADAS.vEmpresaResponsableMedico);
			objJSON.put("vNumeroCertificadoSanidad",OBJSMFICHASGRABADAS.vNumeroCertificadoSanidad);
			objJSON.put("bCertificadoMedico",OBJSMFICHASGRABADAS.bCertificadoMedico);



			objJSON.put("vNroContrato",OBJSMFICHASGRABADAS.vNroContrato);
			objJSON.put("vNroComiteCompra",OBJSMFICHASGRABADAS.vComite);//vNroComiteCompra);
			objJSON.put("vItem",OBJSMFICHASGRABADAS.vItem);
			objJSON.put("vComite",OBJSMFICHASGRABADAS.vComite);
			objJSON.put("vNombreRacionesVerificadas", OBJSMFICHASGRABADAS.vNombreRacion);
			objJSON.put("vNombreEstablecimiento", OBJSMFICHASGRABADAS.vNombreEstablecimiento);
			objJSON.put("iCantidadRacionesVerificadas", OBJSMFICHASGRABADAS.vCantidadSupervisada);
			objJSON.put("iCantidadRacionesProgramadas", OBJSMFICHASGRABADAS.vCantidadProgramada);


			objJSON.put("vNombreSupervisor", OBJSMFICHASGRABADAS.vNSupervisor);
			objJSON.put("vDNISupervisor", OBJSMFICHASGRABADAS.vDNISupervisor);
			objJSON.put("vObservacion", OBJSMFICHASGRABADAS.vObservacion);

			List<RECORDCARDITEM> Listar=RECORDCARDITEM_DAO.ListarRecordCardTransporte(context, OBJSMFICHASGRABADAS.idFichasGrabadas);
			JSONArray listJSONCausales = new JSONArray();
			JSONArray listJSONRespuestas = new JSONArray();
			JSONArray listJSONObservaciones = new JSONArray();
			JSONArray listJSONSeguimiento=new JSONArray();

			int incremental=0;
			for(RECORDCARDITEM entidad : Listar)
			{
				incremental++;

				if((entidad.iCodTipoSeccion==5 ||entidad.iCodTipoSeccion==6)&&entidad.bcheck){

					JSONObject CausalesJSON=new JSONObject();
					CausalesJSON.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
					CausalesJSON.put("iCodTipoCausal",entidad.iCodTipoSeccion);
					CausalesJSON.put("cAlternativa",entidad.iCodBcoPreg);
					listJSONCausales.put(CausalesJSON);
				}

				if(entidad.iOrden>-1 && entidad.iOrden<24){
					JSONObject Observaciones=new JSONObject();
					Observaciones.put("iCodRecordCardItem",entidad.iRecordCardItem);
					Observaciones.put("iCodRecordCardItemPadre",entidad.iRecordCardItemPadre);
					Observaciones.put("iCodFormato",entidad.iCodFormato);
					Observaciones.put("iValor",entidad.iValor);
					Observaciones.put("vDescripcion",entidad.vResultado);
					Observaciones.put("vObservacion",entidad.vObservacion);
					if(entidad.bcheck==true){Observaciones.put("bAplica",1);}
					else{Observaciones.put("bNoAplica",0);	}

					Observaciones.put("bNoAplica",entidad.bcheck);
					listJSONSeguimiento.put(Observaciones);
				}
			}
			//listJSONCausales
			objJSON.put("Causales",listJSONCausales);
			objJSON.put("Observaciones",listJSONObservaciones);
			objJSON.put("Respuestas",listJSONRespuestas);
			objJSON.put("DetalleSeguimiento",listJSONSeguimiento);

			JSONArray listJSONParticipantes = new JSONArray();
			if(OBJSMFICHASGRABADAS.cCategoria.equals("S"))
			{
				JSONObject Participantes2=new JSONObject();
				Participantes2.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
				Participantes2.put("cDNI",OBJSMFICHASGRABADAS.vDNIControlCalidad);
				Participantes2.put("vNombres",OBJSMFICHASGRABADAS.vNControlCalidad);
				Participantes2.put("iCodCargo",5);
				Participantes2.put("iCodParticipante",1);
				listJSONParticipantes.put(Participantes2);
			}
			else if(OBJSMFICHASGRABADAS.cCategoria.equals("X"))
			{
				JSONObject Participantes2=new JSONObject();
				Participantes2.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
				Participantes2.put("cDNI",OBJSMFICHASGRABADAS.vDNIPNAEQW);
				Participantes2.put("vNombres",OBJSMFICHASGRABADAS.vNPNAEQW);
				Participantes2.put("iCodCargo",6);
				Participantes2.put("iCodParticipante",1);
				listJSONParticipantes.put(Participantes2);
			}
			else if(OBJSMFICHASGRABADAS.cCategoria.equals("O"))
			{
				JSONObject Participantes2=new JSONObject();
				Participantes2.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
				Participantes2.put("cDNI",OBJSMFICHASGRABADAS.vDNIControlCalidad);
				Participantes2.put("vNombres",OBJSMFICHASGRABADAS.vNControlCalidad);
				Participantes2.put("iCodCargo",5);
				Participantes2.put("iCodParticipante",1);
				listJSONParticipantes.put(Participantes2);
			}
			objJSON.put("Participantes",listJSONParticipantes);

			//PuntosCriticos
			JSONArray listJSONPuntosCriticos = new JSONArray();
			List<SMPUNTOCRITICO> listaPC=SMPUNTOCRITICO_DAO.Listar(context, OBJSMFICHASGRABADAS.idFichasGrabadas);
			for(int i=0;i<listaPC.size();i++)
			{

				JSONObject PuntosCriticos=new JSONObject();
				PuntosCriticos.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
				PuntosCriticos.put("iCodPuntoC",(i+1));
				PuntosCriticos.put("vDesPCControl",listaPC.get(i).vPuntoCriticoControl);
				PuntosCriticos.put("vDesLimCritico",listaPC.get(i).vLimiteCritico);
				PuntosCriticos.put("vDesFrecRevision",listaPC.get(i).vFrecuenRevision);
				PuntosCriticos.put("vDesCalFrecuencia",listaPC.get(i).vCalibracionFrecuencia);
				PuntosCriticos.put("iOrden",(i+1));

				listJSONPuntosCriticos.put(PuntosCriticos);
			}
			objJSON.put("PuntosCriticos",listJSONPuntosCriticos);

			//nueva tabla

			JSONArray listJSONVehiculos=new JSONArray();

			List<SMFICHASGBOTROS> SM= SMFICHASGOTROS_DAO.SeleccionarFichas(context, OBJSMFICHASGRABADAS.iCodFicha
					, OBJSMFICHASGRABADAS.idFichasGrabadas);
			for(SMFICHASGBOTROS entidad : SM)
			{
				JSONObject Vehiculos = new JSONObject();
				Vehiculos.put("idSMFICHAS", entidad.idSMFICHAS);
				Vehiculos.put("cCodEstablecimiento", entidad.cCodEstablecimiento);
				Vehiculos.put("cCodProveedor", entidad.cCodProveedor);
				Vehiculos.put("iCodFicha", entidad.iCodFicha);
				Vehiculos.put("iNumDiasPlazo",entidad.iNumDiasPlazo);
				Vehiculos.put("lFechaPlazo", formatted.format(new Date(entidad.lFechaPlazo)));
				Vehiculos.put("vNSupervisor", entidad.vNSupervisor);
				Vehiculos.put("vDNISupervisor", entidad.vDNISupervisor);
				Vehiculos.put("iTipoEstablecimiento", entidad.iTipoEstablecimiento);
				Vehiculos.put("iNumConforme", entidad.iNumConforme);
				Vehiculos.put("iNumNoConforme", entidad.iNumNoConforme);
				Vehiculos.put("iNumTotalVh", entidad.iNumTotalVh);
				Vehiculos.put("iNumTotalVhRv", entidad.iNumTotalVhRv);
				listJSONVehiculos.put(Vehiculos);
			}
			objJSON.put("DetalleFichaTransporte",listJSONVehiculos);
//items
			JSONArray listJSONItems=new JSONArray();
			List<SMITEMSTRANSPORTE> SA= SMITEMSTRANSPORTE_DAO.SeleccionarFichas(context, OBJSMFICHASGRABADAS.iCodFicha
					, OBJSMFICHASGRABADAS.idFichasGrabadas);

			for(SMITEMSTRANSPORTE entidad:SA)
			{
				JSONObject Items=new JSONObject();
				Items.put("idSMFICHAS",entidad.idSMFICHAS);
				Items.put("iRecordCardPadre",entidad.iRecordCardPadre);
				Items.put("vCodItem",entidad.vCodItem);
				Items.put("vNItem", entidad.vNItem);
				listJSONItems.put(Items);
			}
			objJSON.put("DetalleItems",listJSONItems);


			//Fotos
			JSONArray listJSONFotos = new JSONArray();
			List<SMDETFOTOS> listFotos=SMDETFOTOS_DAO.ListarTodo(context, OBJSMFICHASGRABADAS.idFichasGrabadas);

			if(listFotos.size()>0)
			{
				for(int i=0;i<listFotos.size();i++)
				{
					JSONObject Fotos=new JSONObject();
					Fotos.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
					Fotos.put("iCodDetFoto",(i+1));
					Fotos.put("vNombreFoto",listFotos.get(i).vNombreFoto+".jpg");
					Fotos.put("vLatitud",""+listFotos.get(i).vLatitud);
					Fotos.put("vLongitud",""+listFotos.get(i).vLongitud);
					listJSONFotos.put(Fotos);
				}
			}
			else
			{
				JSONObject Fotos=new JSONObject();
				Fotos.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
				Fotos.put("iCodDetFoto",1);
				Fotos.put("vNombreFoto","none.jpg");
				Fotos.put("vLatitud","0");
				Fotos.put("vLongitud","0");
				listJSONFotos.put(Fotos);
			}

			objJSON.put("Fotos",listJSONFotos);

			//Almacenes
			JSONArray listJSONAlmacenes = new JSONArray();

			List<SMALMACEN> listaAlamcen =SMALMACEN_DAO.Listar(context, OBJSMFICHASGRABADAS.idFichasGrabadas);
			for(int i=0;i<listaAlamcen.size();i++)
			{
				JSONObject Almacenes=new JSONObject();
				Almacenes.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
				Almacenes.put("iCodFichaEquipo",(i+1));
				Almacenes.put("iTipoEqAl",3);
				Almacenes.put("vNombre",listaAlamcen.get(i).vAlmacen);
				Almacenes.put("vDetalle",listaAlamcen.get(i).vArea);
				listJSONAlmacenes.put(Almacenes);
			}

			List<SMEQUIPOS> listaEquipos=SMEQUIPOS_DAO.Listar(context, OBJSMFICHASGRABADAS.idFichasGrabadas);
			for(int i=0;i<listaEquipos.size();i++)
			{
				JSONObject Almacenes=new JSONObject();
				Almacenes.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
				Almacenes.put("iCodFichaEquipo",(i+1));
				Almacenes.put("iTipoEqAl",2);
				Almacenes.put("vNombre",listaEquipos.get(i).vEquipo);
				Almacenes.put("vDetalle",listaEquipos.get(i).vMarca);
				listJSONAlmacenes.put(Almacenes);
			}
			objJSON.put("Almacenes",listJSONAlmacenes);



			Data=objJSON.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return Data;

	}


	public static String  SendDataLiberacionRaciones(Context context,SMFICHASGRABADAS OBJSMFICHASGRABADAS)
	{
		 String Data="";
		 
		try 
		{
			JSONObject objJSON=new JSONObject();        
			objJSON.put("iCodFicha",OBJSMFICHASGRABADAS.iCodFicha);
			objJSON.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
			objJSON.put("vCodProveedor",OBJSMFICHASGRABADAS.cCodProveedor);
			objJSON.put("vCodEstablecimiento",OBJSMFICHASGRABADAS.cCodEstablecimiento);
			
			SimpleDateFormat formatted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
			
			objJSON.put("sdtFechaSupervicion",formatted.format(new Date(OBJSMFICHASGRABADAS.lFecha)));
			objJSON.put("sdtFecTermino",formatted.format(new Date(OBJSMFICHASGRABADAS.lFechaFin)));
			
			
			objJSON.put("vFechaVigenciaSanidad",formatted.format(new Date(OBJSMFICHASGRABADAS.vFechaVigenciaSanidad)));
			objJSON.put("vFechaVigenciaCertificadoMedico",formatted.format(new Date(OBJSMFICHASGRABADAS.vFechaVigenciaCertificadoMedico)));

			
			objJSON.put("vEspecialidad",OBJSMFICHASGRABADAS.vEspecialidad);
			objJSON.put("vColegiatura",OBJSMFICHASGRABADAS.vColegiatura);
			
			
			objJSON.put("vTipoVehiculo",OBJSMFICHASGRABADAS.vTipoVehiculo);			
			objJSON.put("vPlaca",OBJSMFICHASGRABADAS.vPlaca);
			objJSON.put("vGuiaRemision",OBJSMFICHASGRABADAS.vGuiaRemision);
			
			
			objJSON.put("dPorcentaje",OBJSMFICHASGRABADAS.dPuntajeFicha);
			objJSON.put("iPuntaje",RECORDCARDITEM_DAO.GetPuntos(context, OBJSMFICHASGRABADAS.idFichasGrabadas));
			///objJSON.put("vObservacion",OBJSMFICHASGRABADAS.vDatosRelevantes);
			objJSON.put("iTotalOperarios",OBJSMFICHASGRABADAS.iTotalOperarios);
			//objJSON.put("iCodUsuario",Integer.parseInt(SESION_DAO.BuscarUt(context)));//falta
			objJSON.put("vComite",SESION_DAO.BuscarUt(context));
			
			
			objJSON.put("iCantidadRacionesAdjudicadas",OBJSMFICHASGRABADAS.iCantidadRacionesAdjudicadas);
			objJSON.put("iCantidadRacionesVerificadas",OBJSMFICHASGRABADAS.iCantidadRacionesVerificadas);
			objJSON.put("vNombreRacionesVerificadas",OBJSMFICHASGRABADAS.vNombreRacionesVerificadas);
			objJSON.put("vTurno",OBJSMFICHASGRABADAS.vTurno);
			objJSON.put("iNumeroOperariosHombre",OBJSMFICHASGRABADAS.iNumeroOperariosHombre);
			objJSON.put("iNumeroOperariosMujer",OBJSMFICHASGRABADAS.iNumeroOperariosMujer);
			
			objJSON.put("vTelefonoRepresentanteLegal",OBJSMFICHASGRABADAS.vTelefonoRepresentanteLegal);
			objJSON.put("vEmpresaResponsableSanidad",OBJSMFICHASGRABADAS.vEmpresaResponsableSanidad);
			objJSON.put("vEmpresaResponsableMedico",OBJSMFICHASGRABADAS.vEmpresaResponsableMedico);
			objJSON.put("vNumeroCertificadoSanidad",OBJSMFICHASGRABADAS.vNumeroCertificadoSanidad);
			objJSON.put("bCertificadoMedico",OBJSMFICHASGRABADAS.bCertificadoMedico);
				
			
			objJSON.put("vNroContrato",OBJSMFICHASGRABADAS.vNroContrato);
			objJSON.put("vNroComiteCompra",OBJSMFICHASGRABADAS.vComite);//vNroComiteCompra);
			objJSON.put("vItem",OBJSMFICHASGRABADAS.vItem);
			objJSON.put("vComite",OBJSMFICHASGRABADAS.vComite);
			objJSON.put("vNombreRacionesVerificadas", OBJSMFICHASGRABADAS.vNombreRacion);
			objJSON.put("vNombreEstablecimiento", OBJSMFICHASGRABADAS.vNombreEstablecimiento);
			objJSON.put("iCantidadRacionesVerificadas", OBJSMFICHASGRABADAS.vCantidadSupervisada);
			objJSON.put("iCantidadRacionesProgramadas", OBJSMFICHASGRABADAS.vCantidadProgramada);
			
			
			objJSON.put("vNombreSupervisor", OBJSMFICHASGRABADAS.vNSupervisor);
			objJSON.put("vDNISupervisor", OBJSMFICHASGRABADAS.vDNISupervisor);
			objJSON.put("vObservacion", OBJSMFICHASGRABADAS.vObservacion);
			objJSON.put("cEstado",String.valueOf(OBJSMFICHASGRABADAS.iEstado));
			objJSON.put("iNroLiberacion",OBJSMFICHASGRABADAS.iNroLiberacion);
			
			List<RECORDCARDITEM> Listar=RECORDCARDITEM_DAO.ListarPreguntasRaciones(context, OBJSMFICHASGRABADAS.idFichasGrabadas);
			
			List<ACTACOLEGIOSITEM> listColegiosItem= ACTACOLEGIOSITEM_DAO.ListarTodo(context, OBJSMFICHASGRABADAS.idFichasGrabadas);
			JSONArray listJSONCausales = new JSONArray();
			JSONArray listJSONRespuestas = new JSONArray();
			JSONArray listJSONObservaciones = new JSONArray();
			JSONArray listJSONSeguimiento=new JSONArray();
			JSONArray listJSONColegiosItem = new JSONArray();
			int incremental=0;
			for(RECORDCARDITEM entidad : Listar)
			{
				incremental++;
				/*if(entidad.iTipoControl==1 && entidad.bcheck && entidad.iOrden < 20 )
				{
					JSONObject CausalesJSON=new JSONObject();	
					CausalesJSON.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
					CausalesJSON.put("iCodTipoCausal",entidad.iCodTipoSeccion);
					CausalesJSON.put("cAlternativa",entidad.iCodBcoPreg);								
					listJSONCausales.put(CausalesJSON);
				}
				
				if(entidad.iTipoControl==1 && entidad.bcheck && entidad.iOrden > 20 )
				{
					
					
					
					JSONObject Respuestas=new JSONObject();	
					Respuestas.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
					Respuestas.put("iCodRespuestas",incremental);
					Respuestas.put("iCodBcoPreg",Integer.parseInt(entidad.iCodBcoPreg.trim()));
					listJSONRespuestas.put(Respuestas);
				}
				
				if(entidad.iIndexCombo==1)
					{
						JSONObject Respuestas=new JSONObject();	
						Respuestas.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
						Respuestas.put("iCodRespuestas",incremental);
						Respuestas.put("iCodBcoPreg",Integer.parseInt(entidad.iCodBcoPreg.trim()));
						listJSONRespuestas.put(Respuestas);
					}
					
					
					if(!entidad.vResultado.equals("") &&  !entidad.vResultado.equals(null) && (entidad.iIndexCombo<3 || entidad.iTipoControl==6 ))
					{
						JSONObject Observaciones=new JSONObject();	
						Observaciones.put("iCodOBSERVACIONES",incremental);
						Observaciones.put("iCodBcoPreg",Integer.parseInt(entidad.iCodBcoPreg.trim()));
						Observaciones.put("iCoDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);	
						Observaciones.put("vObservacion",entidad.vResultado);	
						listJSONObservaciones.put(Observaciones);
					}
					
					if(entidad.iIndexCombo==3)
					{
						JSONObject Observaciones=new JSONObject();	
						Observaciones.put("iCodOBSERVACIONES",incremental);
						Observaciones.put("iCodBcoPreg",Integer.parseInt(entidad.iCodBcoPreg.trim()));
						Observaciones.put("iCoDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);	
						Observaciones.put("vObservacion","No Aplica "+entidad.vResultado);	
						listJSONObservaciones.put(Observaciones);
					}*/
				
				if((entidad.iCodTipoSeccion==5 ||entidad.iCodTipoSeccion==6)&&entidad.bcheck){
					
					JSONObject CausalesJSON=new JSONObject();	
					CausalesJSON.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
					CausalesJSON.put("iCodTipoCausal",entidad.iCodTipoSeccion);
					CausalesJSON.put("cAlternativa",entidad.iCodBcoPreg);								
					listJSONCausales.put(CausalesJSON);
				}
				
				if(entidad.iCodTipoSeccion>=8 &&entidad.iCodTipoSeccion<=12){
					if(entidad.iCodFormato==2015)
					{
						
					}
					JSONObject Observaciones=new JSONObject();	
					Observaciones.put("iCodRecordCardItem",entidad.iRecordCardItem);
					Observaciones.put("iCodRecordCardItemPadre",entidad.iRecordCardItemPadre);
					Observaciones.put("iCodFormato",entidad.iCodFormato);	
					Observaciones.put("iValor",entidad.iValor);	
					Observaciones.put("vDescripcion",entidad.vResultado);
					Observaciones.put("vObservacion",entidad.vObservacion);
					Observaciones.put("iTipoControl", entidad.iTipoControl);
					Observaciones.put("bNoAplica",entidad.bcheck);
					listJSONSeguimiento.put(Observaciones);
					
				}
			}
			
			
			for(ACTACOLEGIOSITEM acta :listColegiosItem)
			{
				JSONObject colegioitem=new JSONObject();
				
				colegioitem.put("cCodColegio", acta.cCodColegio);
				colegioitem.put("iCodLiberacion", acta.iCodLiberacion);
				colegioitem.put("iCodItem", acta.iCodItem);
				colegioitem.put("iCantidadLiberada", acta.iCantidadLiberada);
				colegioitem.put("vObservacion", acta.vObservacion);
				listJSONColegiosItem.put(colegioitem);
			
			}
			//listJSONCausales
			objJSON.put("Causales",listJSONCausales);
			objJSON.put("Observaciones",listJSONObservaciones);	
			objJSON.put("Respuestas",listJSONRespuestas);
			objJSON.put("DetalleSeguimiento",listJSONSeguimiento);
			objJSON.put("DetalleColegioRacion", listJSONColegiosItem);
			JSONArray listJSONParticipantes = new JSONArray();
			if(OBJSMFICHASGRABADAS.cCategoria.equals("S"))
        	{					
					JSONObject Participantes2=new JSONObject();	
					Participantes2.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
					Participantes2.put("cDNI",OBJSMFICHASGRABADAS.vDNIControlCalidad);
					Participantes2.put("vNombres",OBJSMFICHASGRABADAS.vNControlCalidad);	
					Participantes2.put("iCodCargo",5);	
					Participantes2.put("iCodParticipante",1);	
					listJSONParticipantes.put(Participantes2);
        	}
			else if(OBJSMFICHASGRABADAS.cCategoria.equals("T"))
        	{					
					JSONObject Participantes2=new JSONObject();	
					Participantes2.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
					Participantes2.put("cDNI",OBJSMFICHASGRABADAS.vDNIPNAEQW);
					Participantes2.put("vNombres",OBJSMFICHASGRABADAS.vNPNAEQW);	
					Participantes2.put("iCodCargo",6);	
					Participantes2.put("iCodParticipante",1);	
					listJSONParticipantes.put(Participantes2);
        	}
			else if(OBJSMFICHASGRABADAS.cCategoria.equals("O"))
        	{					
					JSONObject Participantes2=new JSONObject();	
					Participantes2.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
					Participantes2.put("cDNI",OBJSMFICHASGRABADAS.vDNIControlCalidad);
					Participantes2.put("vNombres",OBJSMFICHASGRABADAS.vNControlCalidad);	
					Participantes2.put("iCodCargo",5);	
					Participantes2.put("iCodParticipante",1);	
					listJSONParticipantes.put(Participantes2);
        	}
			objJSON.put("Participantes",listJSONParticipantes);					
			
			//PuntosCriticos
			JSONArray listJSONPuntosCriticos = new JSONArray();
			List<SMPUNTOCRITICO> listaPC=SMPUNTOCRITICO_DAO.Listar(context, OBJSMFICHASGRABADAS.idFichasGrabadas);
			for(int i=0;i<listaPC.size();i++)
			{	
			
				      JSONObject PuntosCriticos=new JSONObject();	
							PuntosCriticos.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
							PuntosCriticos.put("iCodPuntoC",(i+1));
							PuntosCriticos.put("vDesPCControl",listaPC.get(i).vPuntoCriticoControl);	
							PuntosCriticos.put("vDesLimCritico",listaPC.get(i).vLimiteCritico);	
							PuntosCriticos.put("vDesFrecRevision",listaPC.get(i).vFrecuenRevision);	
							PuntosCriticos.put("vDesCalFrecuencia",listaPC.get(i).vCalibracionFrecuencia);	
							PuntosCriticos.put("iOrden",(i+1));									
					
						listJSONPuntosCriticos.put(PuntosCriticos);
			}
			objJSON.put("PuntosCriticos",listJSONPuntosCriticos);	
			
			//Fotos
			JSONArray listJSONFotos = new JSONArray();

				
				List<SMDETFOTOS> listFotos=SMDETFOTOS_DAO.ListarTodo(context, OBJSMFICHASGRABADAS.idFichasGrabadas);
				
				if(listFotos.size()>0)	
				{
					for(int i=0;i<listFotos.size();i++)
					{
						JSONObject Fotos=new JSONObject();	
						Fotos.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
						Fotos.put("iCodDetFoto",(i+1));
						Fotos.put("vNombreFoto",listFotos.get(i).vNombreFoto+".jpg");	
						Fotos.put("vLatitud",""+listFotos.get(i).vLatitud);	
						Fotos.put("vLongitud",""+listFotos.get(i).vLongitud);
						listJSONFotos.put(Fotos);
					}
				}
				else
				{
					JSONObject Fotos=new JSONObject();	
					Fotos.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
					Fotos.put("iCodDetFoto",1);
					Fotos.put("vNombreFoto","none.jpg");	
					Fotos.put("vLatitud","0");	
					Fotos.put("vLongitud","0");
					listJSONFotos.put(Fotos);
				}
				
			objJSON.put("Fotos",listJSONFotos);	
			
			//Almacenes
					JSONArray listJSONAlmacenes = new JSONArray();
					
					List<SMALMACEN> listaAlamcen =SMALMACEN_DAO.Listar(context, OBJSMFICHASGRABADAS.idFichasGrabadas);
					for(int i=0;i<listaAlamcen.size();i++)
					{
						JSONObject Almacenes=new JSONObject();	
						Almacenes.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
						Almacenes.put("iCodFichaEquipo",(i+1));
						Almacenes.put("iTipoEqAl",3);	
						Almacenes.put("vNombre",listaAlamcen.get(i).vAlmacen);	
						Almacenes.put("vDetalle",listaAlamcen.get(i).vArea);	
						listJSONAlmacenes.put(Almacenes);
					}
					
					List<SMEQUIPOS> listaEquipos=SMEQUIPOS_DAO.Listar(context, OBJSMFICHASGRABADAS.idFichasGrabadas);
					for(int i=0;i<listaEquipos.size();i++)
					{
						JSONObject Almacenes=new JSONObject();	
						Almacenes.put("iCodDetFicha",OBJSMFICHASGRABADAS.idFichasGrabadas);
						Almacenes.put("iCodFichaEquipo",(i+1));
						Almacenes.put("iTipoEqAl",2);	
						Almacenes.put("vNombre",listaEquipos.get(i).vEquipo);	
						Almacenes.put("vDetalle",listaEquipos.get(i).vMarca);	
						listJSONAlmacenes.put(Almacenes);
					}
					objJSON.put("Almacenes",listJSONAlmacenes);
		

		
		Data=objJSON.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return Data;
       
	}
}