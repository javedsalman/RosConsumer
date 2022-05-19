package eu.arrowhead.application.skeleton.consumer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;

import ai.aitia.arrowhead.application.library.ArrowheadService;
import eu.arrowhead.common.CommonConstants;
import eu.arrowhead.common.Utilities;
import eu.arrowhead.common.dto.shared.OrchestrationFlags.Flag;
import eu.arrowhead.common.dto.shared.OrchestrationFormRequestDTO;
import eu.arrowhead.common.dto.shared.OrchestrationFormRequestDTO.Builder;
import eu.arrowhead.common.dto.shared.OrchestrationResponseDTO;
import eu.arrowhead.common.dto.shared.OrchestrationResultDTO;
import eu.arrowhead.common.dto.shared.ServiceQueryFormDTO;
import eu.arrowhead.common.exception.ArrowheadException;

import org.json.JSONArray;
import org.json.JSONObject;

@SpringBootApplication
@ComponentScan(basePackages = {CommonConstants.BASE_PACKAGE, "ai.aitia"}) //TODO: add custom packages if any
public class ConsumerMain implements ApplicationRunner {
    
    //=================================================================================================
	// members
	
    @Autowired
	private ArrowheadService arrowheadService;
    
	private final Logger logger = LogManager.getLogger( ConsumerMain.class );
    
    //=================================================================================================
	// methods

	//------------------------------------------------------------------------------------------------
    public static void main( final String[] args ) {
    	SpringApplication.run(ConsumerMain.class, args);
    }

