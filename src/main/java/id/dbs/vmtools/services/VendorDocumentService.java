package id.dbs.vmtools.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.dbs.vmtools.models.entities.VendorDocument;
import id.dbs.vmtools.models.repos.VendorDocumentRepo;

@Service
@Transactional
public class VendorDocumentService {
  @Autowired
  private VendorDocumentRepo documentRepo;

  // insert into table
  public VendorDocument insert(VendorDocument doc) {
    return documentRepo.save(doc);
  }

  // batch insert
  public List<VendorDocument> insertBatch(List<VendorDocument> doc) {
    return documentRepo.saveAll(doc);
  }

  // select data by id
  public VendorDocument getDataById(Integer id) {
    Optional<VendorDocument> doc = documentRepo.findById(id);
    if (!doc.isPresent()) {
      return null;
    }
    return doc.get();
  }

  // select data by vendorId
  public List<Map<String, Object>> getDataByVendorId(String vendorId) {
    List<Map<String, Object>> results = documentRepo.getVendorDocuments(vendorId, vendorId);
    return results;
  }

  public List<Map<String, Object>> getHistory(String vendorId, Integer documentTypeId){
    return documentRepo.getHistoryDocuments(vendorId, documentTypeId);
  }
}