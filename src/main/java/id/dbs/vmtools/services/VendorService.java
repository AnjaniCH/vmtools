package id.dbs.vmtools.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.dbs.vmtools.models.entities.Vendor;
import id.dbs.vmtools.models.repos.VendorRepo;

@Service
@Transactional
public class VendorService {
  @Autowired
  private VendorRepo vendorRepo;

  // insert into table
  public Vendor insert(Vendor vendor){
    return vendorRepo.save(vendor);
  }

  // select data by id
  public Vendor getDataById(String id){
    Optional<Vendor> vendor = vendorRepo.findById(id);
    if(!vendor.isPresent()){
      return null;
    }
    return vendor.get();
  }

  // select all data
  public Iterable<Vendor> getAll(){
    return vendorRepo.findAll();
  }

  // delete data by id
  public void delete(String id){
    vendorRepo.deleteById(id);
  }
}
