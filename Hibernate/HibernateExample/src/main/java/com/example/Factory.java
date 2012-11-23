package com.example;

public class Factory {

	  private static PCImplDAO  PCImplDAO = null;
	  private static COMImplDAO COMImplDAO = null;
	  private static Factory instance = null;

	  public static synchronized Factory getInstance(){
	    if (instance == null){
	      instance = new Factory();
	    }
	    return instance;
	  }

	  public PCImplDAO getPCDAO(){
	    if (PCImplDAO == null){
	      PCImplDAO = new PCImplDAO();
	    }
	    return PCImplDAO;
	  }

	  public COMImplDAO getCOMDAO(){
	    if (COMImplDAO == null){
	      COMImplDAO = new COMImplDAO();
	    }
	    return COMImplDAO;
	  }

	   
	
}
