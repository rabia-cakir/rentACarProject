package com.kodlamaio.rentACar.core.utilities.adapters.concretes;

import java.rmi.RemoteException;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.core.utilities.adapters.abstracts.CheckUserService;
import com.kodlamaio.rentACar.entities.concretes.IndividualCustomer;
import com.kodlamaio.rentACar.entities.concretes.User;

import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

@Service
public class MernisAdapter implements CheckUserService{

	@Override
	public boolean checkIfUserExist(IndividualCustomer individualCustomer) {
		boolean result=false;
		KPSPublicSoapProxy kpsPublicSoapProxy=new KPSPublicSoapProxy();
		try {
			result=kpsPublicSoapProxy.TCKimlikNoDogrula(Long.parseLong(individualCustomer.getIdentityNumber()), individualCustomer.getFirstName(), 
					individualCustomer.getLastName(), individualCustomer.getBirthDate().getYear());
		} catch (NumberFormatException e) {
			System.out.println("number format exception");
			e.printStackTrace();
		} catch (RemoteException e) {
			System.out.println("remote exception");
			e.printStackTrace();
		}
		return result;
	}

}
