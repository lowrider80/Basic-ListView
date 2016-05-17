//LOWRIDER PRUEBA 123


package com.supervision.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLite extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "BDQALIWARMA"; 
    private static final int DATABASE_VERSION = 1;        

    private static final String TABLE_SESION = "CREATE TABLE SESION( "
													+"iSesion integer NOT NULL, "
													+"vUsuario text NULL); ";
    
    private static final String TABLE_SMBCOPREGUNTAS = "CREATE TABLE SMBCOPREGUNTAS( "
														+"iCodBcoPreg integer primary key NOT NULL, " //autoincrement
														+"iCodFicha integer NULL, "
														+"iCodTipoSeccion integer NULL, "
														+"cSeccion text NULL, "
														+"vDetalleSeccion text NULL, "
														+"iValor integer NULL, "
														+"iTipoControl integer NULL, "
														+"bVisible integer NULL, "
														+"bactivo integer NULL, "
														+"iOrden integer NULL, "
														+"iTipoColumna integer NULL, "
														+"vTabla text NULL, "
														+"vCampo text NULL); ";      

    
    private static final String TABLE_SMCAUSALES = "CREATE TABLE SMCAUSALES( "
													+"iCodCausal integer primary key autoincrement NOT NULL, "
													+"iCodFicha integer NOT NULL, "
													+"iCodTipoCausal integer NULL, "
													+"cAlternativa text NULL, "
													+"vDesCausal text NULL, "
													+"iTipoControl integer NULL, "
													+"iOrden integer NULL, "
													+"bActivo integer NULL, "
													+"vTabla text NULL, "
													+"vCampo text NULL); ";
    
    private static final String TABLE_SMDETCAUSALES = "CREATE TABLE SMDETCAUSALES( "
													+"iCodDetFicha integer NOT NULL, "
													+"iCodDetCausal integer primary key autoincrement NOT NULL, "
													+"iCodTipoCausal integer NOT NULL, "
													+"cAlternativa text NULL, "
													+"vObservacion text NULL, "
													+"bactivo integer NULL, "
													+"dtFecReg text NULL); ";

    private static final String TABLE_SMDETFOTOS = "CREATE TABLE SMDETFOTOS( "
    												+"iCodDetFoto integer primary key autoincrement NOT NULL, "
													+"idFichasGrabadas integer NULL, "
													+"vNombreFoto text NULL, "
													+"vLatitud numeric NULL, "
													+"vLongitud numeric NULL, "
													+"dtFecReg text NULL); ";

    private static final String TABLE_SMMAEFICHAS = "CREATE TABLE SMMAEFICHAS( "
													+"iCodFicha integer primary key autoincrement NOT NULL, "
													+"vDescFicha text NULL, "
													+"iCodTipoInst integer NULL, "
													+"cCategoria text NULL, "
													+"bactivo integer NULL, "
													+"iCodUsuario integer NULL, "
													+"dtFecReg text NULL, "
													+"vNombreTerminal text NULL, "
													+"vIpTerminal text NULL); ";	

    private static final String TABLE_TABLETPROVEEDORES = "CREATE TABLE TABLETPROVEEDORES( "
														//query += "cCodDetEstablecimiento text NULL, ";
														+"cCodProveedor text NULL, "
														+"vNombreProveedor text NULL, "
														+"cCodEstablecimiento text NULL, "
														+"iNro integer NULL, "
														+"vDireccionEstablecimiento text NULL, "
														+"vReferenciaEstablecimiento text NULL, "
														+"vLatitudEstablecimiento text NULL, "
														+"vLongitudEstablecimiento text NULL, "
														+"vEstablecimientoDistrito text NULL, "
														+"vEstablecimientoProvincia text NULL, "
														+"vEstablecimientoDepartamento text NULL, "
														+"iTipo integer NULL, "
														//query += "bActivo integer NULL, ";
														+"cCodDepartamento text NULL, "
														+"cCodProvincia text NULL, "
														+"cCodUbigeo text NULL, "
														+"cRUC text NULL, "
														+"vObservacion text NULL, "
														+"cMultiple text NULL,"
														+"vNombreRacion text NULL,"
														+"vCantidadSupervisada text null,"
														+"vNombreDepartamento text NULL,"
														+"vNombreProvincia text NULL,"
														+"vNombreDistrito text NULL, " 
														+"iCodTipoRacion int NULL); ";


	private static final String TABLE_TABLETSUPERVISORES = "CREATE TABLE TABLETSUPERVISORES( "
														+"vNombre text NULL, "
														+"cCodDocPersona text NULL, "
														+"vDesCargo text NULL); ";
    
    private static final String TABLE_TABLETPOSTORES = "CREATE TABLE TABLETPOSTORES( "
			//query += "cCodDetEstablecimiento text NULL, ";
    		+"iCodConvocatoria int NULL, "
    		+"vConvocatoria text NULL, "
    		+"iCodUnidad int NULL, "
    		+"vUnidad text NULL, "
    		+"iCodComite int NULL, "
    		+"vComite text NULL, "
    		+"iCodItem int NULL, "
    		+"vItem text NULL, "
    		+"iCodModalidad int NULL, "
    		+"vModalidad text NULL, "
    		+"iCodPostor int NULL, "
			+"cCodProveedor text NULL, "
			+"vNombreProveedor text NULL, "
			+"cCodEstablecimiento text NULL, "
			+"vDireccionEstablecimiento text NULL, "
			+"cRUC text NULL, "
			+"iPuntajeTecnico int NULL, "
			+"iPuntajeEconomico int NULL, "
			+"vObservacion text NULL, "
			+"vComponentes text NULL, "
			+"dFechaInicio text NULL,"
			+"dFechaFin text NULL); ";
    
    private static final String TABLE_SMFORMATO = "CREATE TABLE SMFORMATO( "
													+"iCodFormato integer primary key NOT NULL, "
													+"iCodFicha integer NULL, "
													+"iCodTipoSeccion integer NULL, "
													+"cSeccion text NULL, "
													+"vDetalleSeccion text NULL, "
													+"iValor text NULL, "
													+"iTipoControl integer NULL, "
													+"bVisible integer NULL, "
													+"bactivo integer NULL, "
													+"iOrden integer NULL,"
													+"vTabla text NULL, "
													+"vCampo text NULL); ";
	
    private static final String TABLE_DBQUESTIONARY = "CREATE TABLE DBQUESTIONARY( "
														+"iCodBcoPreg text  NOT NULL, " //autoincrement
														+"iCodFicha integer NULL, "
														+"ordenllamada integer NULL, "
														+"iCodTipoSeccion integer NULL, "
														+"cSeccion text NULL, "
														+"vDetalleSeccion text NULL, "
														+"iValor integer NULL, "
														+"iTipoControl integer NULL, "
														+"bVisible integer NULL, "
														+"bactivo integer NULL, "
														+"iOrden integer NULL, "
														//+"iTipoColumna integer NULL, " //SE QUITA COMENTARIO A ITIPOCOLUMNA
														+"vTabla text NULL, "
														+"iTipoColumna integer NULL, "
														+"vCampo text NULL); ";
    
   /*
    private static final String TABLE_MAEDEPARTAMENTO = "CREATE TABLE MAEDEPARTAMENTO( "
														+"cCodDepartamento text primary key NOT NULL, " //autoincrement
														+"vDepartamento text NULL); ";
    
    private static final String TABLE_MAEDISTRITO = "CREATE TABLE MAEDISTRITO( "
													+"cCodDistrito text primary key NOT NULL, " //autoincrement
													+"cCodProvincia text NULL, "
													+"vDistrito text NULL); ";
    
    private static final String TABLE_MAEPROVINCIA = "CREATE TABLE MAEPROVINCIA( "
													+"cCodProvincia text primary key NOT NULL, " //autoincrement
													+"vProvincia text NULL, "
													+"cCodDepartamento text NULL); ";
     */
    private static final String TABLE_RECORDCARDITEM = "CREATE TABLE RECORDCARDITEM( "
    												+"iRecordCardItem integer primary key autoincrement NOT NULL, "	
    												+"iCodBcoPreg text NULL, " //autoincrement PREVIOUS WAS NOT NULL
    												+"iCodFormato text NULL,"
													+"iCodFicha integer NULL, "
													+"ordenllamada integer NULL, "
													+"iCodTipoSeccion integer NULL, "
													+"cSeccion text NULL, "
													+"vDetalleSeccion text NULL, "
													+"iValor integer NULL, "
													+"iTipoControl integer NULL, "													
													+"iOrden integer NULL, "
													+"cCodEstablecimiento text NULL, "
													+"cCodProveedor text NULL, "
													+"vResultado text NULL, "
													+"vTabla text NULL, "
													+"iIndexCombo integer NULL, "
													+"idFichasGrabadas integer NULL, "
													+"bcheck integer NULL, "
													+"bactivo integer NULL, "
													+"iTipoColumna integer NULL, "
													+"vCampo text NULL,"
													+"iRecordCardItemPadre integer NULL,"
													+"iRecordCardItemReference integer NULL,"
													+"vObservacion text NULL," +
													" bVisible integer null); ";
    

    private static final String TABLE_SMFICHAINICIALANEXO = "CREATE TABLE SMFICHAINICIALANEXO( "
			+"idFichaGrabadas int NULL, "
			+"iCodModalidad int NULL, "
    		+"iCodComite int NULL, "
    		+"vComite text NULL, "
    		+"vItem text NULL, "
    		+"vCodItem text NULL, "
    		+"vCodPostor text NULL, "
    		+"iCantSolido int NULL, "
    		+"iCantSolidoP int NULL, "
    		+"iCantBebible int NULL, "
    		+"iCantBebibleP int NULL, "
    		+"iCantAcompanamiento int NULL, "
    		+"iCantAcompanamientoP int NULL, "
    		+"iCantAlimento int NULL, "
    		+"iCantAlimentoP int NULL, "
    		+"iCantGalleta int NULL, "
    		+"iCantGalletaP int NULL, "
    		+"iCalificado int NULL); ";

    
    
    private static final String TABLE_SMFICHASGRABADAS = "CREATE TABLE SMFICHASGRABADAS( "
			+"idFichasGrabadas integer primary key autoincrement NOT NULL, "
			+"cCodEstablecimiento text  NOT NULL, " //autoincrement
			+"cCodProveedor text NOT NULL, "
			+"vCodPostor text NULL, "
			+"iCodFicha integer NOT NULL, "
			+"vDescFicha text NOT NULL, "			
			+"iEstado integer NOT NULL, "
			+"lFecha numeric NOT NULL, "
			+"lFechaFin numeric NOT NULL, "			
			+"vNControlCalidad text NOT NULL, "
			+"vDNIControlCalidad text NOT NULL, "
			+"vEspecialidad text NOT NULL, "
			+"vColegiatura text NOT NULL, "
			+"vNRepresentanteLegal text NOT NULL, "
			+"vDNIRepresentanteLegal text NOT NULL, "
			+"vNSupervisor text NOT NULL, "													
			+"vDNISupervisor integer NOT NULL, "
			+"vNPNAEQW text NOT NULL, "
			+"vDNIPNAEQW text NOT NULL, "
			+"vDatosRelevantes text NOT NULL, "
			+"cCategoria text NOT NULL, "			
			+"vTipoVehiculo text NOT NULL, "			
			+"vPlaca text NOT NULL, "			
			+"vGuiaRemision text NOT NULL, "	
			+"iTotalOperarios integer NOT NULL, "
			+"dOpcion1 text NOT NULL, "
			+"dOpcion2 numeric NOT NULL, "	// SI HAY ACTIVIDAD 1 = 1 ELSE 0
			+"dOpcion3 numeric NOT NULL, "	// SI HAY ACTIVIDAD 1 = 1 ELSE 0
			+"dOpcion4 numeric NOT NULL, "	// SI HAY ACTIVIDAD 1 = 1, ELSE 0
			+"dPuntajeFicha numeric NOT NULL, "
			+"dPuntajeAnexo numeric NOT NULL, "
			+"iTotalPreguntas integer NOT NULL, "
			+"bCalificaFicha integer NOT NULL, "
			+"bCalificaAnexo integer NOT NULL, "
			
			+"iCantidadRacionesAdjudicadas integer NOT NULL, "
			+"iCantidadRacionesVerificadas integer NOT NULL, "
			+"vNombreRacionesVerificadas text NOT NULL, "
			+"vTurno text NOT NULL, "
			+"iNumeroOperariosHombre integer NOT NULL, "
			+"iNumeroOperariosMujer integer NOT NULL, "
			
			+"vNroContrato text NOT NULL, "
			+"vNroComiteCompra text NOT NULL, "
			+"vItem text NOT NULL, "

			+"iCodContrato integer NOT NULL, "
			+"iCodCronograma integer NULL, "
			+"iNroLiberacion integer NOT NULL, "
			+"vTelefonoRepresentanteLegal text NOT NULL, "
			+"vEmpresaResponsableSanidad text NOT NULL, "
			+"vFechaVigenciaSanidad numeric NOT NULL, "  //PUEDE SER FECHA PLAZO
			+"vFechaVigenciaCertificadoMedico numeric NOT NULL, "
			+"vNumeroCertificadoSanidad text NOT NULL, "
			+"bCertificadoMedico integer NOT NULL, " //PUEDE SER NUMERO DIAS PLAZO
			+"iTipoEstablecimiento integer NOT NULL, "
			+"vEmpresaResponsableMedico text NOT NULL," +
			" vObservacion text NULL, "
			+" vNombreRacion text NULL,"
			+" vNombreEstablecimiento text NULL,"
			+" vComite text NULL," 
			+ " vCantidadSupervisada int null,"
			+ " vCantidadProgramada int null,"
			+ " vNombresSupervisor text null,"
			+ " vApellidosSupervisor text null"
			+ " ) ; ";
    
    

    
    private static final String TABLE_SMPUNTOCRITICO = "CREATE TABLE SMPUNTOCRITICO( "
			+"iPuntoCritico integer primary key autoincrement NOT NULL, "
			+"idFichasGrabadas integer NOT NULL, "
			+"vPuntoCriticoControl text NOT NULL, "			
			+"vLimiteCritico text NOT NULL, "
			+"vFrecuenRevision text NOT NULL, "
			+"vCalibracionFrecuencia text NOT NULL); ";
   
    
    private static final String TABLE_SMEQUIPOS = "CREATE TABLE SMEQUIPOS( "
    		+"iEquipos integer primary key autoincrement NOT NULL, "
			+"idFichasGrabadas integer NOT NULL, "
			+"vEquipo text  NOT NULL, " //autoincrement
			+"vNSerie text  NOT NULL, " //autoincrement
			+"vMarca text NOT NULL); ";
    
    private static final String TABLE_SMALMACEN = "CREATE TABLE SMALMACEN( "
			+"iAlmacen integer primary key autoincrement NOT NULL, "
			+"idFichasGrabadas integer NOT NULL, "
			+"vAlmacen text  NOT NULL, " //autoincrement
			+"vArea text NOT NULL); ";
    
    private static final String TABLE_LIBERACIONRECORDCARDITEM = "CREATE TABLE LIBERACIONRECORDCARDITEM( "
			+"iLIBERACIONRECORDCARDITEM integer primary key autoincrement NOT NULL,"
			+"idFichasGrabadas integer NOT NULL,"
			+"iCodDetFicha integer NOT NULL,"//Se agrego el campo que se trae del servicio y la tabla SMDetFicha
			+"iCodProducto integer NOT NULL,"
			+"vNomMarca text NOT NULL,"
			+"vNomProducto text NOT NULL,"
			+"iPresentacion integer NOT NULL,"
			+"iIndexComboPresentacion integer NOT NULL,"
			+"iCantidad integer NOT NULL,"
			+"iIndexCombo integer NOT NULL,"
			+"vResultado text NOT NULL," 
			+"iOrden integer NOT NULL," 
			+"bactivo integer NOT NULL);";
    
    private static final String TABLE_FICHATECNICAPRESENTACION = "CREATE TABLE FICHATECNICAPRESENTACION( "
			+"iCodFichaTecnicaPresentacion integer primary key NOT NULL, "
			+"iCodFichaTecnica integer NOT NULL, "
			+"vNombre text NOT NULL); ";
    
    private static final String TABLE_DETALLELIBERACION = "CREATE TABLE DETALLELIBERACION( "
    		+"iDETALLELIBERACION integer primary key autoincrement NOT NULL,"
			+"iCodContrato integer NOT NULL, "
			+"iCodDetFicha integer NOT NULL, "//se agrego para guardar el iCodDetFicha que viene del servicio GetListaLiberacionDetalleXUT_B
			+"cCodProveedor text NOT NULL, "
			+"cCodEstablecimiento text NOT NULL, "
			+"iCodLiberacion integer NOT NULL, "
			+"iCodDetLiberacion integer NOT NULL, "
			+"dcCantidad numeric NOT NULL, "
			+"iCodFichaTecnica integer NOT NULL, "
			+"iCodFichaTecnicaPresentacion integer NOT NULL, "
			+"Presentacion text NOT NULL, "
			+"Marca text NOT NULL, "
			+"iNumEntrega text NOT NULL, "
			+"iCodCronogramaEntrega integer NOT NULL, "
			+"vNombreFichaTecnica text NOT NULL); ";
    
    private static final String TABLE_CONTRATO = "CREATE TABLE CONTRATO( "
			+"iCodContrato integer primary key NOT NULL,"			
			+"vNomContrato text NOT NULL,"			
			+"cCodProveedor text NOT NULL,"
			+"iCodItem integer NOT NULL,"
			+"iCodComite integer NOT NULL,"
			+"iCodUnidad integer NOT NULL,"
			+"iNumContrato integer NOT NULL,"
			+"iCodModalidad integer NOT NULL,"
			+"DescripcionUT text NOT NULL,"
			+"vComite text NOT NULL,"
			+"vNombreItem text NOT NULL,"
			+"iCodProveedor integer NOT NULL,"
			+"iCantidadProgramada integer NULL );";
    
    
    
    private static final String TABLE_PROVEEDORESITEMS = "CREATE TABLE PROVEEDORESITEMS( "
    		+"iCodUnidad integer   NOT NULL, "
			+"cCodEstablecimiento TEXT NOT NULL, "
			+"cCodProveedor TEXT NOT NULL, "
			//+"vNombreProveedor text  NOT NULL, " //autoincrement
			+"vNombreItem text  NOT NULL, " //autoincrement
			+"iTipoFicha text  NOT NULL, " //autoincrement
			+"iCodItem integer NOT NULL); ";
       
    private static final String TABLE_ESTABLECIMIENTOITEMSATENDIDOS="CREATE TABLE ESTABLECIMIENTOSITEMSATENDIDOS( "
    		+"iCodProveedor integer NULL, "
			+"cCodEstablecimiento text NULL, "
			+"iCodFicha integer NULL, "
			+"iCodItem integer NOT NULL, "
			+"iCodComite integer NOT NULL ); ";
    
    private static final String TABLE_SECCIONFICHAGRABADA="CREATE TABLE SECCIONFICHAGRABADA("
    		+"idFichasGrabadas integer NOT NULL,"
    		+"iCodFicha integer NOT NULL,"
    		+"iCodTipoSeccion integer NOT NULL,"
    		+" bNoAplicaSeccion integer NOT NULL);";
    
    private static final String TABLE_COLEGIOSITEM ="CREATE TABLE COLEGIOSITEM("
    		+"cCodColegio text not null,"
    		+"iCodUnidad integer not null,"
    		+"cCodModular text not null,"
    		+"cCodNivelEducativo text null,"
    		+"vNombre text null,"
    		+"vDireccion text null,"
    		+"vNombreItem text null,"
    		+"iCodItem integer not null," 
    		+"iNroUsuario integer not null,"
    		+"EstadoItem integer null);";
    
    private static final String TABLE_ACTACOLEGIOSITEM ="CREATE TABLE ACTACOLEGIOSITEM("
    		+"iACTACOLEGIOSITEM integer primary key autoincrement NOT NULL,"
    		+"cCodColegio text not null,"
    		+"iCodLiberacion integer null," //cambiar a not null
    		+"cCodTurno text null," //cambiar a not null
    		+"cCodEstablecimiento text null," //cambiar a null
    		+"cCodProveedor text null," //not null
    		+"cEstado text null," //not null
    		+"idFichasGrabadas integer not null,"
    		+"iCantidadProgramada text null,"
    		+"iCantidadLiberada text null,"
    		+"iCodItem integer not null," 
    		+"vObservacion text null,"
    		+"iCodUnidad integer null, " //not null
    	 	+ "cCodModular text  null, "  //not null
    	 	+ "cCodNivelEducativo text null," 
    	 	+ "vNombreColegio text null," 
    	 	+ "vNivelEducativo text null," 
    	 	+ "iOrden integer null," 
    	 	+ "vFechaLiberacion text null,"
    	 	+ "iEstadoAdendado int null);"; //not null
    		
    private static final String TABLE_COMPONENTELIBERACION = "CREATE TABLE COMPONENTELIBERACION( "
			+"iComponenteLiberacion integer primary key autoincrement NOT NULL, "	
			+"iCodFormato text NULL,"
			+"iCodFicha integer NULL, "
			+"iCodTipoSeccion integer NULL, "
			+"cSeccion text NULL, "
			+"vDetalleSeccion text NULL, "
			+"iValor integer NULL, "
			+"iTipoControl integer NULL, "													
			+"iOrden integer NULL, "
			+"cTipoRacion text NULL, "
			+"cCodProveedor text NULL, "
			+"vResultado text NULL, "
			+"iIndexCombo integer NULL, "
			+"bcheck integer NULL, "
			+"bactivo integer NULL, "
			+"vCampo text NULL,"
			+"iComponenteLiberacionPadre integer NULL,"
			+"vObservacion text NULL," 
			+" bVisible integer null); ";
    
    
    private static final String TABLE_COMPONENTERACION = "CREATE TABLE COMPONENTERACION( "
			+"iCodFichaTecnicaRacion integer NOT NULL, "	
			+"iGrupoRacion integer NOT NULL,"
			+"vNombreComponente text NULL, "
			+"vGrupoComponente text NULL, "
			+"iCodTipoRacion integer NOT NULL, "
			+"vTipoComponente text NULL," +
			"vTipoRacion text NULL); ";
    
	public SQLite(Context context, CursorFactory factory) {
		super(context, DATABASE_NAME, factory, DATABASE_VERSION);
	}

	private static final String TABLE_SMFICHASGBOTROS = "CREATE TABLE SMFICHASGBOTROS( "
			+"idFichasGrabadas integer primary key autoincrement NOT NULL, "
			+"idSMFICHAS integer NOT NULL,"
			+"cCodEstablecimiento text  NOT NULL, " //autoincrement
			+"cCodProveedor text  NULL, "
			+"iCodFicha integer NOT NULL, "
			+"iNumDiasPlazo integer NOT NULL, "
			+"lFechaPlazo numeric NOT NULL, "
			+"vNSupervisor text  NULL, "
			+"vDNISupervisor integer  NULL, "
			+"iTipoEstablecimiento integer NOT NULL, "
			+"iNumConforme integer NOT NULL, "
			+"iNumNoConforme integer NOT NULL, "
			+"iNumTotalVh integer NOT NULL, "
			+"iNumTotalVhRv integer NOT NULL) ";

	private static final String TABLE_SMITEMSTRANSPORTE = "CREATE TABLE SMITEMSTRANSPORTE( "
			+"idFichasGrabadas integer primary key autoincrement NOT NULL, "
			+"idSMFICHAS integer NOT NULL,"
			+"iRecordCardPadre integer NOT NULL, "
			+"vCodItem integer NOT NULL, "
			+"vNItem text  NULL) ";


	private static final String TABLE_TABLETVEHICULOS="CREATE TABLE TABLETVEHICULOS ( "
			+"id integer primary key autoincrement NOT NULL, "
			+"iCodRutaEntrega integer NOT NULL, "
			+"iCodContrato integer NOT NULL, "
			+"vPlaca text NOT NULL,"
			+"cCodEstablecimiento text NOT NULL, "
			+"cCodProveedor text NOT NULL, "
			+"iCodUnidad integer NOT NULL ,"
			+"iCodTipoVehiculo integer NOT NULL , "
			+"vNombre text NOT NULL) ";

	private static final String TABLE_MATERIAPRIMA="CREATE TABLE MATERIAPRIMA ( "
			+"id integer primary key autoincrement NOT NULL, "
			+"cCodEstablecimiento text NOT NULL, "
			+"cCodProveedor text NOT NULL, "
			+"cEstado text NOT NULL,"
			+"iCodModalidad integer NOT NULL, "
			+"vCaracteristica text NOT NULL, "
			+"vFabricante text NOT NULL ,"
			+"vMarca text NOT NULL , "
			+"vNroLote text NOT NULL) ";


	@Override
	public void onCreate(SQLiteDatabase db) {
				db.execSQL(TABLE_SESION);
                db.execSQL(TABLE_SMBCOPREGUNTAS);
                db.execSQL(TABLE_SMCAUSALES);
                db.execSQL(TABLE_SMDETCAUSALES);
                db.execSQL(TABLE_SMDETFOTOS);
                db.execSQL(TABLE_SMMAEFICHAS);
                db.execSQL(TABLE_TABLETPROVEEDORES);
				db.execSQL(TABLE_TABLETSUPERVISORES);
                db.execSQL(TABLE_TABLETPOSTORES);
                db.execSQL(TABLE_SMFORMATO);
                db.execSQL(TABLE_DBQUESTIONARY);
                //db.execSQL(TABLE_MAEDEPARTAMENTO);
                //db.execSQL(TABLE_MAEDISTRITO);                
                //db.execSQL(TABLE_MAEPROVINCIA);                
                db.execSQL(TABLE_RECORDCARDITEM);
                db.execSQL(TABLE_SMFICHASGRABADAS);
                db.execSQL(TABLE_SMFICHAINICIALANEXO);
                db.execSQL(TABLE_SMPUNTOCRITICO);                
                db.execSQL(TABLE_SMEQUIPOS);
                db.execSQL(TABLE_SMALMACEN);
                db.execSQL(TABLE_LIBERACIONRECORDCARDITEM);
                db.execSQL(TABLE_FICHATECNICAPRESENTACION);
                db.execSQL(TABLE_DETALLELIBERACION);
                db.execSQL(TABLE_CONTRATO);
                db.execSQL(TABLE_PROVEEDORESITEMS);
                db.execSQL(TABLE_ESTABLECIMIENTOITEMSATENDIDOS);
                db.execSQL(TABLE_SECCIONFICHAGRABADA);
                db.execSQL(TABLE_COLEGIOSITEM); 
                db.execSQL(TABLE_ACTACOLEGIOSITEM); 
                db.execSQL(TABLE_COMPONENTELIBERACION); 
                db.execSQL(TABLE_COMPONENTERACION);
				db.execSQL(TABLE_SMFICHASGBOTROS);
				db.execSQL(TABLE_SMITEMSTRANSPORTE);
				db.execSQL(TABLE_TABLETVEHICULOS);
				db.execSQL(TABLE_MATERIAPRIMA);
                
        }   
         
	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
				db.execSQL("drop table if exists SESION");		
				db.execSQL(TABLE_SESION);
                db.execSQL("drop table if exists SMBCOPREGUNTAS");
                db.execSQL(TABLE_SMBCOPREGUNTAS);       
                db.execSQL("drop table if exists SMCAUSALES");
                db.execSQL(TABLE_SMCAUSALES);
                db.execSQL("drop table if exists SMDETCAUSALES");
                db.execSQL(TABLE_SMDETCAUSALES);
                db.execSQL("drop table if exists SMDETFOTOS");
                db.execSQL(TABLE_SMDETFOTOS);
                db.execSQL("drop table if exists SMMAEFICHAS");
                db.execSQL(TABLE_SMMAEFICHAS);
                db.execSQL("drop table if exists TABLETPROVEEDORES");
				db.execSQL(TABLE_TABLETSUPERVISORES);
				db.execSQL("drop table if exists TABLETSUPERVISORES");
				db.execSQL(TABLE_TABLETPROVEEDORES);
                db.execSQL("drop table if exists TABLETPOSTORES");
				db.execSQL(TABLE_TABLETPOSTORES);
                db.execSQL("drop table if exists SMFORMATO");
                db.execSQL(TABLE_SMFORMATO);
                db.execSQL("drop table if exists DBQUESTIONARY");
                db.execSQL(TABLE_DBQUESTIONARY);
                /*
                db.execSQL("drop table if exists MAEDEPARTAMENTO");
                db.execSQL(TABLE_MAEDEPARTAMENTO);
                db.execSQL("drop table if exists MAEDISTRITO");
                db.execSQL(TABLE_MAEDISTRITO);     
                db.execSQL("drop table if exists MAEPROVINCIA");
                db.execSQL(TABLE_MAEPROVINCIA);    
                */
                db.execSQL("drop table if exists RECORDCARDITEM");
                db.execSQL(TABLE_RECORDCARDITEM);      
                db.execSQL("drop table if exists SMFICHASGRABADAS");
                db.execSQL(TABLE_SMFICHASGRABADAS);   
                db.execSQL("drop table if exists SMFICHAINICIALANEXO");
                db.execSQL(TABLE_SMFICHAINICIALANEXO);   
                db.execSQL("drop table if exists SMPUNTOCRITICO");
                db.execSQL(TABLE_SMPUNTOCRITICO);
                db.execSQL("drop table if exists SMEQUIPOS");
                db.execSQL(TABLE_SMEQUIPOS);
                db.execSQL("drop table if exists SMALMACEN");
                db.execSQL(TABLE_SMALMACEN);
                db.execSQL("drop table if exists LIBERACIONRECORDCARDITEM");
                db.execSQL(TABLE_LIBERACIONRECORDCARDITEM);
                db.execSQL("drop table if exists FICHATECNICAPRESENTACION");
                db.execSQL(TABLE_FICHATECNICAPRESENTACION);
                db.execSQL("drop table if exists DETALLELIBERACION");
                db.execSQL(TABLE_DETALLELIBERACION);
                db.execSQL("drop table if exists CONTRATO");
                db.execSQL(TABLE_CONTRATO);
                db.execSQL("drop table if exists PROVEEDORESITEMS");
                db.execSQL(TABLE_PROVEEDORESITEMS);
                db.execSQL("drop table if exists ESTABLECIMIENTOSITEMSATENDIDOS");
                db.execSQL("TABLE_ESTABLECIMIENTOITEMSATENDIDOS");
                db.execSQL("drop table if exists SECCIONFICHAGRABADA");
                db.execSQL("TABLE_SECCIONFICHAGRABADA");
                db.execSQL("drop table if exists COLEGIOSITEM");
                db.execSQL("COLEGIOSITEM");
                db.execSQL("drop table if exists ACTACOLEGIOSITEM");
                db.execSQL("ACTACOLEGIOSITEM");
                db.execSQL("drop table if exists COMPONENTELIBERACION");
                db.execSQL("COMPONENTELIBERACION");
                db.execSQL("drop table if exists COMPONENTERACION");
                db.execSQL("COMPONENTERACION");
				db.execSQL("drop table if exists SMFICHASGBOTROS");
				db.execSQL(TABLE_SMFICHASGBOTROS);
				db.execSQL("drop table if exists SMITEMSTRANSPORTE");
				db.execSQL(TABLE_SMITEMSTRANSPORTE);
				db.execSQL("drop table if exists TABLETVEHICULOS");
				db.execSQL(TABLE_TABLETVEHICULOS);
				db.execSQL("drop table if exists TABLETVEHICULOS");
				db.execSQL(TABLE_MATERIAPRIMA);
                
        }
	
	

	

}