    //-------------------------------------------------------------------------------------------------
    @Override
	public void run(final ApplicationArguments args) throws Exception {
    	
    	//INITIALIZE TEMPERATURE & PRICE SET POINTS    	
    
    	
    	double Setpoint = 0.0;
      int MillingState = 255;
      int DrillingState = 255;
    	double NewSetpoint= 0.0;
    	String price= "";
    	int hour =LocalDateTime.now().getHour();

  
    while(true) 
    {	
    
    	//GET CURRENT PRICE & SETPOINT--------------------------------------------------------------------------------------------------------
    	price=getPrice();
     System.out.println(price);
     
    JSONArray jsonArr = new JSONArray(price);
  
       for (int i = 0; i < jsonArr.length(); i++)
        {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            String qid = (String) jsonObj.get("id");
          System.out.println("--"+ qid + "--");
            String temp7 = "Q7";
            String temp5 = "I9";
            String mltrue = "true";
            String drtrue = "true";
            String mlfalse = "false";
            String drfalse = "false";
            
            
            System.out.println("--"+ qid + "--");
            
           // milling = jsonObj.getString("value");
           // System.out.println(milling);
        /*    boolean retval1 = qid.equals(temp7);
           if (retval1 == true)
            {
           // System.out.println("Hurrah");
            String milling = (String) jsonObj.get("value");
            System.out.println(milling);
            
            boolean retval2 = milling.equals(mltrue);
              if (retval2 == true)
              {
                  ZwaveSwitchMilling(255);
               System.out.println("milling light it turned ON ");   
                    
              }
            
            boolean retval3 = milling.equals(mlfalse);
              if (retval3 == true)
              {
                  ZwaveSwitchMilling(0);
                     System.out.println("milling light it turned OFF ");           
              }
            
            
            } */
       /*-------------------------------------------------------------------- */     
               boolean retval4 = qid.equals(temp5);
           if (retval4 == true)
            {
            System.out.println("Hurrah");
            String robpickup = (String) jsonObj.get("value");
            System.out.println(robpickup);
            
            boolean retval5 = robpickup.equals(drfalse);
              if (retval5 == true)
              {
                  Rospickupservice();
            System.out.println("Robot has picked Up ");
                    
              }
            
            /* boolean retval6 = drilling.equals(mlfalse);
              if (retval6 == true)
              {
                  ZwaveSwitchDrilling(0);
                     System.out.println("drilling light it turned OFF ");           
              } */
            
            
           } 
    
            
        }
		//Thread.sleep(10000);
		
		
     }   

    //  Thread.sleep(20000);
     
    //	Setpoint= getSetpoint(Tmax, Tmin, Pmax, Pmin, price);
    	
//      while(true) {
//      
//      ZwaveSwitchMilling(MillingState);
//      
//      Thread.sleep(10000);
//      MillingState = 0;
//      ZwaveSwitchMilling(MillingState);
//      
//      Thread.sleep(10000);
//      MillingState = 255;
//      ZwaveSwitchMilling(MillingState);
//      ////////////////////////
//      ZwaveSwitchDrilling(DrillingState);
//      
//      Thread.sleep(10000);
//      DrillingState = 0;
//      ZwaveSwitchDrilling(DrillingState);
//      
//      Thread.sleep(10000);
//      DrillingState = 255;
//      ZwaveSwitchDrilling(DrillingState);
//      
//      }
  ///////////////////////////////////////////////////////////////////////////////////////  		
 /*   	while(true) {
    	if(hour!=LocalDateTime.now().getHour())	{
    		//GET CURRENT PRICE & SETPOINT--------------------------------------------------------------------------------------------------------
        	price=getPrice();
        	NewSetpoint= getSetpoint(Tmax, Tmin, Pmax, Pmin, price);

        	//COMPARE SETPOINTS AND SEND NEW-SETPOINT TO ZWAVE PROVIDER------------------------------------------------------------------------------------------------------------------------------	
        	if(NewSetpoint==Setpoint) {
        		logger.info("No Change in Setpoint");
        	}
        	else {
        		ZwaveSetpointRegulation(MillingState);
        		Setpoint= NewSetpoint;
        	}
        	hour =LocalDateTime.now().getHour();
    	}
   */ 	
    	
		//GET CURRENT INDOOR TEMPERATURE------------------------------------------------------------------------------------------------------------------------------
  /*  	
    	String indoortemp="";
    	List<OrchestrationResultDTO> response01 = orchestrate("get-indoortemperature", false);
    	final OrchestrationResultDTO result01 = response01.get(0); //Simplest way of choosing a provider.
    	final HttpMethod httpMethod01 = HttpMethod.GET;//Http method should be specified in the description of the service.
    	final String address01 = result01.getProvider().getAddress();
    	final int port01 = result01.getProvider().getPort();
    	final String serviceUri01 = result01.getServiceUri();
    	final String interfaceName01 = result01.getInterfaces().get(0).getInterfaceName(); //Simplest way of choosing an interface.
    	String token01 = null;
    	if (result01.getAuthorizationTokens() != null) {
    		token01 = result01.getAuthorizationTokens().get(interfaceName01); //Can be null when the security type of the provider is 'CERTIFICATE' or nothing.
		}
    	final Object payload01 = null; //Can be null if not specified in the description of the service.
    	final String consumedService01 = arrowheadService.consumeServiceHTTP(String.class, httpMethod01, address01, port01, serviceUri01, interfaceName01, token01, payload01);
    	logger.info("Current Room Temperature "+httpMethod01+"/"+address01+":"+port01+serviceUri01+": "+consumedService01);
    	indoortemp= consumedService01;
    	
  */  
    	
    	//GET CURRENT PIPE TEMPERATURE------------------------------------------------------------------------------------------------------------------------------
 /*   	
    	//String pipeTemp="";
       	List<OrchestrationResultDTO> response02 = orchestrate("get-PipeTemperature", false);
    	final OrchestrationResultDTO result02 = response02.get(0); //Simplest way of choosing a provider.
    	final HttpMethod httpMethod02 = HttpMethod.GET;//Http method should be specified in the description of the service.
    	final String address02 = result02.getProvider().getAddress();
    	final int port02 = result02.getProvider().getPort();
    	final String serviceUri02 = result02.getServiceUri();
    	final String interfaceName02 = result02.getInterfaces().get(0).getInterfaceName(); //Simplest way of choosing an interface.
    	String token02 = null;
    	if (result02.getAuthorizationTokens() != null) {
    		token02 = result02.getAuthorizationTokens().get(interfaceName02); //Can be null when the security type of the provider is 'CERTIFICATE' or nothing.
		}
    	final Object payload02 = null; //Can be null if not specified in the description of the service.
    	final String consumedService02 = arrowheadService.consumeServiceHTTP(String.class, httpMethod02, address02, port02, serviceUri02, interfaceName02, token02, payload02);
    	logger.info("Current Pipe Temperature "+httpMethod02+"/"+address02+":"+port02+serviceUri02+": "+consumedService02);
    
   */ 	
    	
/*
    	

    	Thread.sleep(60000);
    	
    	}
  */ 
    	
	}
    
