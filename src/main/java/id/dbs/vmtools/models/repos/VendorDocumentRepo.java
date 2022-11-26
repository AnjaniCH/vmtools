package id.dbs.vmtools.models.repos;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import id.dbs.vmtools.models.entities.VendorDocument;

public interface VendorDocumentRepo extends JpaRepository<VendorDocument, Integer> {
  @Query(value = "SELECT dt.*, doc.documentId, doc.fileName, DATE_FORMAT(doc.expiryDate, '%Y-%m-%d') as expiryDate, DATE_FORMAT(doc.approvalTime, '%Y-%m-%d %H:%i:%s') as last_update, IFNULL(doc.status, 'Not Available') as status FROM DocumentTypes dt LEFT JOIN (SELECT vd.* FROM VendorDocuments vd JOIN (SELECT MAX(v.documentId) maxDocId, v.vendorId, v.documentTypeId FROM VendorDocuments v WHERE v.vendorId = ?1 GROUP BY v.documentTypeId) vd2 ON vd.documentTypeId = vd2.documentTypeId AND vd.documentId = vd2.maxDocId WHERE vd.vendorId = ?2 ORDER BY vd.documentId DESC) doc ON dt.documentTypeId = doc.documentTypeId", nativeQuery = true)
  List<Map<String, Object>> getVendorDocuments(String vendorId, String vendorId2);

  List<VendorDocument> findByVendorIdAndDocumentTypeId(String vendorId, Integer documentTypeId);

  @Query(value = "SELECT vd.fileName, vd.expiryDate, DATE_FORMAT(vd.receivedTime, '%Y-%m-%d %H:%i:%s') receivedTime, DATE_FORMAT(vd.verificationTime, '%Y-%m-%d %H:%i:%s') verificationTime, DATE_FORMAT(vd.approvalTime, '%Y-%m-%d %H:%i:%s') approvalTime, (SELECT u.name FROM Users u WHERE u.userId = vd.verifier) verifier, (SELECT u.name FROM Users u WHERE u.userId = vd.approver) approver, vd.status FROM VendorDocuments vd WHERE vd.vendorId = ?1 AND vd.documentTypeId = ?2", nativeQuery = true)
  List<Map<String, Object>> getHistoryDocuments(String vendorId, Integer documentTypeId);
}