package id.dbs.vmtools.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.dbs.vmtools.models.entities.CurrencyRate;
import id.dbs.vmtools.models.repos.CurrencyRateRepo;

@Service
@Transactional
public class CurrencyRateService {
  @Autowired
  private CurrencyRateRepo currRateRepo;

  // insert into table
  public CurrencyRate insert(CurrencyRate currency){
    return currRateRepo.save(currency);
  }

  // select data by id
  public CurrencyRate getDataById(Integer id){
    Optional<CurrencyRate> currency = currRateRepo.findById(id);
    if(!currency.isPresent()){
      return null;
    }
    return currency.get();
  }

  // select all data
  public Iterable<CurrencyRate> getAll(){
    return currRateRepo.findAll();
  }

  // delete data by id
  public void delete(Integer id){
    currRateRepo.deleteById(id);
  }
}
