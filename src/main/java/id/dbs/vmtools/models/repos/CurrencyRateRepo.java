package id.dbs.vmtools.models.repos;

import org.springframework.data.repository.CrudRepository;

import id.dbs.vmtools.models.entities.CurrencyRate;

public interface CurrencyRateRepo extends CrudRepository<CurrencyRate, Integer> {
  
}