  //GET CURRENT OPCUS STATE THROUGH INTRA CLOUD--------------------------------------------------------------------------------------------------------
    private String getPrice() {
    	List<OrchestrationResultDTO> response00= orchestrate("StatusCheck", false);
    	final OrchestrationResultDTO result00 = response00.get(0); //Simplest way of choosing a provider.
    	final HttpMethod httpMethod00 = HttpMethod.GET;//Http method should be specified in the description of the service.
    	final String address00 = result00.getProvider().getAddress();
    	final int port00 = result00.getProvider().getPort();
    	final String serviceUri00 = result00.getServiceUri();
    	final String interfaceName00 = result00.getInterfaces().get(0).getInterfaceName(); //Simplest way of choosing an interface.
    	String token00 = null;
    	if (result00.getAuthorizationTokens() != null) {
    		token00 = result00.getAuthorizationTokens().get(interfaceName00); //Can be null when the security type of the provider is 'CERTIFICATE' or nothing.
		}
    	final Object payload00 = null; //Can be null if not specified in the description of the service.
    	
    	final String consumedService00 = arrowheadService.consumeServiceHTTP(String.class, httpMethod00, address00, port00, serviceUri00, interfaceName00, token00, payload00);
    //	logger.info("Actuator Values are "+httpMethod00+"/"+address00+":"+port00+serviceUri00+": "+consumedService00);
		return consumedService00;
    }
   
