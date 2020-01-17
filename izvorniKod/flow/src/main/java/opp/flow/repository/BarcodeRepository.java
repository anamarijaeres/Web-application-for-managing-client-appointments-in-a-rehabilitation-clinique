package opp.flow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import opp.flow.model.Barcode;

@Repository
public interface BarcodeRepository extends JpaRepository<Barcode, Long> {
	Barcode findByproductname(String productname);
}