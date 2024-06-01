package exportation.model.bl;

import lombok.Getter;
import exportation.controller.exceptions.NoSupplierFoundException;
import exportation.model.da.SupplierDa;
import exportation.model.entity.Supplier;
import exportation.model.tools.CRUD;

import java.util.List;

public class SupplierBl implements CRUD<Supplier> {
    @Getter
    private static SupplierBl supplierBl = new SupplierBl();

    private SupplierBl() {
    }

    //save
    @Override
    public Supplier save(Supplier supplier) throws Exception {
        try (SupplierDa supplierDa = new SupplierDa()) {
            supplierDa.save(supplier);
            return supplier;
        }
    }

    //edit
    @Override
    public Supplier edit(Supplier supplier) throws Exception {
        try (SupplierDa supplierDa = new SupplierDa()) {
            if (supplierDa.findById(supplier.getId()) != null) {
                supplierDa.edit(supplier);
                return supplier;
            } else {
                throw new NoSupplierFoundException();
            }
        }
    }

    //remove
    @Override
    public Supplier remove(int id) throws Exception {
        try (SupplierDa supplierDa = new SupplierDa()) {
            Supplier supplier = supplierDa.findById(id);
            if (supplier != null) {
                supplierDa.remove(id);
                return supplier;
            } else {
                throw new NoSupplierFoundException();
            }
        }
    }

    //findAll
    @Override
    public List<Supplier> findAll() throws Exception {
        try (SupplierDa supplierDa = new SupplierDa()) {
            List<Supplier> perosnList = supplierDa.findAll();
            if (!perosnList.isEmpty()) {
                return perosnList;
            } else {
                throw new NoSupplierFoundException();
            }
        }
    }

    //findById
    @Override
    public Supplier findById(int id) throws Exception {
        try (SupplierDa supplierDa = new SupplierDa()) {
            Supplier supplier = supplierDa.findById(id);
            if (supplier != null) {
                return supplier;
            } else {
                throw new NoSupplierFoundException();
            }
        }
    }
}