  //DERIVING TEMP SETPOINT BASED ON PRICE--------------------------------------------------------------------------------------------------
/*
	private double getSetpoint(Double Tmax, Double Tmin, Double Pmax, Double Pmin, String price) {
		double NewSetpoint = 0;
		double Pcurrent= Double.parseDouble(price);
		double m,b;
		
		m= (Tmax-Tmin)/(Pmin-Pmax);
		//System.out.println("m= "+m);
		
		b= Tmin-(m*Pmax);
		//System.out.println("b= "+b);
		
		if(Pcurrent>= Pmax) {
			NewSetpoint= Tmin;
		}
		else if(Pcurrent<= Pmin){
			NewSetpoint= Tmax;
		}
		else
			NewSetpoint= m*Pcurrent+b;
		
		NewSetpoint = Math.round(NewSetpoint * 100.0) / 100.0;
		logger.info("Derived Setpoint= "+NewSetpoint);
		
		return NewSetpoint;
	}
	
  */  
    //SEND SETPOINT TO ZWAVE PROVIDER------------------------------------------------------------------------------------------------------------------------------
	/*  private void ZwaveSwitchMilling(int millingState) {
    	
    	List<OrchestrationResultDTO> response03 = orchestrate("switch-Milling-Indicator-Light", false);
    	final OrchestrationResultDTO result03 = response03.get(0); //Simplest way of choosing a provider.
    	final HttpMethod httpMethod03 = HttpMethod.PUT;//Http method should be specified in the description of the service.
    	final String address03 = result03.getProvider().getAddress();
    	final int port03 = result03.getProvider().getPort();
    	String serviceUri03 = result03.getServiceUri();
    	serviceUri03= serviceUri03+"/"+Integer.toString(millingState);
    	final String interfaceName03 = result03.getInterfaces().get(0).getInterfaceName(); //Simplest way of choosing an interface.
    	String token03 = null;
    	if (result03.getAuthorizationTokens() != null) {
    		token03 = result03.getAuthorizationTokens().get(interfaceName03); //Can be null when the security type of the provider is 'CERTIFICATE' or nothing.
		}
    	final Object payload03 = null; //Can be null if not specified in the description of the service.
    	final String consumedService03 = arrowheadService.consumeServiceHTTP(String.class, httpMethod03, address03, port03, serviceUri03, interfaceName03, token03, payload03);
    	logger.info("New millingState sent to Zwave Controller: "+httpMethod03+"/"+address03+":"+port03+serviceUri03+": "+consumedService03);
    } */
    
    
    	 private void Rospickupservice() {
    	
    	List<OrchestrationResultDTO> response04 = orchestrate("ros-pickup", false);
    	final OrchestrationResultDTO result04 = response04.get(0); //Simplest way of choosing a provider.
    	final HttpMethod httpMethod04 = HttpMethod.POST;//Http method should be specified in the description of the service.
    	final String address04 = result04.getProvider().getAddress();
    	final int port04 = result04.getProvider().getPort();
    	String serviceUri04 = result04.getServiceUri();
    	//serviceUri04= serviceUri04+"/"+Integer.toString(pickupvalue);
    	final String interfaceName04 = result04.getInterfaces().get(0).getInterfaceName(); //Simplest way of choosing an interface.
    	String token04 = null;
    	if (result04.getAuthorizationTokens() != null) {
    		token04 = result04.getAuthorizationTokens().get(interfaceName04); //Can be null when the security type of the provider is 'CERTIFICATE' or nothing.
		}
    	final Object payload04 = null; //Can be null if not specified in the description of the service.
    	final String consumedService04 = arrowheadService.consumeServiceHTTP(String.class, httpMethod04, address04, port04, serviceUri04, interfaceName04, token04, payload04);
    	logger.info("New DrillingState sent to Zwave Controller: "+httpMethod04+"/"+address04+":"+port04+serviceUri04+": "+consumedService04);
    }
    
    
    //DEFINING THE ORCHESTRATION METHOD---------------------------------------------------------------------------------------------------------------------------
    private  List<OrchestrationResultDTO> orchestrate(String serviceDefinition, boolean flag) {
    	final Builder orchestrationFormBuilder = arrowheadService.getOrchestrationFormBuilder();
    	   
    	final ServiceQueryFormDTO requestedService = new ServiceQueryFormDTO();
    	requestedService.setServiceDefinitionRequirement(serviceDefinition);
    	
    	orchestrationFormBuilder.requestedService(requestedService)
    							.flag(Flag.MATCHMAKING, false) //When this flag is false or not specified, then the orchestration response cloud contain more proper provider. Otherwise only one will be chosen if there is any proper.
    							.flag(Flag.OVERRIDE_STORE, true) //When this flag is false or not specified, then a Store Orchestration will be proceeded. Otherwise a Dynamic Orchestration will be proceeded.
    							.flag(Flag.TRIGGER_INTER_CLOUD, flag); //When this flag is false or not specified, then orchestration will not look for providers in the neighbor clouds, when there is no proper provider in the local cloud. Otherwise it will. 
    	
    	final OrchestrationFormRequestDTO orchestrationRequest = orchestrationFormBuilder.build();
    	//printOut(orchestrationRequest);
    	OrchestrationResponseDTO response = null;
    	try {
    		response = arrowheadService.proceedOrchestration(orchestrationRequest);	
    		//printOut(response);
    		
		} catch (final ArrowheadException ex) {
			//Handle the unsuccessful request as you wish!
			
		}
    	
    	//EXAMPLE OF CONSUMING THE SERVICE FROM A CHOSEN PROVIDER
    	
    	if (response == null || response.getResponse().isEmpty()) {
    		//If no proper providers found during the orchestration process, then the response list will be empty. Handle the case as you wish!
    		System.out.println("Orchestration response is empty");
    	}
    	final List<OrchestrationResultDTO> result = response.getResponse(); //Simplest way of choosing a provider.
		return result;
    }
    
   
    
    private void printOut(final Object object) {
		System.out.println(Utilities.toPrettyJson(Utilities.toJson(object)));
	}
}
