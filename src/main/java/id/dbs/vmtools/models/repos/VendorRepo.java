package id.dbs.vmtools.models.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import id.dbs.vmtools.models.entities.Vendor;

public interface VendorRepo extends JpaRepository<Vendor, String> {
}